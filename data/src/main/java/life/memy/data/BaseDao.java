package life.memy.data;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.JsonLongDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;

import io.swagger.model.Entity;
import life.memy.exception.DataNotFoundException;
import life.memy.exception.RepositoryException;
import life.memy.json.JacksonConverter;
import life.memy.json.JsonConverter;

public class BaseDao {
	protected final JsonConverter converter = new JacksonConverter();
	protected final JsonTranscoder transcoder = new JsonTranscoder();
	protected Bucket bucket;
	protected String bucketName = null;
	
	/**
	 * Constructor
	 * 
	 * @param cluster Couchbase Cluster
	 * @param bucketName Name of the bucket to use
	 */
	public BaseDao(Cluster cluster, String bucketName) {
		try {
			this.bucketName = bucketName;
			bucket = cluster.openBucket(bucketName);
			
			// Create a N1QL Primary Index (but ignore if it exists)
	        bucket.bucketManager().createN1qlPrimaryIndex(true, false);
			
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
	}
	
	/**
	  * @see Repository#findById(String, Class<? extends T>) findById
	  */
	public <T extends Entity> T findById(String id, Class<? extends T> type) {
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
		return fromJsonDocument(doc, type);
	}
	
//	/**
//	  * @see Repository#create(T, Class<? extends T>) create
//	  */
//	public <T extends Entity> T create(T entity, Class<? extends T> type) {
//		if(isBlank(entity.getDocid())) {
//			String id = getNextId(type, 1, 100);
//			entity.setId(id);
//		}
//		JsonDocument docIn = toJsonDocument(entity);
//		JsonDocument docOut;
//		try {
//			docOut = bucket.insert(docIn);
//		} catch (CouchbaseException e) {
//			throw new RepositoryException(e);
//		}
//		return fromJsonDocument(docOut, type);
//	}
	
	/**
	 * Generates an ID using the Couchbase counter feature.
	 * 
	 * @param type Class<T> that represents the type of the entity
	 * @param init Initial value of the counter
	 * @param incr Amount to increment the counter value each time
	 * @return Next value of the counter
	 */
	protected <T extends Entity> String getNextId(Class<T> type, long incr, 
		long init) {
		String name = type.getSimpleName().toLowerCase();
		JsonLongDocument doc = 
			bucket.counter("counter::" + name, incr, init);
		return name + "::" + doc.content().toString();
	}

	/**
	
	/**
	 * Converts a JsonDocument into an object of the specified type
	 * 
	 * @param doc JsonDocument to be converted
	 * @param type Class<T> that represents the type of this or a parent class
	 * @return Reference to an object of the specified type
	 */
	protected <T extends Entity> T fromJsonDocument(JsonDocument doc, Class<T> type) {
		if (doc == null) {
			throw new IllegalArgumentException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		if (type == null) {
			throw new IllegalArgumentException("type is null");
		}
		T result = converter.fromJson(content.toString(), type);
		result.setCas(doc.cas());
		return result;
	}

	/**
	 * Converts an object to a JsonDocument
	 * 
	 * @param source Object to be converted
	 * @return JsonDocument that represents the specified object
	 */
//	protected <T extends Entity> JsonDocument toJsonDocument(T source) {
//		if (source == null) {
//			throw new IllegalArgumentException("entity is null");
//		}
//		String id = source.getId();
//		if (id == null) {
//			throw new IllegalStateException("entity ID is null");
//		}
//		try {
//			JsonObject content = 
//				transcoder.stringToJsonObject(converter.toJson(source));
//			JsonDocument doc = JsonDocument.create(id, content, source.getCas());
//			return doc;
//		} catch (Exception e) {
//			throw new RepositoryException(e);
//		}
//	}

}
