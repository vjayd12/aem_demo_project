package com.aemtutorial.core.listeners;

import com.aemtutorial.core.service.ResourceHelper;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

@Component(immediate = true, service = EventListener.class)
public class PageStructureChangeListener implements EventListener {
    private static final Logger log = LoggerFactory.getLogger(PageStructureChangeListener.class);
    private Session session;
    private ObservationManager observationManager;
    ResourceResolver resourceResolver = null;
    @Reference
    ResourceHelper resourceHelper;

    @Activate
    protected void activate() {
        try {
            resourceResolver = resourceHelper.getResourceResolver();
            session = resourceResolver.adaptTo(Session.class);

            if (session != null) {
                observationManager = session.getWorkspace().getObservationManager();
                observationManager.addEventListener(
                        this,
                        Event.NODE_ADDED | Event.NODE_REMOVED | Event.PROPERTY_CHANGED | Event.PROPERTY_ADDED ,
                        "/content/aemtutorial/us/en/",
                        true,
                        null,
                        null,
                        false
                );
                log.info("JCR EventListener registered successfully for /content");
            } else {
                log.error("Unable to get JCR Session");
            }
        } catch (Exception e) {
            log.error("Exception during listener registration", e);
        }
    }

    @Deactivate
    protected void deactivate() {
        try {
            if (observationManager != null) {
                observationManager.removeEventListener(this);
                log.info("EventListener removed successfully");
            }
            if (session != null && session.isLive()) {
                session.logout();
            }
        } catch (RepositoryException e) {
            log.error("Error during listener deactivation", e);
        }
    }

    @Override
    public void onEvent(EventIterator events) {
        while (events.hasNext()) {
            Event event = events.nextEvent();
            try {
                log.info(" Event Type: {}, Path: {}", event.getType(), event.getPath());
            } catch (RepositoryException e) {
                log.error("Error processing JCR event", e);
            }
        }
    }
}
