package com.aemtutorial.core.listeners;

import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Component(
        service = ResourceChangeListener.class,
        immediate = true,
        property = {
                ResourceChangeListener.PATHS + "=/content/aemtutorial/us/en/",
                ResourceChangeListener.CHANGES + "=ADDED",
                ResourceChangeListener.CHANGES + "=CHANGED"
        }
)

public class DemoEventListener implements ResourceChangeListener {
    private static final Logger log = LoggerFactory.getLogger(DemoEventListener.class);

    @Override
    public void onChange( List<ResourceChange> changes) {
        for (ResourceChange change : changes) {
            log.info("Resource Change Detected - Type: {}, Path: {}",
                    change.getType(), change.getPath());
        }
    }
}
