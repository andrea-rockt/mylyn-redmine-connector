package it.altecspace.mylyn.redmine.ui;

import it.altecspace.mylyn.redmine.core.RedmineRepositoryConnectorConstants;

import org.eclipse.mylyn.commons.ui.CommonImages;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;
import org.eclipse.mylyn.tasks.ui.TasksUiUtil;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPageFactory;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorInput;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.IFormPage;

public class RedmineTaskEditorPageFactory extends AbstractTaskEditorPageFactory
{

	@Override
	public boolean canCreatePageFor(TaskEditorInput input)
	{
		if (input.getTask().getConnectorKind().equals(RedmineRepositoryConnectorConstants.CONNECTOR_KIND))
		{
			return true;
		}
		else if (TasksUiUtil.isOutgoingNewTask(input.getTask(), RedmineRepositoryConnectorConstants.CONNECTOR_KIND))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public IFormPage createPage(TaskEditor parentEditor)
	{
		return new RedmineTaskEditorPage(parentEditor, RedmineRepositoryConnectorConstants.CONNECTOR_KIND);
	}

	@Override
	public Image getPageImage()
	{
		return CommonImages.getImage(TasksUiImages.TASK);

	}

	@Override
	public String getPageText()
	{
		return "Issue";
	}

}
