import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Login;
import main.StaffAccount;

public class LoginTest {

	@Before
	public void setUp() throws Exception {
		StaffAccount temp = new StaffAccount("user", "password", 1);
	}

	@Test
	public void checkDetailsTest() {
		boolean marks = Login.checkDetails("user", "password");
		assertTrue(marks);
	}
	
	@Test
	public void checkPermissionsTest() {
		boolean marks = Login.checkPermissions("user");
		assertTrue(marks);
	}
}
