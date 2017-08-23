package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.UserAccountInterface;
import main.UserAccountSystem;

public class UserAccountSystemTest {

	@Test
	public void test() {
		int[] ids = new int[10];
		UserAccountSystem accountSystem = new UserAccountSystem();
		UserAccountInterface[] accounts = new UserAccountInterface[10];
		int[] orderToCheck = {0,2,5,3,7,9,8,1,4,6,0,4,3,5,6};
		for(int i = 0; i < 10; i++) {
			UserAccountInterface eachAccount = new DummyAccount();
			accounts[i] = eachAccount;
			int eachID = accountSystem.add(eachAccount);
			ids[i] = eachID;
		}
		for(int i = 0; i<orderToCheck.length; i++) {
			int eachID = ids[i];
			UserAccountInterface eachAccount = accounts[i];
			assertEquals(accountSystem.getByID(eachID),eachAccount);
		}
	}

}
