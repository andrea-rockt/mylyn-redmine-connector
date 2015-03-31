package it.altecspace.mylyn.redmine.core;

import it.altecspace.mylyn.redmine.client.IRedmineClient;
import it.altecspace.mylyn.redmine.client.IRedmineClientManager;
import it.altecspace.mylyn.redmine.client.RedmineClientManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.tasks.core.AbstractRepositoryConnector;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITask;
import org.eclipse.mylyn.tasks.core.RepositoryInfo;
import org.eclipse.mylyn.tasks.core.RepositoryStatus;
import org.eclipse.mylyn.tasks.core.RepositoryVersion;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskAttachmentHandler;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;
import org.eclipse.mylyn.tasks.core.data.TaskMapper;
import org.eclipse.mylyn.tasks.core.sync.ISynchronizationSession;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

public class RedmineRepositoryConnector extends AbstractRepositoryConnector
{
	private final IRedmineClientManager clientManager = new RedmineClientManager(RedmineCorePlugin.getDefault().getStateLocation());

	private final RedmineTaskDataHandler taskDataHandler = new RedmineTaskDataHandler(this);
	
	private final RedmineTaskAttachmentHandler taskAttachmentHandler = new RedmineTaskAttachmentHandler(this);
	
	public IRedmineClientManager getClientManager()
	{
		return clientManager;
	}


	@Override
	public AbstractTaskAttachmentHandler getTaskAttachmentHandler()
	{
		return taskAttachmentHandler;
	}


	@Override
	public AbstractTaskDataHandler getTaskDataHandler()
	{
		return taskDataHandler;
	}


	@Override
	public boolean canCreateNewTask(TaskRepository repository)
	{

		return false;
	}

	@Override
	public boolean canCreateTaskFromKey(TaskRepository repository)
	{

		return false;
	}

	@Override
	public String getConnectorKind()
	{

		return RedmineRepositoryConnectorConstants.CONNECTOR_KIND;
	}

	@Override
	public String getLabel()
	{
		return RedmineRepositoryConnectorConstants.CONNECTOR_LABEL;
	}

	@Override
	public String getRepositoryUrlFromTaskUrl(String taskUrl)
	{
		int index = taskUrl.indexOf('/');
		
		return taskUrl.substring(0,index);
	}

	@Override
	public TaskData getTaskData(TaskRepository repository, String taskIdOrKey, IProgressMonitor monitor)
			throws CoreException
	{

		try
		{
			IRedmineClient client = getClientManager().getClient(repository);
			
			Issue issue = client.getIssueById(Integer.parseInt(taskIdOrKey));
			
			
			return taskDataHandler.createTaskDataFromIssue(repository, issue, client.getCachedRepositoryConfiguration());
			
		}
		catch (RedmineException e)
		{
			throw new CoreException(new RepositoryStatus(Status.ERROR, RedmineCorePlugin.PLUGIN_ID, RepositoryStatus.ERROR_NETWORK, "Error performing query.",e));
		}
	}

	@Override
	public String getTaskIdFromTaskUrl(String taskUrl)
	{

		int index = taskUrl.lastIndexOf('/');
		
		return taskUrl.substring(index,taskUrl.length());
	}

	@Override
	public String getTaskUrl(String repositoryUrl, String taskIdOrKey)
	{

		try
		{
			return (new URL(new URL(repositoryUrl),"issues/"+taskIdOrKey)).toString();
		}
		catch (MalformedURLException e)
		{
			return "";
		}
	}

	@Override
	public boolean hasTaskChanged(TaskRepository taskRepository, ITask task, TaskData taskData)
	{
		TaskMapper mapper = new RedmineTaskMapper(taskData);
		
		if (taskData.isPartial()) 
		{
			return mapper.hasChanges(task);
		} 
		else
		{
			java.util.Date repositoryDate = mapper.getModificationDate();
			java.util.Date localDate = task.getModificationDate();
			
			if (repositoryDate != null && repositoryDate.equals(localDate))
			{
				return false;
			}
			return true;
		}
	}

	@Override
	public IStatus performQuery(TaskRepository repository, IRepositoryQuery query, TaskDataCollector collector,
			ISynchronizationSession session, IProgressMonitor monitor)
	{
		
		TaskAttributeMapper mapper = taskDataHandler.getAttributeMapper(repository);
		
		
		
		monitor.beginTask("Performing query on repository.", 0);
		
		try
		{
	
			IRedmineClient client = getClientManager().getClient(repository);
			
			for(Project p : client.getCachedRepositoryConfiguration().getProjects())
			{
				
				for(Issue i : client.getIssuesByProject(p))
				{
					
					//boolean createdByMe = i.getAuthor().getId().equals(client.getCurrentUser().getId());
					//The && operator is short circuiting, if assignee is null the subsequent expression will not be evaluated
					boolean assignedToMe = i.getAssignee() != null  && i.getAssignee().getId().equals(client.getCurrentUser().getId());
					
					
					if(!(assignedToMe))
					{
						continue;
					}
						
					
					if(monitor.isCanceled())
					{
						return new RepositoryStatus(Status.CANCEL, RedmineCorePlugin.PLUGIN_ID, RepositoryStatus.OK, "Error performing query.");
					}
					
					TaskData t = taskDataHandler.createTaskDataFromIssue(repository, i, client.getCachedRepositoryConfiguration());
					t.setPartial(true);
					collector.accept(t);				
				}
			}
			
			monitor.done();
		}
		catch (RedmineException e)
		{
			return new RepositoryStatus(Status.ERROR, RedmineCorePlugin.PLUGIN_ID, RepositoryStatus.ERROR_NETWORK, "Error performing query.",e);
		}
		
		
				
		return RepositoryStatus.OK_STATUS;
	}

	@Override
	public void updateRepositoryConfiguration(TaskRepository taskRepository, IProgressMonitor monitor)
			throws CoreException
	{
		System.out.println("updating");
	}

	@Override
	public void updateTaskFromTaskData(TaskRepository taskRepository, ITask task, TaskData taskData)
	{

		
		RedmineTaskMapper mapper = new RedmineTaskMapper(taskData);
		mapper.applyTo(task);
	}

	@Override
	public RepositoryInfo validateRepository(TaskRepository repository, IProgressMonitor monitor) throws CoreException
	{
		RepositoryInfo info = new RepositoryInfo(new RepositoryVersion("1"));		
		
		try
		{
			clientManager.validateConnection(repository);
		}
		catch (RedmineException e)
		{
			throw new CoreException(new Status(IStatus.ERROR, RedmineCorePlugin.PLUGIN_ID, "Error connecting to repository",e));
		}
		
		return info;
	}
	
	

}
