package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.PricingMethod;
import main.ProductType;
import main.Purchase;
import main.Sale;
import main.PricingMethod;
import main.BulkDiscount;

public class DiscountSystemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDiscountsAreApplied() {
		ProductType x = new ProductType("Id", "name", "supplier",10,PricingMethod.QUANTITY,10);
		BulkDiscount y = new BulkDiscount("50% Off when you buy 5 or more", 5, 0.5);
		x.addProductDiscount(y);
		Sale z = new Sale();
		Purchase p = new Purchase(x, 5);
		z.addPurchase(p);
		System.out.print(z.getTotal());
		assertTrue(z.getTotal()==25);
	}
	public void testPrecedenceOfDiscounts() {
		// A 10% discount each 5 and 30% discount each 8 should not stack
		// Only the minimum should be taken
		fail("Not yet implemented");
	}

}
