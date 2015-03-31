package it.altecspace.mylyn.redmine.core;

import it.altecspace.mylyn.redmine.client.IRedmineClient;

import java.io.ByteArrayOutputStream;
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
import org.eclipse.mylyn.tasks.core.data.TaskMapper;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.AttachmentFactory;
import com.taskadapter.redmineapi.bean.Issue;

public class RedmineTaskAttachmentHandler extends AbstractTaskAttachmentHandler
{

	private final RedmineRepositoryConnector redmineRepositoryConnector;

	public RedmineTaskAttachmentHandler(RedmineRepositoryConnector redmineRepositoryConnector)
	{
		this.redmineRepositoryConnector = redmineRepositoryConnector;
	}

	@Override
	public boolean canGetContent(TaskRepository repository, ITask task)
	{
		return true;
	}

	@Override
	public boolean canPostContent(TaskRepository repository, ITask task)
	{
		return true;
	}

	@Override
	public InputStream getContent(TaskRepository repository, ITask task, TaskAttribute attachmentAttribute,
			IProgressMonitor monitor) throws CoreException
	{
		TaskAttachmentMapper mapper = TaskAttachmentMapper.createFrom(attachmentAttribute);
				
		try
		{
			IRedmineClient client = redmineRepositoryConnector.getClientManager().getClient(repository);
			Attachment attachment = client.getAttachmentById(Integer.parseInt(mapper.getAttachmentId()));
			return client.downloadAttachment(attachment);
		}
		catch (RedmineException e)
		{
			throw new CoreException(new RepositoryStatus(Status.ERROR, RedmineCorePlugin.PLUGIN_ID, RepositoryStatus.ERROR_NETWORK, "Error downloading attachment.",e));
		}		
	}

	@Override
	public void postContent(TaskRepository repository, ITask task, AbstractTaskAttachmentSource source, String comment,
			TaskAttribute attachmentAttribute, IProgressMonitor monitor) throws CoreException
	{
		Integer issueId = Integer.parseInt(task.getTaskId());
		
		TaskAttachmentMapper attachment = TaskAttachmentMapper.createFrom(attachmentAttribute);
		
		
		try
		{
			IRedmineClient client = redmineRepositoryConnector.getClientManager().getClient(repository);

			Issue issue = client.getIssueById(issueId);
			
			client.uploadAttachment(issue, source.getName(),source.getDescription(), source.getContentType(), source.createInputStream(monitor));
		}
		catch (Exception e)
		{
			throw new CoreException(new RepositoryStatus(Status.ERROR, RedmineCorePlugin.PLUGIN_ID, RepositoryStatus.ERROR_NETWORK, "Error downloading attachment.",e));
		}		
	}

}
