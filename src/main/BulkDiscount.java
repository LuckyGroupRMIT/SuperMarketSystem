package main;

public class BulkDiscount implements Discount {
	PricingMethod pricingMethod;
	String name;
	int amount;
	double percentage;
	BulkDiscount(String name, int amount, double percentage) {
		this.name = name;
		this.amount = amount;
		this.percentage = percentage;
	}
	

	@Override
	public double apply(Purchase purchase) {
		if (purchase.getPricingMethod == this.pricingMethod) {
			if (purchase.getAmount()>this.amount) {
				double newDiscountedPrice = purchase.getUndiscountedPrice()*(1d-this.percentage);
				if (newDiscountedPrice<purchase.getDiscountedPrice()) {
					
				}
			}
		}
	}

}
