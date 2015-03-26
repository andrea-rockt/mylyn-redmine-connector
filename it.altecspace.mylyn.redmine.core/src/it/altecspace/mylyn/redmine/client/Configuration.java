package it.altecspace.mylyn.redmine.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.taskadapter.redmineapi.bean.*;

public class Configuration implements Serializable
{
	private static final long serialVersionUID = -3367140793853904462L;
	
	private List<CustomFieldDefinition> customFields = new ArrayList<CustomFieldDefinition>();
	private List<IssueCategory>         categories   = new ArrayList<IssueCategory>();
	private List<IssuePriority>         priorities   = new ArrayList<IssuePriority>();
	private List<IssueStatus>           statuses     = new ArrayList<IssueStatus>();
	private List<Project>               projects     = new ArrayList<Project>();
	private List<Tracker>               trackers     = new ArrayList<Tracker>();
	private List<User>                  users        = new ArrayList<User>();
	private List<Version>               versions     = new ArrayList<Version>();
	
	public List<CustomFieldDefinition> getCustomFields()
	{
		return customFields;
	}
	
	public List<IssueCategory> getCategories()
	{
		return categories;
	}
	
	public List<IssuePriority> getPriorities()
	{
		return priorities;
	}
	
	public List<IssueStatus> getStatuses()
	{
		return statuses;
	}
	
	public List<Project> getProjects()
	{
		return projects;
	}
	
	public List<Tracker> getTrackers()
	{
		return trackers;
	}
	
	public List<User> getUsers()
	{
		return users;
	}
	
	public List<Version> getVersions()
	{
		return versions;
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
				&&   versions.size()==0;
	}
}
