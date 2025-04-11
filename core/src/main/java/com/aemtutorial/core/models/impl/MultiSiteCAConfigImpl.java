package com.aemtutorial.core.models.impl;

import com.aemtutorial.core.config.DemoCaConfig;
import com.aemtutorial.core.models.MultiSiteCAConfig;
import com.day.cq.wcm.api.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.caconfig.ConfigurationResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;


@Model(adaptables = SlingHttpServletRequest.class,adapters = {MultiSiteCAConfig.class},resourceType = {MultiSiteCAConfigImpl.RESOURCE_TYPE}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiSiteCAConfigImpl implements MultiSiteCAConfig {
    private static final Logger LOG = LoggerFactory.getLogger(MultiSiteCAConfigImpl.class);
    protected static final String RESOURCE_TYPE = "aemtutorial/components/custom-components/caconfigcomponent";

    @SlingObject
    ResourceResolver resourceResolver;

    @ScriptVariable
    Page currentPage;

    @OSGiService
    ConfigurationResolver configurationResolver;

    private String siteCountry;
    private String siteLocale;
    private String siteEmailId;
    private DemoCaConfig demoCaConfig;

    @Override
    public String getSiteCountry() {
        return siteCountry;
    }

    @Override
    public String getSiteLocale() {
        return siteLocale;
    }

    @Override
    public String getSiteEmailId() {
        return siteEmailId;
    }

    @PostConstruct
    public void postConstruct() {
        DemoCaConfig msmcaConfig=getContextAwareConfig(currentPage.getPath(),resourceResolver);
        siteCountry=msmcaConfig.siteCountry();
        siteLocale=msmcaConfig.siteLocale();
        siteEmailId=msmcaConfig.siteEmailId();

    }

    public DemoCaConfig getContextAwareConfig(String currentPage, ResourceResolver resourceResolver) {
        String currentPath = StringUtils.isNotBlank(currentPage) ? currentPage : StringUtils.EMPTY;
        Resource contentResource = resourceResolver.getResource(currentPath);
        if (contentResource != null) {
            ConfigurationBuilder configurationBuilder = contentResource.adaptTo(ConfigurationBuilder.class);
            if (configurationBuilder != null) {
                return configurationBuilder.as(DemoCaConfig.class);
            }
        }
        return null;
    }
}
