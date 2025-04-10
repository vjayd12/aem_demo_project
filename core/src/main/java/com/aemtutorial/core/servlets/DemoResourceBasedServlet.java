package com.aemtutorial.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "aemtutorial/components/page",
        selectors = "detail", extensions = {"txt", "json"})
public class DemoResourceBasedServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet( SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/plain");
response.setCharacterEncoding("UTF-8");
response.getWriter().write("Hello World:" );

    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String formData = request.getParameter("data");

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Form submitted with data:" + formData );
    }
}
