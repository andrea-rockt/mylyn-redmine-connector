package it.altecspace.mylyn.redmine.core;

import it.altecspace.mylyn.redmine.client.CachedRepositoryConfiguration;
import it.altecspace.mylyn.redmine.client.IRedmineClientManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttributeMapper;

import com.taskadapter.redmineapi.bean.IssuePriority;
import com.taskadapter.redmineapi.bean.IssueStatus;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.bean.Version;

public class RedmineTaskAttributeMapper extends TaskAttributeMapper
{
	private final CachedRepositoryConfiguration configuration;

	public RedmineTaskAttributeMapper(TaskRepository taskRepository, CachedRepositoryConfiguration configuration)
	{
		super(taskRepository);
		
		this.configuration = configuration;
		
	}

	@Override
	public Map<String, String> getOptions(TaskAttribute attribute)
	{
		switch (RedmineAttribute.forKey(attribute.getId()))
		{
			case PROJECT:
				return getOptionsForProject();
			case PRIORITY:
				return getOptionsForPriority();
			case STATUS:
				return getOptionsForStatus();
			case VERSION:
				return getOptionsForVersion(attribute.getParentAttribute());
			case REPORTER:
			case ASSIGNED_TO:
				return getOptionsForUser();
			default:
				return super.getOptions(attribute);
		}
		
		
	}
	
	private Map<String, String> getOptionsForUser()
	{
		Map<String,String> options = new HashMap<String, String>();
		
		for(User u : configuration.getUsers())
		{
			options.put(u.getLogin(), u.getLogin());
		}
		
		return options;
	}

	private Map<String, String> getOptionsForStatus()
	{
		Map<String,String> options = new HashMap<String, String>();
		
		for(IssueStatus s : configuration.getStatuses())
		{
			options.put(s.getName(), s.getName());
		}
		
		return options;
	}

	private Map<String, String> getOptionsForVersion(TaskAttribute rootAttribute)
	{
		Map<String,String> options = new HashMap<String, String>();	
		
		String projectName = rootAttribute.getAttribute(RedmineAttribute.PROJECT.getKey()).getValue();
		
		for(Project p : configuration.getProjects())
		{
			if(p.getName().equals(projectName))
			{
				for(Version v: configuration.getProjectVersions(p))
				{
					options.put(v.getName(), v.getName());
				}
				
				return options;
			}
		}
		
		return options;
	}

	private Map<String, String> getOptionsForPriority()
	{
		
		return Collections.<String,String>emptyMap();
	}

	private Map<String, String> getOptionsForProject()
	{
		Map<String,String> options = new HashMap<String, String>();
		
		for(Project p : configuration.getProjects())
		{
			options.put(p.getName(),p.getName());
		}
		
		return options;
	}

}
