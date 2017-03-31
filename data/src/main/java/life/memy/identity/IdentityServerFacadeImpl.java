package life.memy.identity;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import life.memy.data.DatabaseClass;
import life.memy.exception.RepositoryException;

public class IdentityServerFacadeImpl implements IdentityServerFacade {
	final static Logger logger = Logger.getLogger(IdentityServerFacade.class);
	
	private IdentityClient identityClient = new IdentityClientImpl();
	private final String URL = this.getUrl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see life.memy.data.IdentityServerFacade#getUsers()
	 */
	@Override
	public String getUsers() {
		String response = identityClient.getUsers();
		return response;
	}

	
	
	@Override
	public String getUserId(String userName) {
		JSONParser parser = new JSONParser();
		String jsonData = null;
		JSONObject jobject = null;
		jsonData = identityClient.getUserId(this.getUserFilterEndpoint(userName));
		
		try {
			jobject = (JSONObject) parser.parse(jsonData);
		} catch (ParseException e) {
			throw new RepositoryException(e);
		}
		JSONArray resources = (JSONArray) jobject.get("Resources");
		String id = null;
		if (noErrors(resources)) {
			JSONObject obj = (JSONObject) resources.get(0);
			id = (String) obj.get("id");
		}
		return id != null ? id : jsonData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see life.memy.data.IdentityServerFacade#createUser(java.lang.String)
	 */
	@Override
	public String createUser(String userName) {
		JSONParser parser = new JSONParser();
		String jsonData = null;
		JSONObject jobject = null;
		jsonData = identityClient.createUser(userName);

		try {
			jobject = (JSONObject) parser.parse(jsonData);
		} catch (ParseException e) {
			throw new RepositoryException(e);
		}
		String id = (String) jobject.get("id");
		if (id != null) {
			return id;
		} else {
			throw new RepositoryException(jsonData);
		}
	}

	
	private boolean noErrors(JSONArray resources) {
		return resources == null ? false : true;
	}

	
	private String getUrl() {
		StringBuilder builder = new StringBuilder("https://").append(DatabaseClass.getProperties().getProperty(DatabaseClass.IDENTITY_NODE))
				.append("/wso2/scim/Users");
		return builder.toString();
	}

	public String getUserFilterEndpoint(String userName) {
		String endpoint = URL + "?filter=userName%20Eq%20%22" + userName + "%22";
		return endpoint;

	}
	
	public void setClient(IdentityClient identityClient) {
		this.identityClient = identityClient;
	}

	public static void main(String[] args) {
		IdentityServerFacadeImpl fac = new IdentityServerFacadeImpl();

//		String response = fac.createUser("per9634");
//	    System.out.println("User created: " + response);

		String user = fac.getUserId("per1234");
		System.out.println("User: " + user);
	}

}
