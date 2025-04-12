package com.aemtutorial.core.workflow;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.aemtutorial.core.service.ResourceHelper;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(
        service = WorkflowProcess.class,
        property = {"process.label=Add Page Metadata"}
)
public class AddPageMetaDataStep  implements WorkflowProcess{
    @Reference
    private ResourceHelper resourceHelper;
    private static final Logger log = LoggerFactory.getLogger(AddPageMetaDataStep.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args)
            throws WorkflowException {
        ResourceResolver resourceResolver = null;
        try {
            // Get Session to connect with JCR
            resourceResolver = resourceHelper.getResourceResolver();
            // Getting payload from Workflow
            String payloadPath = workItem.getWorkflowData().getPayload().toString();
            if (resourceResolver == null) {
                log.info("null resource resolver");
            }
            // Getting Workflow Session with the help of ResourceResolver Session
            workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
            if (resourceResolver != null) {
                Resource pageContent = resourceResolver.getResource(payloadPath + "/jcr:content");
                if (pageContent != null) {
                    ModifiableValueMap properties = pageContent.adaptTo(ModifiableValueMap.class);
                    if (properties != null) {
                        log.info("properties",properties );
                        properties.put("workflowStatus", "Reviewed");
                        properties.put("reviewedBy", "custom-workflow-step");
                        resourceResolver.commit();

                    }
                }
            }

        } catch (Exception e) {
            throw new WorkflowException("Failed to update metadata on page", e);
        } finally {
            if (resourceResolver != null) {
                resourceResolver.close();
            }
        }
    }
}
