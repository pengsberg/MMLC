package life.memy.identity.scim;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import life.memy.exception.RepositoryException;

public class UserFactory {
	
	public static String getUserJson(String userName) {
		ObjectMapper objectMapper = new ObjectMapper();
		ScimUser user = createUser(userName);
		String json = null;
		try {
			json = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			throw new RepositoryException(e);
		}
		return json;
	}
	
	private static ScimUser createUser(String userName) {
		Name name = new Name();
		name.setFamilyName(userName);
		name.setGivenName(userName);
		
		Emails[] emails = new Emails[2];
		Emails email = new Emails();
		email.setPrimary("true");
		email.setValue(userName + "@lala");
		email.setType("home");
		Emails email2 = new Emails();
		email2.setValue(userName + "work@lala");
		email2.setType("work");
		emails[0] = email;
		emails[1] = email2;
		
		ScimUser user = new ScimUser();
		user.setUserName(userName);
		user.setPassword("hasinitg");
		user.setEmails(emails);
		user.setName(name);
		
		return user;
	}
	
	private String getUser(String userName) {
		return "{\"schemas\":[],\"name\":{\"familyName\":\"gunasinghe\",\"givenName\":\"hasinitg\"},\"userName\""
				+ ":\"" + userName
				+ "\",\"password\":\"hasinitg\",\"emails\":[{\"primary\":true,\"value\":\"hasini_home.com\""
				+ ",\"type\":\"home\"},{\"value\":\"hasini_work.com\",\"type\":\"work\"}]}";
	}

}
