package it.altecspace.mylyn.redmine.ui;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositoryQueryPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class RedmineTaskRepositoryQueryPage extends AbstractRepositoryQueryPage
{
	private final static String TITLE = "Redmine query";

	public RedmineTaskRepositoryQueryPage( TaskRepository repository, IRepositoryQuery query)
	{
		super(TITLE, repository, query);
	}

	
	@Override
	public void applyTo(IRepositoryQuery query)
	{
		query.setSummary(getQueryTitle());
	}


	@Override
	public void createControl(Composite parent)
	{
		Composite displayArea = new Composite(parent, SWT.NONE);
		
		GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(true).applyTo(displayArea);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(displayArea);
		
		new Label(displayArea, SWT.NONE).setText("This connector can only query for assigned to user issues");
		
		setControl(displayArea);
	}


	@Override
	public String getQueryTitle()
	{
		return "Assigned to me";
	}
	
	
	

}
