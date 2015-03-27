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
	
	Map<String,RedmineClient> clientsByUrl = new HashMap<String, RedmineClient>();

	
	@Override
	public IRedmineClient getClient(TaskRepository repository) throws RedmineException
	{
		RedmineClient client = clientsByUrl.get(repository.getUrl());
		
		if(client == null)
		{
			client = createClientFromTaskRepository(repository);
			client.loadConfiguration();
			clientsByUrl.put(repository.getUrl(), client);
		}
				
		return client;
	}
	
	@Override
	public void validateConnection(TaskRepository repository) throws RedmineException
	{	
		RedmineClient client = createClientFromTaskRepository(repository);
		
		try
		{
			client.validateConnection();
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
	
	private RedmineClient createClientFromTaskRepository(TaskRepository repository)
	{
		return createClientFromTaskRepository(repository, new CachedRepositoryConfiguration());
	}
	
	private RedmineClient createClientFromTaskRepository(TaskRepository repository, CachedRepositoryConfiguration configuration)
	{
		String url = repository.getUrl();	
		
		AuthenticationCredentials credentials = repository.getCredentials(AuthenticationType.REPOSITORY);
		
		String username = credentials.getUserName();
		String password = credentials.getPassword();
		
		return new RedmineClient(url, username, password, configuration);
	}

}
