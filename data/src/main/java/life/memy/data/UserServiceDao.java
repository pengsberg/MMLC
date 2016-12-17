package life.memy.data;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.x;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.List;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Statement;

import io.swagger.model.User;
import life.memy.exception.DataNotFoundException;
import life.memy.exception.RepositoryException;

public class UserServiceDao extends BaseDao {

	public UserServiceDao(Cluster cluster, String bucketName) {
		super(cluster, bucketName);
	}
	
	public User findByUsername(String userName) {
		Statement statement = select("*").from(bucket.name())
				.where(x("username").eq("$username").and(x("type").eq("$type")));
		JsonObject placeholderValues = JsonObject.create().put("username", userName).put("type", "user");
		N1qlQuery q = N1qlQuery.parameterized(statement, placeholderValues );
		User user = new User();
		for (N1qlQueryRow row : bucket.query(q)) {
		    JsonObject jsonObject = row.value();
		    JsonObject value = (JsonObject) jsonObject.get(bucketName);
		    user = converter.fromJson(value.toString(), User.class);
		}
		if (user.getDocid() == null) {
    		throw new DataNotFoundException("User with username " + userName + " not found");
    	}
		return user;
	}
	
	public List<User> getAllUsers() {
		Statement statement = select("*").from(bucket.name()).where(x("type").eq(x("$type")));
		JsonObject placeholderValues = JsonObject.create().put("type", "user");
		N1qlQuery q = N1qlQuery.parameterized(statement, placeholderValues );
		List<User> users = new ArrayList<>();
		for (N1qlQueryRow row : bucket.query(q)) {
		    JsonObject jsonObject = row.value();
		    JsonObject value = (JsonObject) jsonObject.get(bucketName);
		    User user = converter.fromJson(value.toString(), User.class);
		    users.add(user);
		}
		if (users.size() == 0) {
			throw new DataNotFoundException("No users found");
		}
		return users;
	}
		
	/**
	  * @see Repository#create(T, Class<? extends T>) create
	  */
	public User create(User user) {
		if(isBlank(user.getDocid())) {
			String id = getNextId(User.class, 1, 100);
			user.setDocid(id);
		}
		JsonDocument docIn = toJsonDocument(user);
		JsonDocument docOut;
		try {
			docOut = bucket.insert(docIn);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
		return fromJsonDocument(docOut);
	}
	
	
	/**
	 * Converts a JsonDocument into an object of the specified type
	 * 
	 * @param doc JsonDocument to be converted
	 * @param type Class<T> that represents the type of this or a parent class
	 * @return Reference to an object of the specified type
	 */
	protected User fromJsonDocument(JsonDocument doc) {
		if (doc == null) {
			throw new IllegalArgumentException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		User result = converter.fromJson(content.toString(), User.class);
		result.setCas(doc.cas());
		return result;
	}
	
	/**
	 * Converts an object to a JsonDocument
	 * 
	 * @param source Object to be converted
	 * @return JsonDocument that represents the specified object
	 */
	protected JsonDocument toJsonDocument(User user) {
		if (user == null) {
			throw new IllegalArgumentException("observation is null");
		}
		String id = user.getDocid();
		if (id == null) {
			throw new IllegalStateException("docId is null");
		}
		try {
			JsonObject content = 
				transcoder.stringToJsonObject(converter.toJson(user));
			JsonDocument doc = JsonDocument.create(id, content);
			return doc;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
	
//	protected String generateDocId() {
//		String prefix = "USR:";
//		JsonLongDocument nextIdNumber = bucket.counter("idGeneratorForUsers", 1, 0); //gives 12345
//		String id = prefix + nextIdNumber;
//		return id;
//	}
}
