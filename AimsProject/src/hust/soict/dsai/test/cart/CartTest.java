package hust.soict.dsai.test.cart;
import hust.soict.dsai.aims.cart.*;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.screen.*;

public class CartTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create a new cart
		Cart cart = new Cart();
		
		//Create new dvd objects and add them to the cart
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		cart.addMedia(dvd1);
		
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
				"Science Fiction", "George Lucas", 87, 24.95f);
		cart.addMedia(dvd2);
		
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
				"Animation", "Roger Allerts", 90, 18.99f);
		cart.addMedia(dvd3);
		
		Book b1 = new Book("Aladin Book",
				"Animation", 18.99f);
		cart.addMedia(b1);
		cart.sortByCostTitle();
		//Test the print method
		cart.print();
		cart.sortByCostTitle();
		cart.print();
		//Test the search methods
	}
}
