package it.altecspace.mylyn.redmine.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.taskadapter.redmineapi.Include;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Attachment;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Membership;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.bean.Version;

public class RedmineClient implements IRedmineClient
{

	final RedmineManager manager;
	final CachedRepositoryConfiguration  configuration;
	
	public RedmineClient(String url, String username, String password, CachedRepositoryConfiguration configuration)
	{
		this.configuration = configuration;
				
		manager = RedmineManagerFactory.createWithUserAuthNoSslCheck(url, username, password); 
		manager.setObjectsPerPage(Integer.MAX_VALUE);
	}

	@Override
	public CachedRepositoryConfiguration getCachedRepositoryConfiguration()
	{
		return configuration;
	}
	
	
	
	public void validateConnection() throws RedmineException
	{
		//This line will trigger an exception if
		//server is not reachable or if authentication
		//cannot be performed due to wrong credentials
		manager.getUserManager().getCurrentUser(); 
	}
	
	@Override
	public void shutdown()
	{
		manager.shutdown();
	}



	public Iterable<Project> getProjects() throws RedmineException
	{
		return configuration.getProjects();
	}

	@Override
	public Iterable<Issue> getIssuesByProject(Project project) throws RedmineException
	{
		List<Issue> issues = manager.getIssueManager().getIssues(project.getIdentifier(),null,Include.journals);
		
//		List<Issue> issuesWithJournals = new ArrayList<Issue>();
//		
//		for(Issue i : issues)
//		{
//			Issue issueWithJournals = manager.getIssueManager().getIssueById(i.getId(), Include.journals);
//			issuesWithJournals.add(issueWithJournals);
//		}
//		
//		return issuesWithJournals;
		
		return issues;
	}

	@Override
	public Issue getIssueById(Integer id) throws RedmineException
	{
		return manager.getIssueManager().getIssueById(id,Include.journals,Include.attachments);
	}

	public void loadConfiguration() throws RedmineException
	{
		User u = manager.getUserManager().getCurrentUser();
		
		configuration.setCurrentUser(u);
		
		if(configuration.isEmpty())
		{
			for(Membership m : u.getMemberships())
			{
				Project p = manager.getProjectManager().getProjectById(m.getProject().getId());
				
				configuration.addProject(p);
				
				List<Version> versions = manager.getProjectManager().getVersions(m.getProject().getId());
				
				configuration.addVersionsToProject(p, versions);
			}
			
			configuration.addStatuses(manager.getIssueManager().getStatuses());
			configuration.addTrackers(manager.getIssueManager().getTrackers());
			configuration.addUsers(manager.getUserManager().getUsers());		
		}
		
	}

	@Override
	public User getCurrentUser()
	{
		return this.configuration.getCurrentUser();
	}

	@Override
	public Attachment getAttachmentById(Integer id) throws RedmineException
	{
		return manager.getAttachmentManager().getAttachmentById(id);
	}

	@Override
	public InputStream downloadAttachment(Attachment attachment) throws RedmineException
	{
		byte[] content = manager.getAttachmentManager().downloadAttachmentContent(attachment);
		
		return new ByteArrayInputStream(content);
	}

	@Override
	public void uploadAttachment(Issue issue,String fileName, String contentType,String description, InputStream content) throws RedmineException, IOException
	{
		manager.getAttachmentManager().addAttachmentToIssue(issue.getId(),fileName,description,contentType,content);
	}


}
