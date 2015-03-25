package it.altecspace.mylyn.redmine.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RedmineUiPlugin extends AbstractUIPlugin
{
	// The plug-in ID
	public static final String PLUGIN_ID = "it.altecspace.mylyn.redmine.ui"; //$NON-NLS-1$

	// The shared instance
	private static RedmineUiPlugin plugin;

	/**
	 * The constructor
	 */
	public RedmineUiPlugin()
	{
	}

	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RedmineUiPlugin getDefault()
	{
		return plugin;
	}

}
