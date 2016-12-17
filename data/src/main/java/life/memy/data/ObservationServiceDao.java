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

import io.swagger.model.Observation;
import life.memy.exception.DataNotFoundException;
import life.memy.exception.RepositoryException;

public class ObservationServiceDao extends BaseDao {
	
	/**
	 * Constructor
	 * 
	 * @param cluster Couchbase Cluster
	 * @param bucketName Name of the bucket to use
	 */
	public ObservationServiceDao(Cluster cluster, String bucketName) {
		super(cluster, bucketName);
	}
	
	
	public Observation findByObservationtype(String observationtype) {
		Statement statement = select("*").from(bucket.name())
				.where(x("observationtype").eq("$observationtype").and(x("type").eq("$type")));
		JsonObject placeholderValues = JsonObject.create().put("observationtype", observationtype)
				.put("type", "observation");
		N1qlQuery q = N1qlQuery.parameterized(statement, placeholderValues );
		Observation observation = new Observation();
		for (N1qlQueryRow row : bucket.query(q)) {
		    JsonObject jsonObject = row.value();
		    JsonObject value = (JsonObject) jsonObject.get(bucketName);
		    observation = converter.fromJson(value.toString(), Observation.class);
		}
		if (observation.getDocid() == null) {
    		throw new DataNotFoundException("Observation with observationtype " + observationtype + " not found");
    	}
		return observation;
	}

	
	/**
	  * @see Repository#create(T, Class<? extends T>) create
	  */
	public Observation create(Observation observation) {
		if(isBlank(observation.getDocid())) {
			String id = getNextId(Observation.class, 1, 100);
			observation.setDocid(id);
		}
		JsonDocument docIn = toJsonDocument(observation);
		JsonDocument docOut;
		try {
			docOut = bucket.insert(docIn);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
		return fromJsonDocument(docOut);
	}
	
	
	public List<Observation> getAllObservations() {
		Statement statement = select("*").from(bucket.name()).where(x("type").eq(x("$type")));
		JsonObject placeholderValues = JsonObject.create().put("type", "observation");
		N1qlQuery query = N1qlQuery.parameterized(statement, placeholderValues );
		List<Observation> observations = new ArrayList<>();
		for (N1qlQueryRow row : bucket.query(query)) {
		    JsonObject jsonObject = row.value();
		    JsonObject value = (JsonObject) jsonObject.get(bucketName);
		    Observation observation = converter.fromJson(value.toString(), Observation.class);
		    observations.add(observation);
		}
		if (observations.size() == 0) {
			throw new DataNotFoundException("No observations found");
		}
		return observations;
	}
	
	
//	public List<Observation> getAllObservations(){
//		Map<String,Observation> observations = new HashMap<>();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Statement statement = select("resourceType","docId","subType", "userId", "observationState","comment","valueDouble",
//				"observationCreated").from(i(bucket.name())).where(x("resourceType").eq(x("$resType")));
//		JsonObject placeholderValues = JsonObject.create().put("resType", "observation");
//		N1qlQuery q = N1qlQuery.parameterized(statement, placeholderValues);
//		
//		for (N1qlQueryRow row : bucket.query(q)) {
//			JsonObject j = row.value();
//			String docId = j.getString("docId");
//			if (docId == null) {
//				docId = j.getString("docid");
//			}
//			if (docId == null) {
//				continue;
//			}
//			//JsonDocument doc = JsonDocument.create(docId, content);
//			
//			//Observation observation = fromJsonDocument(doc);
//			Observation observation = new Observation();
//			
//			String ds = j.getString("observationCreated");
//			Date date = null;
//			try {
//				date = dateFormat.parse(ds);
//				observation.setObservationcreateddatetime(date.toString());
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			//observation.setResourcetype(j.getString("resourceType"));
//			observation.setDocid(j.getString("docId"));
//			observation.setSubjectid(j.getString("subType"));
//			observation.setUserid(j.getString("userId"));
//			//observation.setObservationState(j.getString("observationState"));
//			observation.setComment(j.getString("comment"));
//						
//			observations.put(observation.getDocid(), observation);
//		}
//		return new ArrayList<Observation> (observations.values());
//	}
	
	/**
	 * Converts a JsonDocument into an object of the specified type
	 * 
	 * @param doc JsonDocument to be converted
	 * @param type Class<T> that represents the type of this or a parent class
	 * @return Reference to an object of the specified type
	 */
	protected Observation fromJsonDocument(JsonDocument doc) {
		if (doc == null) {
			throw new IllegalArgumentException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		Observation result = converter.fromJson(content.toString(), Observation.class);
		result.setCas(doc.cas());
		return result;
	}
	
	
	/**
	 * Converts an object to a JsonDocument
	 * 
	 * @param source Object to be converted
	 * @return JsonDocument that represents the specified object
	 */
	protected JsonDocument toJsonDocument(Observation observation) {
		if (observation == null) {
			throw new IllegalArgumentException("observation is null");
		}
		String id = observation.getDocid();
		if (id == null) {
			throw new IllegalStateException("docId is null");
		}
		
		try {
			JsonObject content = 
				transcoder.stringToJsonObject(converter.toJson(observation));
			JsonDocument doc = JsonDocument.create(id, content);
			return doc;
		} catch (Exception e) {
			throw new RepositoryException(e);
		}
	}
}
