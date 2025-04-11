package com.aemtutorial.core.config;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label = "Demo - Context Aware Configuration", description = "Context Aware Configuration")
public @interface DemoCaConfig {
    @Property(label = "AEMTutorial Country Site",
            description = "Site Name")
    String siteCountry() default "us";

    @Property(label = "AEMTutorial Site Locale",
            description = "Site for for different languages")
    String siteLocale() default "en";

  @Property(label = "Email Address for Local Sites",
            description = "Email Address for Local Sites")
    String siteEmailId();

}
