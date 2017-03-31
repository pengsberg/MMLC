package life.memy.identity;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import life.memy.data.DatabaseClass;
import life.memy.exception.RepositoryException;
import life.memy.identity.scim.UserFactory;

public class IdentityClientImpl implements IdentityClient {
	final static Logger logger = Logger.getLogger(IdentityClientImpl.class);
	
	private OkHttpClient client = new OkHttpClient().setHostnameVerifier(new NullHostNameVerifier());
	private final String URL = this.getUrl();
	private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	@Override
	public String createUser(String userName) {
		
		String jsonData = null;
		RequestBody body = RequestBody.create(JSON, UserFactory.getUserJson(userName));
		Request request = new Request.Builder()
				.url(URL)
				.post(body)
				.addHeader("content-type", "application/json")
				.addHeader("authorization", "Basic YWRtaW46YWRtaW4=")
				.addHeader("cache-control", "no-cache")
				.build();
		try {
			Response response = client.newCall(request).execute();
			jsonData = response.body().string();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new RepositoryException("Error when creating user at Identity Server", e);
		} 
		return jsonData;
	}

	@Override
	public String getUserId(String endpoint) {
		String jsonData = null;

		Request request = new Request.Builder()
				.url(endpoint)
				.get()
				.addHeader("authorization", "Basic YWRtaW46YWRtaW4=")
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
	
	private String getUrl() {
		StringBuilder builder = new StringBuilder("https://")
				.append(DatabaseClass.getProperties().getProperty(DatabaseClass.IDENTITY_NODE))
				.append("/wso2/scim/Users");
		return builder.toString();
	}
}
