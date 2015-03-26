package it.altecspace.mylyn.redmine.core;

import org.eclipse.mylyn.tasks.core.data.TaskData;
import org.eclipse.mylyn.tasks.core.data.TaskMapper;



public class RedmineTaskMapper extends TaskMapper
{
	public RedmineTaskMapper(TaskData taskData, boolean createNonExistingAttributes)
	{
		super(taskData, createNonExistingAttributes);
	}

	public RedmineTaskMapper(TaskData taskData)
	{
		super(taskData);
	}	
	
	
}
