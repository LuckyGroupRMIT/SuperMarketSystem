package main;

import java.io.Serializable;

// Quantity refers to the number of items purchased
// Weight refers to the weight in grams
// Both are represented using Ints.
public enum PricingMethod implements Serializable {
	WEIGHT, QUANTITY
}
