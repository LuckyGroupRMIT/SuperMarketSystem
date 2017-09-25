package main;

public class Purchase {
	// Represents a purchase of a certain item for a certified price'
	private ProductType product;
	private int amount;
	private double basePrice;
	private double discountedPrice;
	private PricingMethod pricingMethod;
	public Purchase(ProductType product, PricingMethod pricingMethod, int amount ) {
		this.product = product;
		this.amount = amount;
		this.basePrice = product.getBasePrice(pricingMethod,amount);
		this.discountedPrice = this.basePrice;
	}
	public ProductType getProduct() {
		return this.product;
	}
	public int getAmount() {
		return this.amount;
	}
	public double getPrice() {
		return this.basePrice;
	}
	public double getDiscountedPrice() {
		return this.discountedPrice;
	}
	public PricingMethod getPricingMethod() {
		return this.pricingMethod;
	}
	public boolean applyDiscount(double discountValue) {
		// Discounts don't stack. Returns true if the discount applies, false otherwise
		double newDiscountedPrice = this.basePrice - discountValue;
		if (newDiscountedPrice < this.discountedPrice) {
			this.discountedPrice = newDiscountedPrice;
			return true;
		} else {
			return false;
		}	
	}
	public void resetDiscounts() {
		this.discountedPrice = this.basePrice;
	}
}
