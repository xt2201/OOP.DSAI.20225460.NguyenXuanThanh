package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import java.util.List;
import hust.soict.dsai.aims.media.Media;

public class Store {
	private List<Media> itemsInStore = new ArrayList<Media>();
	
	public boolean addMedia(Media m) {
		if (itemsInStore.contains(m))
			return false;
		itemsInStore.add(m);
		return true;
	}
	
	public boolean removeMedia(Media m) {
		if (itemsInStore.contains(m)) {
			itemsInStore.remove(m);
			return true;
		}
		return false;
	}
	
	public void printStore() {
		//this method is not in the instruction
		//only used for testing
		System.out.println("List of items in Store: ");
		int count = 0;
		for (Media d: itemsInStore) {
			count+=1;
			System.out.print(count+". ");
			System.out.println(d);
		}
	}
	
	public ArrayList<Media> search(String title) {
		ArrayList<Media> m = new ArrayList<Media>();
		for (Media d: itemsInStore) {
			if ((d.isMatch(title))&&(!(m.contains(d))))
				m.add(d);
		}
		return m;
	}
	
	public ArrayList<Media> getItemsInStore(){
		return (ArrayList<Media>) itemsInStore;
	}
	 
}
