package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.*;

public class UserAccountTest {
	
	private UserAccountSystem accountSystem;
	@Before
	public void setUp() throws Exception {
		accountSystem = new UserAccountSystem();
	}
	
	@Test
	public void testAccountsAreAccessiblyById() {
		UserAccountInterface newAccount = new test.DummyAccount();
		int id = accountSystem.add(newAccount);
		assertSame(accountSystem.getByID(id),newAccount);
	}
	@Test
	public void testAccountsAreAccessiblyInDifferentOrders() {
		UserAccountInterface[] accounts = new UserAccountInterface[5];
		int[] ids = new int[5];
		int[] orderToCheck = {0,2,1,3,2,4,4,3};
		for(int i = 0; i<ids.length; i++) {
			accounts[i] = new test.DummyAccount();
			ids[i] = accountSystem.add(accounts[i]);
		}
		for (int i = 0; i<orderToCheck.length; i++) {
			int j = orderToCheck[i];
			assertSame(accounts[j],accountSystem.getByID(ids[j]));
		}
	}

	@Test
	public void testPassword() {

	}
	
}