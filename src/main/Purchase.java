package main;

import java.io.Serializable;

public class Purchase implements Serializable{
	// Represents a purchase of a certain item for a certified price'
    private static final long serialVersionUID = 1113799434508676095L;

	private ProductType product;
	private int cartQuant;
	private double cartPrice;
	private double cartTotal;
	private PricingMethod pricingMethod;

	public Purchase(ProductType product, int amount ) {
		this.product = product;
		this.cartQuant = amount;
		this.cartPrice = product.getBasePrice(amount);
		this.cartTotal = this.cartPrice;
	}

	public ProductType getProduct() {
		return this.product;
	}

	public int getAmount() {
		return this.cartQuant;
	}

	public double getPrice() {
		return this.cartPrice;
	}

	public double getDiscountedPrice() {
		return this.cartTotal;
	}

	public PricingMethod getPricingMethod() {
		return this.pricingMethod;
	}

	public boolean applyDiscount(double discountValue) {
		// Discounts don't stack. Returns true if the discount applies, false otherwise
		double newDiscountedPrice = this.cartPrice - discountValue;
		if (newDiscountedPrice < this.cartTotal) {
			this.cartTotal = newDiscountedPrice;
			return true;
		} else {
			return false;
		}	
	}

	public void resetDiscounts() {
		this.cartTotal = this.cartPrice;
	}

	public double getUndiscountedPrice() {
		return cartPrice;
	}

	public void setDiscountedPrice(double newDiscountedPrice) {
		this.cartTotal = newDiscountedPrice;
	}
}
