package it.altecspace.mylyn.redmine.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentHandler;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentSource;
import org.eclipse.mylyn.tasks.core.data.TaskAttachmentMapper;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

public class RedmineTaskAttachmentHandler extends AbstractTaskAttachmentHandler
{

	@Override
	public boolean canGetContent(TaskRepository repository, ITask task)
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canPostContent(TaskRepository repository, ITask task)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InputStream getContent(TaskRepository repository, ITask task, TaskAttribute attachmentAttribute,
			IProgressMonitor monitor) throws CoreException
	{
		TaskAttachmentMapper mapper = TaskAttachmentMapper.createFrom(attachmentAttribute);
		
		URL url = null;
		
		try
		{
			url = new URL(mapper.getUrl());
		}
		catch (MalformedURLException e)
		{
			throw new CoreException(new RepositoryStatus(Status.ERROR, RedmineCorePlugin.PLUGIN_ID, RepositoryStatus.ERROR_NETWORK, "Error downloading attachment query.",e));
		}
		
		try
		{
			return url.openStream();
		}
		catch (IOException e)
		{
			throw new CoreException(new RepositoryStatus(Status.ERROR, RedmineCorePlugin.PLUGIN_ID, RepositoryStatus.ERROR_NETWORK, "Error downloading attachment query.",e));
		}
	}

	@Override
	public void postContent(TaskRepository repository, ITask task, AbstractTaskAttachmentSource source, String comment,
			TaskAttribute attachmentAttribute, IProgressMonitor monitor) throws CoreException
	{
		// TODO Auto-generated method stub
		
	}

}
