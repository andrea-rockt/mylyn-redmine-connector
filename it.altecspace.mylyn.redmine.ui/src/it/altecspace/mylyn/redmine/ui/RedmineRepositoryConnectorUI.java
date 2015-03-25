package it.altecspace.mylyn.redmine.ui;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.ITaskRepositoryPage;

public class RedmineRepositoryConnectorUI extends AbstractRepositoryConnectorUi
{
	@Override
	public String getConnectorKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITaskRepositoryPage getSettingsPage(TaskRepository repository) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizard getQueryWizard(TaskRepository repository,
			IRepositoryQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizard getNewTaskWizard(TaskRepository repository,
			ITaskMapping selection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSearchPage() {
		// TODO Auto-generated method stub
		return false;
	}
}
