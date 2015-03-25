package it.altecspace.mylyn.redmine.client;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.tasks.core.IRepositoryListener;
import org.eclipse.mylyn.tasks.core.TaskRepository;

import com.taskadapter.redmineapi.RedmineException;

public interface IRedmineClientManager
{	
	public IRedmineClient getClient(TaskRepository repository) throws RedmineException;

	public void validate(TaskRepository repository) throws RedmineException;
}
