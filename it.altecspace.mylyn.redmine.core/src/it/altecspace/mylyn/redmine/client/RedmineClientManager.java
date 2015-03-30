package it.altecspace.mylyn.redmine.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.spi.XmlWriter;
import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.xmlrpc.serializer.XmlWriterFactory;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.tasks.core.TaskRepository;

import com.taskadapter.redmineapi.RedmineException;

public class RedmineClientManager implements IRedmineClientManager
{

	private final IPath stateLocationPath;

	public RedmineClientManager(IPath stateLocationPath)
	{
		this.stateLocationPath = stateLocationPath;
		
	}
	
	Map<String,RedmineClient> clientsByUrl = new HashMap<String, RedmineClient>();
	
	@Override
	public IRedmineClient getClient(TaskRepository repository) throws RedmineException
	{
		RedmineClient client = clientsByUrl.get(repository.getUrl());
		
		if(client!=null)
		{
			return client;
		}		
		
		if(configurationFileExists(repository.getUrl()))
		{
			CachedRepositoryConfiguration configuration = loadConfigurationFromCache(repository.getUrl());
			client = createClientFromTaskRepository(repository, configuration);
		}
		else
		{
			CachedRepositoryConfiguration configuration = new CachedRepositoryConfiguration();
			client = createClientFromTaskRepository(repository, configuration);
			client.loadConfiguration();
			saveConfigurationToCache(repository.getUrl(),configuration);
		}

		clientsByUrl.put(repository.getUrl(), client);
		
		return client;
	}
	
	@Override
	public void validateConnection(TaskRepository repository) throws RedmineException
	{	
		RedmineClient client = createClientFromTaskRepository(repository, new CachedRepositoryConfiguration());
		
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
	
	private RedmineClient createClientFromTaskRepository(TaskRepository repository, CachedRepositoryConfiguration configuration)
	{
		String url = repository.getUrl();	
		
		AuthenticationCredentials credentials = repository.getCredentials(AuthenticationType.REPOSITORY);
		
		String username = credentials.getUserName();
		String password = credentials.getPassword();
		
		return new RedmineClient(url, username, password, configuration);
	}

	
	private boolean configurationFileExists(String repositoryUrl)
	{
		String encodedFilename = null; 
		
		try
		{
			encodedFilename = URLEncoder.encode(repositoryUrl,"UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			return false;
		}
		
		File destinationFile = stateLocationPath.append(encodedFilename).toFile();
		
		return destinationFile.exists();
	}
	
	private CachedRepositoryConfiguration loadConfigurationFromCache(String repositoryUrl) throws RedmineException
	{
		File configurationFile = getConfigurationFileForUrl(repositoryUrl);
		
		
		BufferedReader reader=null;

		try
		{
			reader = new BufferedReader(new FileReader(configurationFile));
		}
		catch (IOException e)
		{
			throw new RedmineException("Failed to open configuration file",e);
		}

		try
		{					
			JAXBContext context = JAXBContext.newInstance(CachedRepositoryConfiguration.class);
			
			Unmarshaller u = context.createUnmarshaller();
			
			return (CachedRepositoryConfiguration)u.unmarshal(reader);
		}
		catch (JAXBException e)
		{
			throw new RedmineException("Failed to serialize configuration to configuration file",e);
		}
		finally
		{
			try
			{
				reader.close();
			}
			catch (IOException e)
			{
			
			}
		}
	}
	
	private void saveConfigurationToCache(String repositoryUrl, CachedRepositoryConfiguration configuration) throws RedmineException 
	{
		File configurationFile = getConfigurationFileForUrl(repositoryUrl);
		
		
		BufferedWriter writer=null;

		try
		{
			writer = new BufferedWriter(new FileWriter(configurationFile));
		}
		catch (IOException e)
		{
			throw new RedmineException("Failed to open configuration file",e);
		}
		
		try
		{					
			JAXBContext context = JAXBContext.newInstance(CachedRepositoryConfiguration.class);
			
			Marshaller m = context.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			m.marshal(configuration, writer);			
		}
		catch (JAXBException e)
		{
			throw new RedmineException("Failed to serialize configuration to configuration file",e);
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch (IOException e)
			{
			
			}
		}
	}

	private File getConfigurationFileForUrl(String repositoryUrl) throws RedmineException
	{
		String encodedFilename = null; 
		
		try
		{
			encodedFilename = URLEncoder.encode(repositoryUrl,"UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			throw new RedmineException("Failed to encode repository url as filename",e);
		}
		
		File destinationFile = stateLocationPath.append(encodedFilename).toFile();
		return destinationFile;
	}
}
