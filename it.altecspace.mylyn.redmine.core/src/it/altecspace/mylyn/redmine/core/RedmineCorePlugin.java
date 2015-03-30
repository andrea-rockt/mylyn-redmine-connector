package it.altecspace.mylyn.redmine.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class RedmineCorePlugin extends Plugin
{
	public static final String PLUGIN_ID = "it.altecspace.mylyn.redmine.core"; //$NON-NLS-1$

	private static BundleContext context;
	
	private static RedmineCorePlugin instance;
	
	
	public static RedmineCorePlugin getDefault()
	{
		return instance;
	}

	public IPath getStatePath() 
	{
		IPath stateLocation = Platform.getStateLocation(getBundle());
		
		return stateLocation;
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
