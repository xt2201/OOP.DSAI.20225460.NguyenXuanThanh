package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Store {
	private static final int MAX_ITEM = 1000; //limit maximum number of items = 1000
	private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_ITEM];
	private int nbProduct = 0; //number of product selling
	
	public void addDVD(DigitalVideoDisc disc) {
		if (nbProduct == MAX_ITEM) {
			//when reach capacity limit
			return;
		}
		itemsInStore[nbProduct] = disc;
		nbProduct+=1;
	}
	
	public void removeDVD(DigitalVideoDisc disc) {
		if (nbProduct == 0) {
			return;
		}
		int pos = -1; 
		for (int i = 0; i < nbProduct; i++) {
			if (itemsInStore[i].equals(disc)) {
				pos = i;
				break;
			}
		}
		if (pos == -1) {
			return;
		}
		for (int i = pos; i < nbProduct - 1; i++) {
			itemsInStore[i] = itemsInStore[i+1];
		}
		itemsInStore[nbProduct-1] = null;
		nbProduct-=1;
	}
	
	public void printStore() {
		//this method is not in the instruction
		//only used for testing
		System.out.println("List of items in Store: ");
		int count = 0;
		for (DigitalVideoDisc d: itemsInStore) {
			if (d == null) {
				break;
			}
			count+=1;
			System.out.print(count+". ");
			System.out.println(d);
		}
	}
	
}
