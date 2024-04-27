package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Aims {
	
	public static void main(String[] args) {
		//Create a new cart
		Cart anOrder = new Cart();
		
		//Create new dvd objects and add them to the cart
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		anOrder.addDigitalVideoDisc(dvd1);
		
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
				"Science FIction", "George Lucas", 87, 24.95f);
		anOrder.addDigitalVideoDisc(dvd2);
		
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
				"Animation", 18.99f);
		anOrder.addDigitalVideoDisc(dvd3);
		
		//print total cost of the items in the cart
		System.out.println("Total Cost is: ");
		System.out.println(anOrder.totalCost());
	}

}

//public class Aims {
//    public static void main(String[] args) {
//        // Create some DigitalVideoDisc objects
//        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Title 1", "Category 1", 10.99f);
//        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Title 2", "Category 2", 12.99f);
//        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Title 3", "Category 3", 9.99f);
//
//        // Create a Cart object
//        Cart cart = new Cart();
//
//        // Add the DVDs to the cart
//        cart.addDigitalVideoDisc(dvd1);
//        cart.addDigitalVideoDisc(dvd2);
//        cart.addDigitalVideoDisc(dvd3);
//
//        // Remove a DVD from the cart
//        cart.removeDigitalVideoDisc(dvd2);
//
//        // Check the total cost of the cart
//        float totalCost = cart.totalCost();
//        System.out.println("Total cost of the cart: $" + totalCost);
//    }
//}