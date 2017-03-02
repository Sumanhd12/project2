package com.aem.service.impl;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.ComponentContext;

import com.aem.service.MyService;

@Component(metatype = true, label = "My service", description = "My service", immediate = true)
@Service
@Properties({
		@Property(name = "service.pid", value = "com.aem.service.impl.MyServiceImpl", propertyPrivate = false),
		@Property(name = "service.vendor", value = "My service", propertyPrivate = false) })
public class MyServiceImpl implements MyService {

	@Reference
	private SlingSettingsService slingSettings;

	@Reference
	private ResourceResolverFactory resourceResolverFactory;

	/**
	 * {@inheritDoc}
	 */
	@Activate
	protected void activate(final ComponentContext context) {
		// If an author instance, ensure we have a redirects directory created.
		System.out.println("Registered the service");
		if (this.slingSettings.getRunModes().contains("author")) {
			System.out.println("author run mode ");
		}
	}
	
	public String doService() {
		System.out.println("service called");
		return null;
	}

}
