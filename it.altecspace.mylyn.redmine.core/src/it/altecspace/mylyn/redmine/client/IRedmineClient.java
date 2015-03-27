package it.altecspace.mylyn.redmine.client;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.User;

public interface IRedmineClient
{
	public User getCurrentUser();
	
	public CachedRepositoryConfiguration getCachedRepositoryConfiguration();

	public Iterable<Issue> getIssuesByProject(Project project) throws RedmineException;
//	
	public Issue getIssueById(Integer id) throws RedmineException;
//
//	public List<Project> getProjects() throws RedmineException;
//
//	public Project getProjectByKey(String projectKey) throws RedmineException;
	
	public abstract void shutdown();
}
