package it.altecspace.mylyn.redmine.client;

import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

public interface IRedmineClient
{
	public Configuration getConfiguration();

	public List<Issue> getIssues() throws RedmineException;
	
	public List<Issue> getIssues(Project project) throws RedmineException;

	public List<Project> getProjects() throws RedmineException;

	public abstract void shutdown();

	public abstract void connect() throws RedmineException;
}
