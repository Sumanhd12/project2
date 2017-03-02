package com.aem.servlets;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.settings.SlingSettingsService;

import com.aem.service.MyService;

@SlingServlet(paths = {"/bin/postServlet","/bin/getServlet"}, extensions = "json", methods = { "GET", "POST" })
@Properties({ @Property(name = "service.pid", value = "com.aem.servlets.MyServlet", propertyPrivate = false),
        @Property(name = "service.description", value = " My Serlvet ", propertyPrivate = false),
        @Property(name = "service.vendor", value = "Practice ", propertyPrivate = false) })
public class MyServlet extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Reference
	private ResourceResolverFactory resourceResolverFactory;
	
	@Reference
	private SlingSettingsService slingSettings;
	
	@Reference
	private MyService service;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.slingSettings.getRunModes());
		Session jcrSession = request.getResourceResolver().adaptTo(Session.class);
		try {
			System.out.println(jcrSession.getRootNode());
			this.service.doService();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(this.slingSettings.getRunModes());
		Session jcrSession = request.getResourceResolver().adaptTo(Session.class);
		try {
			System.out.println(jcrSession.getRootNode());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
