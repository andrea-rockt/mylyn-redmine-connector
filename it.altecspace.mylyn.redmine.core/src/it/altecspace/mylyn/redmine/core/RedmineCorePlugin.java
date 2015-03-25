package it.altecspace.mylyn.redmine.core;

import it.altecspace.mylyn.redmine.client.RedmineClientManager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class RedmineCorePlugin implements BundleActivator
{
	public static final String PLUGIN_ID = "it.altecspace.mylyn.redmine.core"; //$NON-NLS-1$

	private static BundleContext context;
	
	private static RedmineCorePlugin instance;
	
	
	public static RedmineCorePlugin getDefault()
	{
		return instance;
	}

	
	static BundleContext getContext()
	{
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception
	{
		RedmineCorePlugin.context = bundleContext;
		instance=this;
	}

	public void stop(BundleContext bundleContext) throws Exception
	{
		RedmineCorePlugin.context = null;
	}
	
	

}
