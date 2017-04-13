package life.memy.identity;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.swagger.model.User;
import life.memy.exception.RepositoryException;

public class IdentityServerFacadeImpl implements IdentityServerFacade {
	final static Logger logger = Logger.getLogger(IdentityServerFacade.class);
	
	private IdentityClient identityClient = new IdentityClientImpl();

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
	public String findUseridByUsername(String userName) {
		JSONParser parser = new JSONParser();
		String jsonData = null;
		JSONObject jobject = null;
		jsonData = identityClient.findUseridByUsername(userName);
		
		try {
			jobject = (JSONObject) parser.parse(jsonData);
		} catch (ParseException e) {
			throw new RepositoryException(e);
		}
		JSONArray resources = (JSONArray) jobject.get("Resources");
		
		if (resources != null) {
			String id = null;
			if (noErrors(resources)) {
				JSONObject obj = (JSONObject) resources.get(0);
				id = (String) obj.get("id");
			}
			return id != null ? id : jsonData;
		} else {
			return jsonData;
		}
	}
	
	@Override
	public String findUserByUserid(String userid) {
		JSONParser parser = new JSONParser();
		String jsonData = null;
		JSONObject jobject = null;
		jsonData = identityClient.findUserByUserid(userid);
		try {
			jobject = (JSONObject) parser.parse(jsonData);
		} catch (ParseException e) {
			throw new RepositoryException(e);
		}
		JSONArray resources = (JSONArray) jobject.get("Resources");
		
		if (resources != null) {
			String id = null;
			if (noErrors(resources)) {
				JSONObject obj = (JSONObject) resources.get(0);
				id = (String) obj.get("id");
			}
			return id != null ? id : jsonData;
		} else {
			return jsonData;
		}
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see life.memy.data.IdentityServerFacade#createUser(java.lang.String)
	 */
	@Override
	public String createUser(User user) {
		JSONParser parser = new JSONParser();
		String jsonData = null;
		JSONObject jobject = null;
		jsonData = identityClient.createUser(user);

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

	
	public void setClient(IdentityClient identityClient) {
		this.identityClient = identityClient;
	}

	public static void main(String[] args) {
		IdentityServerFacadeImpl fac = new IdentityServerFacadeImpl();

//		String response = fac.createUser("per9634");
//	    System.out.println("User created: " + response);

//		String user = fac.findUseridByUsername("per1234");
//		System.out.println("User: " + user);
		
		String user = fac.findUserByUserid("3e076302-4c40-4699-8ef4-ed485b1c32c1");
		System.out.println("User: " + user);
		
	}
}
