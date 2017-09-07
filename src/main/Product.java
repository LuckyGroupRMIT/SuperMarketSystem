package main;

public class Product {
	private String productID;
	private int price;
	private int currentStock;
	private int restock;
	private String supplier;
	private int purchaseOrderPrice;
	
	public Product(String productID, int price, String supplier, int purchaseOrderPrice){
		this.productID = productID;
		this.price = price;
		this.supplier = supplier;
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

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
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
	
	public boolean changePrice(int price){
		if (price > 0)
		setPrice(price);
		return true;
	}
}
