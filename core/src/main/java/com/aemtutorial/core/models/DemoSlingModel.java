package com.aemtutorial.core.models;

import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.sling.models.annotations.injectorspecific.SlingObject;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType="aemtutorial/components/custom-components/slingmodeldemo")
@Exporter(name = "jackson", extensions = "json", options = { @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true"),
		@ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true")
})

public class DemoSlingModel {

    @ValueMapValue
    @JsonIgnore
    private String title;

    @ValueMapValue
    private String description;

    @ChildResource(name = "sourcecardField")  // Multifield node name
    @Via("resource")   // Retrive the current resource
    private List<MultifieldItemModel> sourcecardField;   //List to return the multifield values

    @SlingObject
    private Resource currentResource; // Retrieves the current AEM resource

    @SlingObject
    private ResourceResolver resourceResolver; // Access to JCR repository

    @SlingObject
    private PageManager pageManager; 
    @JsonProperty("customdata")
    private String processedData;  // Stores custom logic output

    @PostConstruct
    protected void init() {
    	if (currentResource != null && resourceResolver != null) {
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            if (pageManager != null) {
                Page currentPage = pageManager.getContainingPage(currentResource);
                if (currentPage != null) {
                    processedData = "Current Page Title: " + currentPage.getTitle();
                } else {
                    processedData = "Current page is null.";
                }
            } else {
                processedData = "PageManager could not be adapted.";
            }
        } else {
            processedData = "Current Resource or ResourceResolver is null.";
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<MultifieldItemModel> getSourcecardField() {
        return sourcecardField;
    }

    public String getProcessedData() {
        return processedData;
    }
}
