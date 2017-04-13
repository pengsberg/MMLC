package life.memy.identity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IdentityClientImplTest {

	
	@Test
	public void testUserPwAsB64_is_correct() {
		IdentityClientImpl ic = new IdentityClientImpl();
		String encoding = ic.getBasicAuth("admin", "admin");
		assertEquals(encoding, "Basic YWRtaW46YWRtaW4=");
		
	}

}
