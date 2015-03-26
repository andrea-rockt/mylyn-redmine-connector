package it.altecspace.mylyn.redmine.client;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.tasks.core.TaskRepository;

import com.taskadapter.redmineapi.RedmineException;

public class RedmineClientManager implements IRedmineClientManager
{
	
	Map<String,IRedmineClient> clientsByUrl = new HashMap<String, IRedmineClient>();

	
	@Override
	public IRedmineClient getClient(TaskRepository repository) throws RedmineException
	{
		IRedmineClient client = clientsByUrl.get(repository.getUrl());
		
		if(client == null)
		{
			client = createClientFromTaskRepository(repository);
		}
		
		
		client.connect();
		
		return client;
	}
	
	@Override
	public void validate(TaskRepository repository) throws RedmineException
	{	
		IRedmineClient client = createClientFromTaskRepository(repository);
		
		try
		{
			client.connect();
		}
		catch (RedmineException e)
		{
			throw e;
		}
		finally
		{
			client.shutdown();
		}
	}
	
	private IRedmineClient createClientFromTaskRepository(TaskRepository repository)
	{
		return createClientFromTaskRepository(repository, new Configuration());
	}
	
	private IRedmineClient createClientFromTaskRepository(TaskRepository repository, Configuration configuration)
	{
		String url = repository.getUrl();
		
		
		
		AuthenticationCredentials credentials = repository.getCredentials(AuthenticationType.REPOSITORY);
		
		String username = credentials.getUserName();
		String password = credentials.getPassword();
		
		return new RedmineClient(url, username, password, configuration);
	}

}
