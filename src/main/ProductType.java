package main;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ProductType implements Serializable{
    private static final long serialVersionUID = 1113799434508676095L;

	private int currentStock;
	private int restock;
	private String name;
	private String supplier;
	private HashMap<PricingMethod, Double> prices;
	private String productID;
	
	public ProductType(String productID,String name, String supplier){
		this. productID = productID;
		this.setName(name);
		this.supplier = supplier;
		prices = new HashMap<>();
	}
	public Double getBasePrice(PricingMethod x, int amount) {
		return this.prices.get(x) * amount;
	}
	public Set<PricingMethod> availablePrices() {
		return this.prices.keySet();
	}

	public void setBasePrice(PricingMethod pricingMethod, double price) {
		this.prices.put(pricingMethod,price);
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public int getRestock() {
		return restock;
	}

	public void setRestock(int restock) {
		this.restock = restock;
	}

	public String getSupplier() {
		return supplier;
	}

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    // Get Price methods should raise an exception if a price is requested
	// That they can't handle. e.g Brooms are asked for price by weight when
	// they are priced by quantity
	public ArrayList<Discount> getProductSpecificDiscounts() {
		// TODO Auto-generated method stub
		return null;
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

	public Property<String> prodIDProperty()
    {
        return new SimpleStringProperty(name);
    }
}
