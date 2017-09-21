package main;

public class Purchase {
	// Represents a purchase of a certain item for a certified price'
	private Product product;
	private int quantity;
	private float price;
	private float discount;
	pricingMethod
	public Purchase(Product product, int quantityOrWeight, boolean isQuantity, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.isQuantity = isQuantity;
		this.price = product.getPrice(quantityOrWeight);
	}
	public getProduct() {
		return this.product;
	}
	public getQuantity() {
		return this.quantity;
	}
	public getPrice() {
		return this.normalPrice;
	}
	public getDiscount() {
		return this.discount();
	}
}
