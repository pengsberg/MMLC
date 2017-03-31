package life.memy.identity;

public interface IdentityServerFacade {

	String getUsers();

	String getUserId(String userName);

	String createUser(String userName);
}