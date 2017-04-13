package life.memy.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;

import io.swagger.model.Entity;
import io.swagger.model.Observation;
import life.memy.json.JacksonConverter;
import life.memy.json.JsonConverter;

public class ObservationServiceDaoTest {
	protected final JsonConverter converter = new JacksonConverter();
	protected final JsonTranscoder transcoder = new JsonTranscoder();

	@Mock
	Bucket bucket;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void testSimple() throws JSONException {
		String expected = "{friends:[{id:123,name:\"Corby Page\"},{id:456,name:\"Carter Page\"}]}";
		JSONAssert.assertEquals("{friends:[{id:123},{id:456}]}", expected, false); // Pass
	}

	@Test
	public void findById_shouldReturnObservation() throws Exception {
		ObservationServiceDao observationService = new ObservationServiceDao();
		observationService.setBucket(bucket);

		String id = "observation::200";
		Observation observationOut = new Observation();
		observationOut.setDocid(id);
		JsonDocument doc = toJsonDocument(observationOut, id);

		when(bucket.get(id)).thenReturn(doc);
		Observation observation = observationService.findById(id);
		assertNotNull(observation);
		assertEquals("observation::200", observation.getDocid());
	}

	private <T extends Entity> JsonDocument toJsonDocument(T source, String id) throws Exception {
		JsonObject content = transcoder.stringToJsonObject(converter.toJson(source));
		JsonDocument doc = JsonDocument.create(id, content, source.getCas());
		return doc;
	}
}
