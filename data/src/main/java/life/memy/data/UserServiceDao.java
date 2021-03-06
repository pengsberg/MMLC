package life.memy.data;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.dsl.Expression.x;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
import life.memy.identity.IdentityServerFacade;
import life.memy.identity.IdentityServerFacadeImpl;

public class UserServiceDao extends BaseDao {
	final static Logger logger = Logger.getLogger(UserServiceDao.class);
	
	IdentityServerFacade isFacade = new IdentityServerFacadeImpl();

	public UserServiceDao(Cluster cluster, String bucketName) {
		super(cluster, bucketName);
	}
	
	/**
	  * @see Repository#findById(String, Class<? extends T>) findById
	  */
	public User findById(String id) {
		JsonDocument doc = bucket.get(id);
		if (doc == null) {
   		throw new DataNotFoundException("resource with docId " + id + " not found");
   	}

//		If un-commenting the following code, you must complete all required imports.
		
//		JsonDocument doc;
//		String statement = "SELECT customer360.* FROM customer360 USE KEYS $1";
//		JsonArray values = JsonArray.empty().add(id);
//		N1qlParams params = N1qlParams.build().consistency(ScanConsistency.REQUEST_PLUS);
//		ParameterizedN1qlQuery query = ParameterizedN1qlQuery.parameterized(statement, values, params);
//		N1qlQueryResult result = bucket.query(query); 
//		List<N1qlQueryRow> list = result.allRows(); 
//	    if (list.size() == 0) {
//	    	doc = null; 
//	    } else {
//	    	N1qlQueryRow firstRow = list.get(0);
//	    	JsonObject firstRowObject = firstRow.value(); 
//	    	doc = JsonDocument.create(id, firstRowObject);
//	    }
		
		//return doc == null ? null : fromJsonDocument(doc, type);
		return fromJsonDocument(doc);
	}
	
	public User findByUsername(String userName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Entering findByUsername " + userName);
		}
		String response = isFacade.findUseridByUsername(userName);
		if (logger.isDebugEnabled()) {
			logger.debug("Got response from IS: " + response.toString());
		}
		if (response.contains("Errors")) {
			logger.error("Response contains error - user not found");
    		throw new DataNotFoundException(response);
    	}
		
		Statement statement = select("*")
				.from(i(bucket.name()))
				.where(x("username").eq("$username").and(x("type").eq("$type")));

		JsonObject placeholderValues = JsonObject.create()
											.put("username", userName)
											.put("type", "user");

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
	
	public User findByUserid(String userid) {
		if (logger.isDebugEnabled()) {
			logger.debug("Entering findByUserid " + userid);
		}
		String response = isFacade.findUserByUserid(userid);
		if (response.contains("Errors")) {
    		throw new DataNotFoundException(response);
    	}
		
		Statement statement = select("*")
				.from(i(bucket.name()))
				.where(x("userid").eq("$userid").and(x("type").eq("$type")));

		JsonObject placeholderValues = JsonObject.create()
											.put("userid", userid)
											.put("type", "user");

		N1qlQuery q = N1qlQuery.parameterized(statement, placeholderValues );
		
		User user = new User();
		for (N1qlQueryRow row : bucket.query(q)) {
		    JsonObject jsonObject = row.value();
		    JsonObject value = (JsonObject) jsonObject.get(bucketName);
		    user = converter.fromJson(value.toString(), User.class);
		}
		if (user.getDocid() == null) {
    		throw new DataNotFoundException("User with userid " + userid + " not found");
    	}
		return user;
	}
	
	public List<User> getAllUsers() {
		Statement statement = select("*").from(i(bucket.name())).where(x("type").eq(x("$type")));
		
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
		if (logger.isDebugEnabled()) {
			logger.debug("Entering create user " + user.getUsername() + ")");
		}
		String userid;
		try {
			userid = isFacade.createUser(user);
		} catch(Exception e) {
			logger.error("Exception from IS when creating user: " + e.getMessage());
			throw e;
		}
		user.setUserid(userid);
		user.setDocid("user::" + userid);
		JsonDocument docIn = toJsonDocument(user);
		JsonDocument docOut;
		try {
			docOut = bucket.insert(docIn);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
		return fromJsonDocument(docOut);
	}
	
	public User update(User user) {
		if(isBlank(user.getDocid())) {
			throw new IllegalArgumentException("docid is null");
		}
		
		JsonDocument docIn = toJsonDocument(user);
		JsonDocument docOut;
		try {
			docOut = bucket.upsert(docIn);
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
}
