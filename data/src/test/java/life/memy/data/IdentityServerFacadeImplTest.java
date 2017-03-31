package life.memy.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import life.memy.identity.IdentityClient;
import life.memy.identity.IdentityServerFacadeImpl;

public class IdentityServerFacadeImplTest {
	
	private static final String USER_INFO = "getUserInfo.json";
	
	@Mock
	IdentityClient client;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUserId() throws IOException {
		
		String json = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(USER_INFO), "UTF-8");
		IdentityServerFacadeImpl isf = new IdentityServerFacadeImpl();
		isf.setClient(client);
		String endpoint = isf.getUserFilterEndpoint("per456");
		when(client.getUserId(endpoint)).thenReturn(json);
		
		String userId = isf.getUserId("per456");
		assertEquals("6921cfe8-9f11-4eb2-a4cc-12fe9619a13c", userId);
		verify(client).getUserId(endpoint);
	}


}
