package it.altecspace.mylyn.redmine.ui;

import it.altecspace.mylyn.redmine.core.RedmineRepositoryConnectorConstants;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.ITaskRepositoryPage;

public class RedmineRepositoryConnectorUI extends AbstractRepositoryConnectorUi
{
	@Override
	public String getConnectorKind()
	{
		return RedmineRepositoryConnectorConstants.CONNECTOR_KIND;
	}

	@Override
	public ITaskRepositoryPage getSettingsPage(TaskRepository repository)
	{
		return new RedmineTaskRepositorySettingPage(repository);
	}

	@Override
	public IWizard getQueryWizard(TaskRepository repository, IRepositoryQuery query)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizard getNewTaskWizard(TaskRepository repository, ITaskMapping selection)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSearchPage()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
