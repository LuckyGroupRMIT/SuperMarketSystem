package main;
import java.util.HashMap;

public class UserAccountSystem {
	private HashMap<Integer,UserAccountInterface> accounts;
	private int idCount = 1;
	public UserAccountSystem() {
		this.accounts = new HashMap<Integer,UserAccountInterface>();
	}
	public Object getByID(int eachID) {
		return accounts.get(eachID);
	}

	public int add(UserAccountInterface newAccount) {
		int newId = this.idCount;
		this.accounts.put(newId, newAccount);
		this.idCount++;
		return newId;
	}

}
