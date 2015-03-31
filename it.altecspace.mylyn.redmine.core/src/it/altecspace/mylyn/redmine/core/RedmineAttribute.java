package it.altecspace.mylyn.redmine.core;

import java.util.EnumSet;
import java.util.Set;

import org.eclipse.mylyn.tasks.core.data.TaskAttribute;

public enum RedmineAttribute
{
	SUMMARY("Summary",
			 TaskAttribute.SUMMARY,
			 TaskAttribute.TYPE_SHORT_TEXT,
			 Flag.REQUIRED,Flag.READ_ONLY),
	
	REPORTER("Reporter",
			  TaskAttribute.USER_REPORTER,
			  TaskAttribute.TYPE_PERSON,
			  Flag.READ_ONLY),
			  
	DESCRIPTION("Description",
				  TaskAttribute.DESCRIPTION,
				  TaskAttribute.TYPE_LONG_RICH_TEXT,
				  Flag.REQUIRED,
				  Flag.READ_ONLY),
				  
	ASSIGNED_TO("Assigned to:",
				  TaskAttribute.USER_ASSIGNED,
				  TaskAttribute.TYPE_PERSON,
				  Flag.REQUIRED),
				  
	DATE_SUBMITTED ("Submitted",
                     TaskAttribute.DATE_CREATION,
                     TaskAttribute.TYPE_DATE,
                     Flag.READ_ONLY),
                     
	DATE_UPDATED  ("Updated on",
			         TaskAttribute.DATE_MODIFICATION,
			         TaskAttribute.TYPE_DATE,
			         Flag.READ_ONLY),
			       
	//DATE_START("Start date",  TaskAttribute.da, RedmineApiIssueProperty.START_DATE, TaskAttribute.TYPE_DATE, Flag.HIDDEN),
	DATE_DUE("Due date",
			  TaskAttribute.DATE_DUE,
			  TaskAttribute.TYPE_DATE,
			  Flag.READ_ONLY),
			  
	PROJECT("Project",
			 TaskAttribute.PRODUCT,
			 TaskAttribute.TYPE_SINGLE_SELECT,
			 Flag.REQUIRED,
			 Flag.READ_ONLY),
			 
	PRIORITY("Priority", 
			  TaskAttribute.PRIORITY, 
			  TaskAttribute.TYPE_SINGLE_SELECT,
			  Flag.REQUIRED,
			  Flag.READ_ONLY),

			  
	VERSION("Target version",
			 TaskAttribute.VERSION,
			 TaskAttribute.TYPE_SINGLE_SELECT,
			 Flag.READ_ONLY),		 

	STATUS("Status",
			TaskAttribute.STATUS,
			TaskAttribute.TYPE_SINGLE_SELECT,
			Flag.REQUIRED,
			Flag.READ_ONLY);
	
	
	public static enum Flag
	{
		READ_ONLY,HIDDEN, REQUIRED,CUSTOM;
	}
	
	private final String prettyName;
	private final String key;

	private final String type;
	private final Set<Flag> flags;

	private RedmineAttribute(String prettyName, String mylynKey, String type, Flag... flags)
	{
		this.prettyName = prettyName;
		this.key = mylynKey;
		this.type = type;
		this.flags = flags.length==0 || flags[0]==null ? EnumSet.noneOf(Flag.class) : EnumSet.of(flags [0], flags);
	}
		
	@Override
	public String toString()
	{
		return prettyName;
	}

	public String getKey()
	{
		return key;
	}

	public String getType()
	{
		return type;
	}


	public Set<Flag> getFlags()
	{
		return flags;
	}
	
	public String getKind()
	{
		switch (this)
		{
		case REPORTER:
		case ASSIGNED_TO:
			return TaskAttribute.KIND_PEOPLE;
		case DESCRIPTION:
			return TaskAttribute.KIND_DESCRIPTION;
		default:
			return TaskAttribute.KIND_DEFAULT;
			
			
		}
	}
	
	public static RedmineAttribute forKey(String taskKey)
	{
		for(RedmineAttribute attribute : values())
		{
			if(attribute.getKey().equals(taskKey))
			{
				return attribute;
			}
		}
		
		return null;
	}
	
}
