package life.memy.identity.scim;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.User;
import life.memy.exception.RepositoryException;

public class UserFactory {
	
	public static String getUserJson(User user) {
		ObjectMapper objectMapper = new ObjectMapper();
		ScimUser scimUser = createUser(user);
		String json = null;
		try {
			json = objectMapper.writeValueAsString(scimUser);
		} catch (JsonProcessingException e) {
			throw new RepositoryException(e);
		}
		return json;
	}
	
	private static ScimUser createUser(User user) {
		Name name = new Name();
		name.setFamilyName(user.getLastname());
		name.setGivenName(user.getFirstname());
		
		Emails[] emails = new Emails[1];
		Emails email = new Emails();
		email.setValue(user.getEmail());
		email.setPrimary("true");
		email.setType("home");
		emails[0] = email;
		
		ScimUser scimUser = new ScimUser();
		scimUser.setUserName(user.getUsername());
		scimUser.setPassword(user.getPassword());
		scimUser.setEmails(emails);
		scimUser.setName(name);
		
		return scimUser;
	}
	
	private String getUser(String userName) {
		return "{\"schemas\":[],\"name\":{\"familyName\":\"gunasinghe\",\"givenName\":\"hasinitg\"},\"userName\""
				+ ":\"" + userName
				+ "\",\"password\":\"hasinitg\",\"emails\":[{\"primary\":true,\"value\":\"hasini_home.com\""
				+ ",\"type\":\"home\"},{\"value\":\"hasini_work.com\",\"type\":\"work\"}]}";
	}

}
