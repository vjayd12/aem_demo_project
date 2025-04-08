package com.aemtutorial.core.ocd;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Designate(ocd = DomainAPIConfiguration.class)
@ObjectClassDefinition(name = "BFF API Endpoint", description = "Configuration for Third Party API endpoint")
public @interface  DomainAPIConfiguration {
    @AttributeDefinition(
            name = "BFF Endpoint",
            description = "The endpoint url for the API",
            type = AttributeType.STRING
    )
    String bffEndpoint() default "https://jsonplaceholder.typicode.com/users";
}
