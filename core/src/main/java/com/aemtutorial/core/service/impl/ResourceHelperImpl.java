package com.aemtutorial.core.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.aemtutorial.core.service.ResourceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service= ResourceHelper.class)
public class ResourceHelperImpl implements ResourceHelper {

	private static final Logger log = LoggerFactory.getLogger(ResourceHelperImpl.class);
	@Reference
	ResourceResolverFactory resourceResolverFactory;

	@Override
	public ResourceResolver getResourceResolver() throws  org.apache.sling.api.resource.LoginException  {
	    Map<String, Object> map = new HashMap< String, Object>();
	    map.put(ResourceResolverFactory.SUBSERVICE, "subServiceName");
	    return resourceResolverFactory.getServiceResourceResolver(map);
	}
}

