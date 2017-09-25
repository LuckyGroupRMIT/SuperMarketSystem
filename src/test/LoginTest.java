import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Database;
import main.Login;
import main.StaffAccount;
import main.Permission;

public class LoginTest {

    StaffAccount temp;

	@Before
	public void setUp() throws Exception {
		temp = new StaffAccount("user", "password", Permission.MANAGER);
        Database.addObject(temp.getStaffID(), temp);
	}

	@Test
	public void checkDetailsTest() {
		boolean marks = Login.checkDetails("user", "password");
		assertTrue(marks);
	}
	
	@Test
	public void checkPermissionsTest() {
		Permission marks = Login.checkPermissions("user");
		assertEquals(temp.getPermissions(), marks);
	}
}
