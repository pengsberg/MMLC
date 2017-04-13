package life.memy.identity;

import io.swagger.model.User;

public interface IdentityServerFacade {

	String getUsers();

	String findUseridByUsername(String userName);
	
	String findUserByUserid(String userid);

	String createUser(User user);
}