package hust.soict.dsai.test.store;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.*;

public class StoreTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create a Store instance and 3 DVDs
		Store store = new Store();	
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
				"Science Fiction", "George Lucas", -87, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
				"Animation", "Roger Allerts", 90,  18.99f);
		Book b1 = new Book("Aladin", "Tales", 20f);
		//here I add DVDs and test add method
 		store.addMedia(dvd1);
		store.addMedia(dvd2);
		store.addMedia(dvd3);
		store.addMedia(b1);
		
		store.printStore();
		try {
			dvd2.play();
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), "ERROR: DVD length is non-positive!", "Illegal DVD length",
					JOptionPane.ERROR_MESSAGE);
		}
		System.out.println("done");
	}

}
