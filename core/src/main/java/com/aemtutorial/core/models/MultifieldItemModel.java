package com.aemtutorial.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class MultifieldItemModel {

	 @ValueMapValue
	    private String cardtitle;

	    @ValueMapValue
	    private String carddescription;

	    @ValueMapValue
	    private String cardimage;

	    @ValueMapValue
	    private String cardredirectlink;

	    public String getCardtitle() {
	        return cardtitle;
	    }

	    public String getCarddescription() {
	        return carddescription;
	    }

	    public String getCardimage() {
	        return cardimage;
	    }

	    public String getCardredirectlink() {
	        return cardredirectlink;
	    }
}
