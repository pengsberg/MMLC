package life.memy.identity.scim;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.swagger.model.User;

public class UserFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	@Test
	public void testGetUserJson() {
		User user = new User();
		String json = UserFactory.getUserJson(user);
	}

}
