package main;

import java.io.Serializable;

public class CustomerAccount extends UserAccount implements Serializable{
	private int loyaltyPoints;
    private static final long serialVersionUID = 1113799434508676095L;
	
	public CustomerAccount (String username){
		super(username);
		loyaltyPoints = 0;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
}
