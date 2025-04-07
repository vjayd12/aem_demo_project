package com.aemtutorial.core.service.impl;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemtutorial.core.service.ResourceHelper;

@Component(immediate = true , name = "Simple Component")
public class SimpleComponentImpl extends SlingAllMethodsServlet {

    private static final Logger logger = LoggerFactory.getLogger(SimpleComponentImpl.class);

    @Reference
    private ResourceHelper resourceHelper;

    private static final String PAGE_PATH = "/content/aemtutorial/us/en/jcr:content";  // Specify the page path here

    private String getProperty(Node node, String propertyName) {
        try {
            if (node.hasProperty(propertyName)) {
                return node.getProperty(propertyName).getString();
            }
        } catch (RepositoryException e) {
            logger.error("Error retrieving property: " + propertyName, e);
        }
        return "N/A"; // Default value if property is not found or error occurs
    }

    @Activate
    public void activate() {
        logger.info("Bundle activated.");
         ResourceResolver resourceResolver = null;
        try {
            // Get ResourceResolver and adapt to JCR Session
            resourceResolver = resourceHelper.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);

            if (resourceResolver == null || session == null) {
                logger.error("ResourceResolver or Session could not be adapted.");
                // return "Execution failed: ResourceResolver or Session issue.";
            }

            // Fetch page resource
            Resource pageResource = resourceResolver.getResource(PAGE_PATH);
            if (pageResource == null) {
                logger.info("No page found at path: {}", PAGE_PATH);
                // return "Execution failed: Page not found.";
            }

            // Adapt Resource to Node to access JCR data
            Node node = pageResource.adaptTo(Node.class);
            if (node == null) {
                logger.info("No JCR data available for page at path: {}", PAGE_PATH);
                // return "Execution failed: No JCR data available.";
            }

            // Retrieve properties
            String title = getProperty(node, "jcr:title");
            String description = getProperty(node, "jcr:description");

            // Log retrieved data
            logger.info("Page Data -> Title: {}, Description: {}", title, description);

        } catch (RepositoryException e) {
            logger.error("Error occurred while fetching JCR data", e);
            // return "Execution failed due to error: " + e.getMessage();
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } finally {
            // Make sure to close the ResourceResolver
            if (resourceResolver != null) {
                resourceResolver.close();
            }
        }
    }

    @Deactivate
    public void deactivate() {
        logger.info("Bundle deactivated.");
    }
}
