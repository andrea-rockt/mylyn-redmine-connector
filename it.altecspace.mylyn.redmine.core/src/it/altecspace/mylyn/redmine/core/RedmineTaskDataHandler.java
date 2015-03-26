package it.altecspace.mylyn.redmine.core;

import it.altecspace.mylyn.redmine.client.IRedmineClientManager;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.tasks.core.IRepositoryPerson;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.RepositoryResponse;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.AbstractTaskDataHandler;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;
import org.eclipse.mylyn.tasks.core.data.TaskData;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.User;

public class RedmineTaskDataHandler extends AbstractTaskDataHandler
{

	private final RedmineRepositoryConnector connector;
	
	public RedmineTaskDataHandler(RedmineRepositoryConnector connector)
	{
		this.connector = connector;
		
	}
	
	@Override
	public RepositoryResponse postTaskData(TaskRepository repository, TaskData taskData,
			Set<TaskAttribute> oldAttributes, IProgressMonitor monitor) throws CoreException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public boolean initializeTaskData(TaskRepository repository, TaskData data, ITaskMapping initializationData,
			IProgressMonitor monitor) throws CoreException
	{
		return false;
	}

	@Override
	public TaskAttributeMapper getAttributeMapper(TaskRepository repository)
	{
		try
		{
			return new RedmineTaskAttributeMapper(repository, connector.getClientManager().getClient(repository).getConfiguration());
		}
		catch (RedmineException e)
		{
			return null;
		}
	}
	
	
	public TaskData createTaskDataFromIssue(TaskRepository repository, Issue issue )
	{
		TaskData taskData = new TaskData(getAttributeMapper(repository), RedmineRepositoryConnectorConstants.CONNECTOR_KIND, repository.getRepositoryUrl(), issue.getId().toString());
		
		createAttributes(taskData);
		
		updateTaskDataFromIssue(repository,taskData,issue);
		
		return taskData;
	}

	private void createAttributes(TaskData taskData)
	{
		createDefaultAttributes(taskData);
	}

	private void createDefaultAttributes(TaskData taskData)
	{
		createAttribute(taskData, RedmineAttribute.SUMMARY);
		createAttribute(taskData, RedmineAttribute.REPORTER);
		createAttribute(taskData, RedmineAttribute.DESCRIPTION);
		createAttribute(taskData, RedmineAttribute.ASSIGNED_TO);
		createAttribute(taskData, RedmineAttribute.DATE_SUBMITTED);
		createAttribute(taskData, RedmineAttribute.DATE_UPDATED);
		createAttribute(taskData, RedmineAttribute.DATE_DUE);
		createAttribute(taskData, RedmineAttribute.PROJECT);
		createAttribute(taskData, RedmineAttribute.PRIORITY);
		createAttribute(taskData, RedmineAttribute.VERSION);
		createAttribute(taskData, RedmineAttribute.STATUS);
	}
	
	private void createAttribute(TaskData taskData, RedmineAttribute attribute)
	{
		TaskAttribute attr = taskData.getRoot().createAttribute(attribute.getKey());
		attr.getMetaData().setType(attribute.getType());
		attr.getMetaData().setKind(attribute.getKind());
		attr.getMetaData().setLabel(attribute.toString());
		attr.getMetaData().setReadOnly(attribute.getFlags().contains(RedmineAttribute.Flag.READ_ONLY));
		attr.getMetaData().setRequired(attribute.getFlags().contains(RedmineAttribute.Flag.REQUIRED));
	}
	
	private TaskAttribute getAttribute(TaskData taskData, RedmineAttribute attribute)
	{
		return taskData.getRoot().getAttribute(attribute.getKey());
	}
	
	private void updateTaskDataFromIssue(TaskRepository repository,TaskData taskData,Issue issue)
	{
		TaskAttributeMapper mapper = taskData.getAttributeMapper();
		
		TaskAttribute summary       = getAttribute(taskData, RedmineAttribute.SUMMARY);
		TaskAttribute reporter      = getAttribute(taskData, RedmineAttribute.REPORTER);
		TaskAttribute description   = getAttribute(taskData, RedmineAttribute.DESCRIPTION);
		TaskAttribute assignedTo    = getAttribute(taskData, RedmineAttribute.ASSIGNED_TO);
		TaskAttribute dateSubmitted = getAttribute(taskData, RedmineAttribute.DATE_SUBMITTED);
		TaskAttribute dateUpdated   = getAttribute(taskData, RedmineAttribute.DATE_UPDATED);
		TaskAttribute dateDue       = getAttribute(taskData, RedmineAttribute.DATE_DUE);
		TaskAttribute project       = getAttribute(taskData, RedmineAttribute.PROJECT);
		TaskAttribute priority      = getAttribute(taskData, RedmineAttribute.PRIORITY);
		TaskAttribute version       = getAttribute(taskData, RedmineAttribute.VERSION);
		TaskAttribute status        = getAttribute(taskData, RedmineAttribute.STATUS);
		
		
		
		mapper.setRepositoryPerson(reporter,createPersonFromUser(repository, issue.getAuthor()) );
		mapper.setValue(summary , issue.getSubject());
		mapper.setValue(description , issue.getDescription());
		mapper.setRepositoryPerson(assignedTo, createPersonFromUser(repository, issue.getAssignee()));
		mapper.setDateValue(dateSubmitted, issue.getCreatedOn());
		mapper.setDateValue(dateUpdated, issue.getUpdatedOn());
		mapper.setDateValue(dateDue, issue.getDueDate());
		mapper.setValue(project, issue.getProject().getName());
//		mapper.setValue(priority, issue.getPriorityText());
//		mapper.setValue(version, issue.getTargetVersion().getName());
//		mapper.setValue(status, issue.getStatusName());
	}
	
	private IRepositoryPerson createPersonFromUser(TaskRepository repository, User user)
	{
		IRepositoryPerson person   = repository.createPerson(user.getId().toString());
		person.setName(user.getFullName());
		return person;
		
	}
}
