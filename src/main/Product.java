package main;

public class Product {
<<<<<<< HEAD
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
=======
	// Get Price methods should raise an exception if a price is requested
	// That they can't handle. e.g Brooms are asked for price by weight when
	// they are priced by quantity

	public float getPriceByQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}
	public float getPriceByWeight() {
		return 0;
		
	}

>>>>>>> 07038a92089f1afe53d20ffe0835a6378583385e
}
