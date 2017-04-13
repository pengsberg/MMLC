package life.memy.identity;

import io.swagger.model.User;

public interface IdentityClient {
	
	public String findUseridByUsername(String username);
	
	public String findUserByUserid(String userid);
	
	public String createUser(User user);
	
	public String getUsers();
	
}
