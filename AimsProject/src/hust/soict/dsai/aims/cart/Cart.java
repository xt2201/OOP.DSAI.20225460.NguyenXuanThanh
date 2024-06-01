package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.Collections;

public class Cart {	
	public static final int MAX_NUMBER_ORDERED = 20;
	private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
	
	public Cart(){
		// do nothing since it's attributes are already initialized
	}

	public boolean addMedia(Media m) {
		if (itemsOrdered.contains(m))
			return false;
		itemsOrdered.add(m);
		return true;
	}
	
	public boolean removeMedia(Media m) {
		if (itemsOrdered.contains(m)) {
			itemsOrdered.remove(m);
			return true;
		}
		return false;
	}
	
	public float totalCost() {
		float s = 0.0f;
		for (Media m: itemsOrdered)
			s += m.getCost();
		return s;
	}
	
	public void sortByTitleCost() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
	}
	
	public void sortByCostTitle() {
		Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
	}
	

	public void print() {
		System.out.println("***********************CART***********************");
		System.out.println("Ordered Items:");
		int count = 0;
		for (Media m: itemsOrdered) {
			count+=1;
			System.out.print(count+". ");
			System.out.println(m);
		}
		System.out.println("Total cost: "+totalCost());
		System.out.println("***************************************************");
	}
	
	
/*
	public ArrayList<Media> search(int id) {
		ArrayList<Media> res = new ArrayList<Media>();
		for (Media d: itemsOrdered) {
			if (d.isMatch(id))
				res.add(d);
		}
		return res;
	}
	
	public ArrayList<Media> search(String title) {
		ArrayList<Media> res = new ArrayList<Media>();
		for (Media d: itemsOrdered) {
			if (d.isMatch(title))
				res.add(d);
		}
		return res;
	} 
*/
	
	public ObservableList<Media> getItemsOrdered() {
		return this.itemsOrdered;
	}
	
}