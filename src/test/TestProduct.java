package main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
public class TestProduct
{
	
	private Product product;
	private Product apple;
	@Before
	public void setUp()
    {
//		product = new Product(null, 5, null, 50);
//		String app = "apple";
//		String aust = "australia";
//		apple = new Product(app, 10, aust, 20);
	}

	@Test
	public void testChangePrice()
    {
		product.setPrice(10);
		assertEquals(10, product.getPrice());
	}
	
	@Test
	public void testValidID()
    {
//		assertEquals(5, product.getProductID().length());
	}
	
	@Test
	public void testValidPrice()
    {
		assertTrue(apple.getPrice()>0);
		assertTrue(product.getPrice()<0); 	
	}
	
	
}
