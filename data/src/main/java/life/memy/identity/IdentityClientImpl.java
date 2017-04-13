package life.memy.identity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.apache.log4j.Logger;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import io.swagger.model.User;
import life.memy.data.DatabaseClass;
import life.memy.exception.RepositoryException;
import life.memy.identity.scim.UserFactory;

public class IdentityClientImpl implements IdentityClient {
	final static Logger logger = Logger.getLogger(IdentityClientImpl.class);
	
	private OkHttpClient client = new OkHttpClient().setHostnameVerifier(new NullHostNameVerifier());
	private final String URL = this.getUrl();
	private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private final String USER = "admin";
	private final String PW = "admin";
	
	@Override
	public String getUsers() {
		Request request = new Request.Builder()
				.url(URL)
				.addHeader("authorization", "Basic YWRtaW46YWRtaW4=")
				.build();
		Response response = null;
		String responseString = null;
		try {
			response = client.newCall(request).execute();
			responseString = response.body().string();

		} catch (IOException e) {
			throw new RepositoryException(e);
		}
		return responseString;
	}

	@Override
	public String createUser(User user) {
		String jsonData = null;
		RequestBody body = RequestBody.create(JSON, UserFactory.getUserJson(user));
		Request request = new Request.Builder()
				.url(URL)
				.post(body)
				.addHeader("content-type", "application/json")
				.addHeader("authorization", getBasicAuth(USER, PW))
				.addHeader("cache-control", "no-cache")
				.build();
		try {
			Response response = client.newCall(request).execute();
			jsonData = response.body().string();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RepositoryException("Exception from IS when creating user", e);
		} 
		return jsonData;
	}

	@Override
	public String findUseridByUsername(String username) {
		String jsonData = null;

		Request request = new Request.Builder()
				.url(this.getUserFilterEndpoint("userName", username))
				.get()
				.addHeader("authorization", getBasicAuth(USER, PW))
				.addHeader("cache-control", "no-cache")
				.build();
		try {
			Response response = client.newCall(request).execute();
			jsonData = response.body().string();

		} catch (IOException e) {
			throw new RepositoryException(e);
		}
		return jsonData;
	}
	
	@Override
	public String findUserByUserid(String userid) {
		String jsonData = null;

		Request request = new Request.Builder()
				.url(this.getUserFilterEndpoint("id", userid))
				.get()
				.addHeader("authorization", getBasicAuth(USER, PW))
				.addHeader("cache-control", "no-cache")
				.build();
		try {
			Response response = client.newCall(request).execute();
			jsonData = response.body().string();

		} catch (IOException e) {
			throw new RepositoryException(e);
		}
		return jsonData;
	}
	
	public String getBasicAuth(String user, String pw) {
		String userAsB64 = null;
		try {
			userAsB64 = Base64.getEncoder().encodeToString((user + ":" + pw).getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RepositoryException(e);
		}
		return "Basic " + userAsB64;
	}
	
	private String getUserFilterEndpoint(String key, String value) {
		String endpoint = URL + "?filter=" + key + "%20Eq%20%22" + value + "%22";
		return endpoint;
		
	}
	
	private String getUrl() {
		StringBuilder builder = new StringBuilder("https://")
				.append(DatabaseClass.getProperties().getProperty(DatabaseClass.IDENTITY_NODE))
				.append("/wso2/scim/Users");
		return builder.toString();
	}
}
