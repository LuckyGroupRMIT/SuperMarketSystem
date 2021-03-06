package main;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Sale implements Serializable{
	// A sale consists of a list of purchases and a list of applicable
	// discounts. Since globals aren't going to be used each sale needs to
	// be passed a
    private static final long serialVersionUID = 1113799434508676095L;
	private ArrayList<Purchase> purchases;
	private ArrayList<Discount> discounts;
	private Date date;
	private CustomerAccount customer;
	
	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}

	public ArrayList<Discount> getDiscounts() {
		return discounts;
	}

	
	public Sale() {
		this.purchases = new ArrayList<Purchase>();
		this.discounts = new ArrayList<Discount>();
		date = new Date();
	}
	
	public void recalculateDiscounts() {
		this.discounts.clear();
		for(int i = 0; i<this.purchases.size(); i++) {
			Purchase eachPurchase = this.purchases.get(i);
			ProductType productPurchased = eachPurchase.getProduct();
			ArrayList<Discount> discounts = productPurchased.getProductSpecificDiscounts();
			this.purchases.get(i).resetDiscounts();
			Discount mostApplicableDiscount = null;
			for (int j = 0; j<discounts.size(); j++) {
				Discount eachDiscount = discounts.get(j);
				double x = eachDiscount.apply(eachPurchase);
				if (x>0) {
					mostApplicableDiscount = eachDiscount;
				}
			}
			if (mostApplicableDiscount!=null) {
				this.discounts.add(mostApplicableDiscount);
			}
		}
	}
	public double getTotal() {
		double sum = 0;
		for(int i = 0; i<this.purchases.size(); i++) {
			sum += this.purchases.get(i).getDiscountedPrice();
		}
		return sum;
	}

	public void setCustomer(CustomerAccount c) {
		this.customer = c;
	}
	public CustomerAccount getCustomer() {
		return this.customer;
	}
	public void setDate(Date date) {
		this.date = date;
	}

    public Date getDate() {
        return date;
    }

	public double getUndiscountedTotal()
    {
        double sum = 0;
        for(Purchase purchase: purchases)
        {
            sum += purchase.getUndiscountedPrice();
        }
        return sum;
    }

	
	public void addPurchase(Purchase purchase) {
		this.purchases.add(purchase);
		this.recalculateDiscounts();
	}

	public void cancelOrder(int i) {
		this.purchases.remove(i-1);
		this.recalculateDiscounts();
	}


}
