import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.Login;

public class LoginTest {

	@Before
	public void setUp() throws Exception {
		List<Accounts> accounts = new ArrayList<>();
		Accounts temp = new Accounts(01, password, 1);
		accounts.add(temp);
	}

	@Test
	public void test1() {
		int marks = Login.checkDetails(01, password);
		assertEquals(1, marks);
	}
	
	@Test
	public void test2() {
		int marks = Login.checkPermissions(01);
		assertEquals(1, marks );
	}

}
