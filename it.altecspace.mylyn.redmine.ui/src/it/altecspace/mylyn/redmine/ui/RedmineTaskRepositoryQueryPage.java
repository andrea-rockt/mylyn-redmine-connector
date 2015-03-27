package it.altecspace.mylyn.redmine.ui;

import org.eclipse.mylyn.commons.workbench.forms.SectionComposite;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositoryQueryPage2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

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

		Text t = new Text(parent, SWT.MULTI | SWT.READ_ONLY | SWT.CENTER);
		
		t.setText("This repository support querying only for tasks created by the current user or assigned to the current user");
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
		query.setSummary("Assigned to me:");
	}
	
	
	

}
