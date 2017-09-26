package main;

public class CustomerAccount extends UserAccount{
	private int loyaltyPoints = 0;
	
	public CustomerAccount (String username, String password){
		super(username, password);
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	

}
