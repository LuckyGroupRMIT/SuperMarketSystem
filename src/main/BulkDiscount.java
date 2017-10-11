package main;

public class BulkDiscount implements Discount {
	PricingMethod pricingMethod;
	String name;
	int amount;
	double percentageOff;
	public BulkDiscount(String name, int amount, double percentageOff) {
		this.name = name;
		this.amount = amount;
		this.percentageOff = percentageOff;
	}
	public String getName() {
		return this.name;
	}
	

	@Override
	public double apply(Purchase purchase) {
		if (purchase.getPricingMethod() == this.pricingMethod) {
			if (purchase.getAmount()>=this.amount) {
				double oldDiscountedPrice = purchase.getDiscountedPrice();
				double newDiscountedPrice = purchase.getUndiscountedPrice()*(1d-this.percentageOff);
				if (newDiscountedPrice<purchase.getDiscountedPrice()) {
					return oldDiscountedPrice-newDiscountedPrice;
				}
			}
		}
		return 0;
	}

}
