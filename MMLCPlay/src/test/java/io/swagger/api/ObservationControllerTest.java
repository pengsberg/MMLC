package io.swagger.api;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.response.Response;

public class ObservationControllerTest {
	public final static String BASE_PATH = "http://localhost:8080/MMLCPlay/api";
	private final static String PATH = BASE_PATH + "/observations";
	
	@Ignore
	@Test
	public void createUpdateDeleteObservation() {
		// See if we can GET the Observation
		Response response2 = given()
				.header("customuserid", "caaf48a5-eda9-4232-bad1-4d9910f21a51")
				.header("customsystemid", "caaf48a5-eda9-4232-bad1-4d9910f21a51")
				.get(PATH);
		
		assertNotNull("Response is null", response2);
		
	}

}
