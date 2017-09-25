package main;

public class ProductType {
	private int price;
	private int currentStock;
	private int restock;
	private String supplier;
	private int purchaseOrderPrice;
	private PricingMethod pricingMethod;
	
	public Product(String productID, int price,PricingMethod pricingMethod, String supplier, int purchaseOrderPrice){
		this.productID = productID;
		this.price = price;
		this.supplier = supplier;
		this.pricingMethod = pricingMethod;
		this.purchaseOrderPrice = purchaseOrderPrice;
	}
	public Product(String productID, int price, int currentStock, int restock,String supplier, int purchaseOrderPrice){
		this.productID = productID;
		this.price = price;
		this.currentStock = currentStock;
		this.restock = restock;
		this.supplier = supplier;
		this.purchaseOrderPrice = purchaseOrderPrice;
	}
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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


	public int getPurchaseOrderPrice() {
		return purchaseOrderPrice;
	}

	public void setPurchaseOrderPrice(int purchaseOrderPrice) {
		this.purchaseOrderPrice = purchaseOrderPrice;
	}
	
	public void changePrice(int price) {
		if (price >= 0) 
		{
		setPrice(price);
		} else {
			//throw something
		}
	}
	public PricingMethod getPricingMethod() 
	{
		return this.pricingMethod;
	}
	
	// Get Price methods should raise an exception if a price is requested
	// That they can't handle. e.g Brooms are asked for price by weight when
	// they are priced by quantity


	public float getBasePrice(PricingMethod pricingMethod, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}
}
