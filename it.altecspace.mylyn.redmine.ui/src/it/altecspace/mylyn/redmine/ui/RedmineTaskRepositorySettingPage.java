package it.altecspace.mylyn.redmine.ui;

import it.altecspace.mylyn.redmine.core.RedmineRepositoryConnectorConstants;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.swt.widgets.Composite;

public class RedmineTaskRepositorySettingPage extends AbstractRepositorySettingsPage
{

	private static final String TITLE = "Redmine repository settings";
	private static final String DESCRIPTION = "Example (http://www.redmine.org/)";
	
	public RedmineTaskRepositorySettingPage(TaskRepository taskRepository)
	{
		super(TITLE, DESCRIPTION, taskRepository);
	}

	@Override
	public String getConnectorKind()
	{
		return RedmineRepositoryConnectorConstants.CONNECTOR_KIND;
	}

	@Override
	protected void createAdditionalControls(Composite parent)
	{

	}

}
