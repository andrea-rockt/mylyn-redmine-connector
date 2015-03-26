package it.altecspace.mylyn.redmine.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

public class RedmineClient implements IRedmineClient
{

	final RedmineManager manager;
	final Configuration  configuration;
	
	public RedmineClient(String url, String username, String password, Configuration configuration)
	{
		this.configuration = configuration;
				
		manager = RedmineManagerFactory.createWithUserAuthNoSslCheck(url, username, password); 
		
		
	}

	@Override
	public Configuration getConfiguration()
	{
		return configuration;
	}
	
	
	@Override
	public void connect() throws RedmineException
	{
		manager.getUserManager().getCurrentUser();
		
		if(configuration.isEmpty())
		{
			//configuration.getPriorities().addAll(manager.getIssueManager().getIssuePriorities());
			configuration.getProjects().addAll(manager.getProjectManager().getProjects());
			configuration.getStatuses().addAll(manager.getIssueManager().getStatuses());
			configuration.getTrackers().addAll(manager.getIssueManager().getTrackers());
			configuration.getUsers().addAll(manager.getUserManager().getUsers());
		}
	}
	
	@Override
	public void shutdown()
	{
		manager.shutdown();
	}

	@Override
	public List<Issue> getIssues() throws RedmineException
	{
		return manager.getIssueManager().getIssues(Collections.<String, String>emptyMap());
	}

	public List<Project> getProjects() throws RedmineException
	{
		if(configuration.getProjects().isEmpty())
		{
			configuration.getProjects().addAll(manager.getProjectManager().getProjects());
		}
		
		return configuration.getProjects();
	}

	@Override
	public List<Issue> getIssues(Project project) throws RedmineException
	{
		return manager.getIssueManager().getIssues(project.getIdentifier(), null);
	}

	@Override
	public Project getProjectByKey(String projectKey) throws RedmineException
	{
		return manager.getProjectManager().getProjectByKey(projectKey);
	}

	@Override
	public Issue getIssueById(Integer id) throws RedmineException
	{
		return manager.getIssueManager().getIssueById(id);
	}


}
