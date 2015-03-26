package it.altecspace.mylyn.redmine.core;

import it.altecspace.mylyn.redmine.client.Configuration;
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
import com.taskadapter.redmineapi.bean.Version;

public class RedmineTaskAttributeMapper extends TaskAttributeMapper
{
	private final Configuration configuration;

	public RedmineTaskAttributeMapper(TaskRepository taskRepository, Configuration configuration)
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
				return getOptionsForVersion();
			default:
				return super.getOptions(attribute);
		}
		
		
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

	private Map<String, String> getOptionsForVersion()
	{
		Map<String,String> options = new HashMap<String, String>();
		
		for(Version v : configuration.getVersions())
		{
			options.put(v.getName(), v.getName());
		}
		
		return options;
	}

	private Map<String, String> getOptionsForPriority()
	{
		Map<String,String> options = new HashMap<String, String>();
		
		for(IssuePriority p : configuration.getPriorities())
		{
			options.put(p.getName(),p.getName());
		}
		
		return options;
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
