package main;

import java.io.Serializable;

public class UserAccount implements Serializable{
    private static final long serialVersionUID = 1113799434508676095L;
	private String username;
	
	public UserAccount(String username){
		this.username = username;
	}

	public String getID() {
		return username;
	}
	
}
