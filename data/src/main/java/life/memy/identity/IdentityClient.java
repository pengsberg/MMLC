package life.memy.identity;

public interface IdentityClient {
	
	public String getUserId(String endpoint);
	
	public String createUser(String userName);
	
	public String getUsers();
	
}
