package it.altecspace.mylyn.redmine.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taskadapter.redmineapi.bean.*;

public class CachedRepositoryConfiguration implements Serializable
{
	private static final long serialVersionUID = -3367140793853904462L;
	
	private List<CustomFieldDefinition> customFields        = new ArrayList<CustomFieldDefinition>();
	private List<IssueCategory>         categories          = new ArrayList<IssueCategory>();
	private List<IssuePriority>         priorities          = new ArrayList<IssuePriority>();
	private List<IssueStatus>           statuses            = new ArrayList<IssueStatus>();
	private Map<Integer,Project>        projects            = new HashMap<Integer,Project>();
	private List<Tracker>               trackers            = new ArrayList<Tracker>();
	private Map<Integer,User>  		 users               = new HashMap<Integer, User>();
	private Map<Project,List<Version>>  versionsByProject   = new HashMap<Project,List<Version>>();
	private User						 currentUser;
		
	public void addProject(Project project)
	{
		addProjects(Arrays.asList(project));
	}

	public void addProjects(Collection<Project> projects)
	{
		for(Project p : projects)
		{
			this.projects.put(p.getId(), p);
		}
	}

	public void addStatus(IssueStatus status)
	{
		addStatuses(Arrays.asList(status));
	}
	
	public void addStatuses(Collection<IssueStatus> statuses)
	{
		this.statuses.addAll(statuses);
	}
	public void addTracker(Tracker tracker)
	{
		addTrackers(Arrays.asList(tracker));
	}
	
	public void addTrackers(Collection<Tracker> trackers)
	{
		this.trackers.addAll(trackers);
	}
	
	public void addUser(User user)
	{
		addUsers(Arrays.asList(user));
	}
	
	public void addUsers(Collection<User> users)
	{
		for(User user: users)
		{
			this.users.put(user.getId(),user);
		}
		
	}
	
	
	public void addVersionsToProject(Project p, Collection<Version> versions)
	{
		List<Version> versionsList = versionsByProject.get(p);
		
		if(versionsList==null)
		{
			versionsList = new ArrayList<Version>();
			versionsByProject.put(p, versionsList);
		}
		
		versionsList.addAll(versions);
	}
	
	public void addVersionToProject(Project p, Version version)
	{
		addVersionsToProject(p,Arrays.asList(version));
	}
	
	public User getCurrentUser()
	{
		return currentUser;
	}
	
	public Project getProjectById(Integer id)
	{
		return projects.get(id);
	}
	
	public Iterable<Project> getProjects()
	{
		return projects.values();
	}
	
	public Iterable<Version> getProjectVersions(Project p)
	{
		return versionsByProject.get(p);
	}
	
	public Iterable<IssueStatus> getStatuses()
	{
		return statuses;
	}
	
	
	public Iterable<Tracker> getTrackers()
	{
		return trackers;
	}
	
	public User getUserById(Integer id)
	{
		return this.users.get(id);
	}
	
	public Iterable<User> getUsers()
	{
		return users.values();
	}
	
	public boolean isEmpty()
	{
		return  customFields.size()==0 
				&& categories.size()==0 
				&& priorities.size()==0 
				&&   statuses.size()==0 
				&&   projects.size()==0 
				&&   trackers.size()==0 
				&&      users.size()==0 
				&&   versionsByProject.size()==0;
	}
	
	
	
	public void setCurrentUser(User currentUser)
	{
		this.currentUser = currentUser;
	}
}