package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class ProductType implements Serializable{
    private static final long serialVersionUID = 1113799434508676095L;

	private int currentStock;
	private int restockAmount;
	private String prodName;
	private String prodSupp;
	private String prodID;
	private double prodPrice;
	private ArrayList<Discount> discounts;
	private PricingMethod pricingMethod;
	
	public ProductType(String productID,String name, String supplier,int restockAmount,PricingMethod method, double basePrice){
		this.prodID = productID;
		this.prodName = name;
		this.prodSupp = supplier;
		this.pricingMethod = method;
		this.prodPrice = basePrice;
		this.currentStock = 0;
		this.restockAmount = restockAmount; 
		this.discounts = new ArrayList<Discount>();
	}

	public Double getBasePrice(int amount) {
		return this.prodPrice*amount;
	}
	public void setBasePrice(double price) {
		this.prodPrice = price;
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

	public String getProdSupp() {
		return prodSupp;
	}

    public void setSupplier(String supplier) {
        this.prodSupp = supplier;
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

	public String getProdID() {
		return prodID;
	}

	public String getProdName() {
		return prodName;
	}

	public void setName(String name) {
		this.prodName = name;
	}
}
