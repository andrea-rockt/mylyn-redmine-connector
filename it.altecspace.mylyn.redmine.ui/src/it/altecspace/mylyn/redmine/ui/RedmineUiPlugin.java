package it.altecspace.mylyn.redmine.ui;

import it.altecspace.mylyn.redmine.client.IRedmineClientManager;
import it.altecspace.mylyn.redmine.core.RedmineCorePlugin;
import it.altecspace.mylyn.redmine.core.RedmineRepositoryConnector;

import org.eclipse.mylyn.tasks.ui.TasksUi;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.omg.CORBA.OMGVMCID;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class RedmineUiPlugin extends AbstractUIPlugin
{
	public static final String PLUGIN_ID = "it.altecspace.mylyn.redmine.ui"; //$NON-NLS-1$

	private static RedmineUiPlugin plugin;

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

	public static RedmineUiPlugin getDefault()
	{
		return plugin;
	}

}
