package com.aemtutorial.core.schedulers;

import com.aemtutorial.core.ocd.DemoSchedulerOCDConfig;
import com.aemtutorial.core.service.ResourceHelper;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.RepositoryException;
import java.time.Instant;
import java.util.Calendar;

@Component(service = Runnable.class, immediate = true   )
@Designate(ocd = DemoSchedulerOCDConfig.class)
public class ArchivePageScheduler implements  Runnable {
    private String cronExpression;
    private boolean isEnabled;
    private static final Logger log = LoggerFactory.getLogger(ArchivePageScheduler.class);
    ResourceResolver resourceResolver = null;

    @Reference
    ResourceHelper resourceHelper;

    @Activate
    @Modified
    protected void activate(DemoSchedulerOCDConfig config) {
        this.cronExpression = config.scheduler_expression();
        this.isEnabled = config.scheduler_enabled();
        log.info("✅ Scheduler config loaded. Cron: {}, Enabled: {}", cronExpression, isEnabled);
    }
    @Override
    public void run() {
        log.info("📅 Simple Scheduler Started");

        if (!isEnabled) {
            log.info("🚫 Scheduler is disabled.");
            return;
        }
        log.info("📅 Scheduler triggered using cron: {}", cronExpression);

        try {
            resourceResolver = resourceHelper.getResourceResolver();
            Resource resource = resourceResolver.getResource("/content/aemtutorial/language-masters/en");

            String lastModifiedPagePath = null;
            Instant latestModifiedTime = Instant.MIN;


            if (resource != null) {
                for (Resource childPage : resource.getChildren()) {
                    Resource contentResource = childPage.getChild("jcr:content");
                    if (contentResource != null && contentResource.getValueMap().containsKey("cq:lastModified")) {
                        Calendar lastModifiedCal = contentResource.getValueMap().get("cq:lastModified", Calendar.class);
                        if (lastModifiedCal != null) {
                            Instant modified = lastModifiedCal.toInstant();
                            if (modified.isAfter(latestModifiedTime)) {
                                latestModifiedTime = modified;
                                lastModifiedPagePath = childPage.getPath();
                            }
                        }
                    }
                }
                if (lastModifiedPagePath != null) {
                    log.info("Most recently modified page: {}", lastModifiedPagePath);
                } else {
                    log.info("No pages with cq:lastModified found under {}", resource.getPath());
                }
            } else {
                log.error("Could not resolve root path: /content/aemtutorial/us/en");
            }

        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }

    }
}
