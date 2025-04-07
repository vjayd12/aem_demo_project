package com.aemtutorial.core.service;

import javax.jcr.RepositoryException;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;

public interface ResourceHelper {
	public ResourceResolver getResourceResolver() throws  RepositoryException , LoginException ;

}
