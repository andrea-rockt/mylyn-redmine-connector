package it.altecspace.mylyn.redmine.core;

import it.altecspace.mylyn.redmine.client.IRedmineClient;
import it.altecspace.mylyn.redmine.client.IRedmineClientManager;
import it.altecspace.mylyn.redmine.client.RedmineClientManager;

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
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskDataCollector;
import org.eclipse.mylyn.tasks.core.sync.ISynchronizationSession;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

public class RedmineRepositoryConnector extends AbstractRepositoryConnector
{
	private final IRedmineClientManager clientManager = new RedmineClientManager();

	private final RedmineTaskDataHandler taskDataHandler = new RedmineTaskDataHandler();
	
	public IRedmineClientManager getClientManager()
	{
		return clientManager;
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

		return null;
	}

	@Override
	public TaskData getTaskData(TaskRepository repository, String taskIdOrKey, IProgressMonitor monitor)
			throws CoreException
	{

		return null;
	}

	@Override
	public String getTaskIdFromTaskUrl(String taskUrl)
	{

		return null;
	}

	@Override
	public String getTaskUrl(String repositoryUrl, String taskIdOrKey)
	{

		return null;
	}

	@Override
	public boolean hasTaskChanged(TaskRepository taskRepository, ITask task, TaskData taskData)
	{

		return false;
	}

	@Override
	public IStatus performQuery(TaskRepository repository, IRepositoryQuery query, TaskDataCollector collector,
			ISynchronizationSession session, IProgressMonitor monitor)
	{
		
		TaskAttributeMapper mapper = taskDataHandler.getAttributeMapper(repository);
		
		try
		{
			IRedmineClient client = getClientManager().getClient(repository);
			Project p = client.getProjects().get(0);
			List<Issue> issues = client.getIssues(p);
			
			for(Issue i :issues )
			{
				TaskData taskData = new TaskData(taskDataHandler.getAttributeMapper(repository),
						RedmineRepositoryConnectorConstants.CONNECTOR_KIND, repository.getRepositoryUrl(), i.getId() + ""); 
				
				taskData.setPartial(true);
				
				collector.accept(taskData);				
			}
		}
		catch (RedmineException e)
		{
			return new RepositoryStatus(RepositoryStatus.ERROR_NETWORK, RedmineCorePlugin.PLUGIN_ID, 0, "Error performing query.",e);
		}
		
		
				
		return RepositoryStatus.OK_STATUS;
	}

	@Override
	public void updateRepositoryConfiguration(TaskRepository taskRepository, IProgressMonitor monitor)
			throws CoreException
	{
		
	}

	@Override
	public void updateTaskFromTaskData(TaskRepository taskRepository, ITask task, TaskData taskData)
	{

	}

	@Override
	public RepositoryInfo validateRepository(TaskRepository repository, IProgressMonitor monitor) throws CoreException
	{
		RepositoryInfo info = new RepositoryInfo(new RepositoryVersion("1"));		
		
		try
		{
			clientManager.validate(repository);
		}
		catch (RedmineException e)
		{
			throw new CoreException(new Status(IStatus.ERROR, RedmineCorePlugin.PLUGIN_ID, "Error connecting to repository",e));
		}
		
		return info;
	}
	
	

}
