package life.memy.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;



public class DatabaseClass {
	public static final String PROPERTIES_FILENAME = "application.properties";
	public static final String NODES_KEY = "couchbase.nodes";
	public static final String NODES_DEFAULT_VALUE = "127.0.0.1";
	public static final String BUCKET_NAME = "bucket.name";
	
	private static DatabaseClass databaseClass;
	private ObservationServiceDao observationRepo;
	private UserServiceDao userRepo;
	
	public static DatabaseClass getDatabaseClass() {
		if (databaseClass == null) {
			databaseClass = new DatabaseClass();
		}
		return databaseClass;
	}
	
	public ObservationServiceDao getObservationRepo() {
		return observationRepo;
	}
	
	public UserServiceDao getUserRepo() {
		return userRepo;
	}

	private DatabaseClass() {
		final Properties props = System.getProperties();
		try {
			InputStream input = DatabaseClass.class.getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
			props.load(input);
		} catch (IOException e) {
			System.err.println("Unable to open " + PROPERTIES_FILENAME);
		}
		String[] nodes = 
			props.getProperty(NODES_KEY, NODES_DEFAULT_VALUE).split(",");
		String bucket = props.getProperty(BUCKET_NAME);
		Cluster cluster = CouchbaseCluster.create(nodes);
		observationRepo = new ObservationServiceDao(cluster, bucket);
		userRepo = new UserServiceDao(cluster, bucket);
	}
}
