package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.UserAccountInterface;
import main.UserAccountSystem;

public class UserAccountSystemTest {
	
	private UserAccountSystem accountSystem;
	@Before
	public void setUp() throws Exception {
		accountSystem = new UserAccountSystem();
	}
	
	@Test
	public void testAccountsAreAccessiblyById() {
		UserAccountInterface newAccount = new DummyAccount();
		int id = accountSystem.add(newAccount);
		assertSame(accountSystem.getByID(id),newAccount);
	}
	@Test
	public void testAccountsAreAccessiblyInDifferentOrders() {
		UserAccountInterface[] accounts = new UserAccountInterface[5];
		int[] ids = new int[5];
		int[] orderToCheck = {0,2,1,3,2,4,4,3};
		for(int i = 0; i<10; i++) {
			accounts[i] = new DummyAccount();
			ids[i] = accountSystem.add(accounts[i]);
		}
		for (int i = 0; i<orderToCheck.length; i++) {
			int j = orderToCheck[i];
			assertSame(accounts[j],accountSystem.getByID(ids[j]));
		}
		
	}
}

