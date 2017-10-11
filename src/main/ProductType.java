package main;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductType implements Serializable{
    private static final long serialVersionUID = 1113799434508676095L;

	private int currentStock;
	private int restockAmount;
	private String name;
	private String supplier;
	private String productID;
	private double basePrice;
	private ArrayList<Discount> discounts;
	private PricingMethod pricingMethod;
	
	public ProductType(String productID,String name, String supplier,int restockAmount,PricingMethod method, double basePrice){
		this.productID = productID;
		this.name = name;
		this.supplier = supplier;
		this.pricingMethod = method;
		this.basePrice = basePrice;
		this.currentStock = 0;
		this.restockAmount = restockAmount; 
		this.discounts = new ArrayList<Discount>();
	}

	public Double getBasePrice(int amount) {
		return this.basePrice *amount;
	}
	public void setBasePrice(double price) {
		this.basePrice = price;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public int getRestockAmount() {
		return restockAmount;
	}
	public Boolean isLowOnStock() {
		return this.currentStock<=this.restockAmount;
	}

	public void setRestockAmount(int restock) {
		this.restockAmount = restock;
	}

	public String getSupplier() {
		return supplier;
	}

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public void addProductDiscount(Discount x) {
    	this.discounts.add(x);
    }
    public void removeProductDiscount(Discount x) {
    	this.discounts.remove(x);
    }

    // Get Price methods should raise an exception if a price is requested
	// That they can't handle. e.g Brooms are asked for price by weight when
	// they are priced by quantity
	public ArrayList<Discount> getProductSpecificDiscounts() {
		return this.discounts;
	}

	public String getProductID() {
		return productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
