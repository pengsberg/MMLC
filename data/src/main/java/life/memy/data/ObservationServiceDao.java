package life.memy.data;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.x;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Statement;
import com.couchbase.client.java.query.dsl.Sort;

import io.swagger.model.Observation;
import life.memy.exception.DataNotFoundException;
import life.memy.exception.RepositoryException;
import life.memy.json.DateParam;

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
	
	public ObservationServiceDao() {
		
	}
	
	/**
	  * @see Repository#findById(String, Class<? extends T>) findById
	  */
	public Observation findById(String id) {
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
	
	
	public Response findByObservationtype(String observationtype, String customuserid, 
			String startdate, String enddate, String sort, Integer limit, String customsystemid ) {
		
		final String ASC = "+";
		final String DESC = "-";
		
		if (customuserid == null) {
			throw new RepositoryException("customuserid is null");
		}
		if (observationtype == null) {
			throw new RepositoryException("observationtype is null");
		}
		
//		Statement statement = select("*").from(bucket.name())
//				.where(x("observationtype").eq("$observationtype")
//						.and(x("type").eq("$type"))
//						.and(x("subjectid").eq("$subjectid")))
//						.orderBy(Sort.desc("observationcreateddatetime"));
		
		String statement = "SELECT * FROM memylifePOC WHERE observationtype = $observationtype AND type = $type AND subjectid = $subjectid";
		
		DateParam startdateParam = null;
		DateParam enddateParam = null;

		// Selecting observations for a period
		if (startdate != null && enddate != null) {
			statement += " AND observationcreateddatetime >= $startdate AND observationcreateddatetime <= $enddate";
			startdateParam = new DateParam(startdate);
			enddateParam = new DateParam(enddate);
		} else {
			// to avoid null pointer exception
			startdateParam = new DateParam("2017-01-01");
			enddateParam = new DateParam("2017-01-01");
		}
		
		// Sorting the observations
		if (sort != null) {
			if (sort.startsWith(ASC)) {
				statement += " ORDER BY " + sort.substring(1) + " ASC";
			} else {
				statement += " ORDER BY " + sort.substring(1) + " DESC";
			}
		}
		 // Limit the total number of observations
		if (limit != null) {
			statement += " LIMIT " + limit;
		}
		
		JsonObject placeholderValues = JsonObject.create()
				.put("bucketname", bucketName)
				.put("observationtype", observationtype)
				.put("type", "observation")
				.put("subjectid", customuserid)
				.put("startdate", startdateParam.getIsoDate())
				.put("enddate", enddateParam.getIsoDate());
			
		N1qlQuery query = N1qlQuery.parameterized(statement, placeholderValues );
		List<Observation> observations = new ArrayList<>();
		
		// Getting the number of retreived observations
		N1qlQueryResult result = bucket.query(query);
		int size = result.allRows().size();
		
		for (N1qlQueryRow row : result) {
		    JsonObject jsonObject = row.value();
		    JsonObject value = (JsonObject) jsonObject.get(bucketName);
		    Observation observation = converter.fromJson(value.toString(), Observation.class);
		    observations.add(observation);
		}
		
		if (observations.size() == 0) {
			throw new DataNotFoundException("Observation with observationtype " + observationtype + " not found");
		}
		
		return Response.ok().entity(observations)
				.header("X-total-count", size)
				.build();
	}

	
	/**
	  * @see Repository#create(T, Class<? extends T>) create
	  */
	public Observation create(Observation observation) {
		String id = getNextId(Observation.class, 1, 100);
		observation.setDocid(id);
		JsonDocument docIn = toJsonDocument(observation);
		JsonDocument docOut;
		try {
			docOut = bucket.insert(docIn);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
		return fromJsonDocument(docOut);
	}
	
	public Observation update(Observation observation) {
		if(isBlank(observation.getDocid())) {
			throw new RepositoryException("docid is null");
		}
		
		JsonDocument docIn = toJsonDocument(observation);
		JsonDocument docOut;
		try {
			docOut = bucket.upsert(docIn);
		} catch (CouchbaseException e) {
			throw new RepositoryException(e);
		}
		return fromJsonDocument(docOut);
	}
	
	
	public List<Observation> getAllObservations(String customuserid) {
		
		if (customuserid == null) {
			throw new RepositoryException("customuserid is null");
		}
		
		Statement statement = select("*").from(bucket.name())
				.where(x("type").eq("$type")
						.and(x("subjectid").eq("$subjectid")))
						.orderBy(Sort.desc("observationcreateddatetime"));

		JsonObject placeholderValues = JsonObject.create()
				.put("type", "observation")
				.put("subjectid", customuserid);

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
	
	

	/**
	 * Converts a JsonDocument into an object of the specified type
	 * 
	 * @param doc JsonDocument to be converted
	 * @param type Class<T> that represents the type of this or a parent class
	 * @return Reference to an object of the specified type
	 */
	protected Observation fromJsonDocument(JsonDocument doc) {
		if (doc == null) {
			throw new RepositoryException("document is null");
		}
		JsonObject content = doc.content();
		if (content == null) {
			throw new IllegalStateException("document has no content");
		}
		Observation result = converter.fromJson(content.toString(), Observation.class);
//		result.setCas(doc.cas());
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
			throw new RepositoryException("observation is null");
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
