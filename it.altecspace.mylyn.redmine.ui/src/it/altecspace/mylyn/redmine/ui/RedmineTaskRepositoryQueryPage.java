package it.altecspace.mylyn.redmine.ui;

import org.eclipse.mylyn.commons.workbench.forms.SectionComposite;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositoryQueryPage2;

public class RedmineTaskRepositoryQueryPage extends AbstractRepositoryQueryPage2
{
	private final static String TITLE = "Redmine query";

	public RedmineTaskRepositoryQueryPage( TaskRepository repository, IRepositoryQuery query)
	{
		super(TITLE, repository, query);
	}

	@Override
	protected void createPageContent(SectionComposite parent)
	{

		
	}

	@Override
	protected void doRefreshControls()
	{

		
	}

	@Override
	protected boolean hasRepositoryConfiguration()
	{
		return false;
	}

	@Override
	protected boolean restoreState(IRepositoryQuery query)
	{
		return false;
	}

	@Override
	public void applyTo(IRepositoryQuery query)
	{

		
	}
	
	
	

}
