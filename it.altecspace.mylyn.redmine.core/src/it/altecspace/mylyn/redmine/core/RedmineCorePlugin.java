package it.altecspace.mylyn.redmine.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class RedmineCorePlugin implements BundleActivator
{

	private static BundleContext context;

	static BundleContext getContext()
	{
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception
	{
		RedmineCorePlugin.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception
	{
		RedmineCorePlugin.context = null;
	}

}
