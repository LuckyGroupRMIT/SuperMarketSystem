package main;

import java.io.Serializable;

public class BulkDiscount implements Discount, Serializable {
    private static final long serialVersionUID = 1113799434508676095L;

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
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPercentage() {
        return percentageOff;
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
