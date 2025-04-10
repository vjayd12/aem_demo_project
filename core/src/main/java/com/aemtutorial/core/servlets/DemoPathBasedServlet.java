package com.aemtutorial.core.servlets;

import com.aemtutorial.core.service.ResourceHelper;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletName;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;


@Component(service = Servlet.class,
        property = {
//                "sling.servlet.paths=/bin/pathExample",
                "sling.servlet.paths=/abc/pathExample", // Custom endpoint
                "sling.servlet.methods=GET"
        })

public class DemoPathBasedServlet extends SlingSafeMethodsServlet {

    @Reference
    private ResourceHelper resourceHelper;

    private static final String PAGE_PATH = "/content/aemtutorial/language-masters";
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Logger LOG = LoggerFactory.getLogger(DemoPathBasedServlet.class);
        JsonArray pagesArray = new JsonArray();
        ResourceResolver resourceResolver = null;
        try {
            resourceResolver = resourceHelper.getResourceResolver();
            Page page = resourceResolver.adaptTo(PageManager.class).getPage(PAGE_PATH);
            Iterator<Page> childPages = page.listChildren();
            while (childPages.hasNext()) {
                Page childPage = childPages.next();
                JsonObject pageObject = new JsonObject();
                pageObject.addProperty("title", childPage.getTitle());
                pageObject.addProperty("path", childPage.getPath().toString());
                pagesArray.add(pageObject);
            }

        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(pagesArray.toString());

    }
}
