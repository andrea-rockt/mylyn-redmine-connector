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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.taskadapter.redmineapi.bean.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CachedRepositoryConfiguration implements Serializable
{
	@XmlAccessorType(XmlAccessType.FIELD)
	private static class Versions
	{
		@XmlElementWrapper(name="versions")
		@XmlElement(name="version")
		private List<Version> versions = new ArrayList<Version>();
		
		public Iterable<Version> get()
		{
			return versions;
		}
		
		public void addAll(Collection<Version> versions)
		{
			this.versions.addAll(versions);
		}
			
		public void add(Version version)
		{
			addAll(Arrays.asList(version));
		}
	}
	
	private static final long serialVersionUID = -3367140793853904462L;
	
	@XmlElementWrapper(name="custom-fields")
	@XmlElement(name="custom-field")
	private List<CustomFieldDefinition> customFields        = new ArrayList<CustomFieldDefinition>();
	@XmlElementWrapper(name="categories")
	@XmlElement(name="category")
	private List<IssueCategory>         categories          = new ArrayList<IssueCategory>();
	@XmlElementWrapper(name="priorities")
	@XmlElement(name="priority")
	private List<IssuePriority>         priorities          = new ArrayList<IssuePriority>();
	@XmlElementWrapper(name="statuses")
	@XmlElement(name="status")
	private List<IssueStatus>           statuses            = new ArrayList<IssueStatus>();
	@XmlElementWrapper(name="projects")
	@XmlElement(name="project")
	private Map<Integer,Project>        projects            = new HashMap<Integer,Project>();
	@XmlElementWrapper(name="trackers")
	@XmlElement(name="tracker")
	private List<Tracker>               trackers            = new ArrayList<Tracker>();
	@XmlElementWrapper(name="priorities")
	@XmlElement(name="user")
	private Map<Integer,User>  		 users               = new HashMap<Integer, User>();
	@XmlElementWrapper(name="versions-by-project")
	@XmlElement(name="version-by-project")
	private Map<Project,Versions>  versionsByProject   = new HashMap<Project,Versions>();
	@XmlElement(name="current-user")
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
		Versions versionsList = versionsByProject.get(p);
		
		if(versionsList==null)
		{
			versionsList = new Versions();
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
		return versionsByProject.get(p).get();
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
