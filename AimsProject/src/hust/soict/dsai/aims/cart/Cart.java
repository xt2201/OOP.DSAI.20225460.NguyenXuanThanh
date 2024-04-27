package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;
	private DigitalVideoDisc itemOrdered[] = 
			new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
	private int qtyOrdered = 0; // Initialize qtyOrdered to 0

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemOrdered[qtyOrdered] = disc;
            qtyOrdered++;
//            System.out.println("The disc has been added.");
        } else {
            System.out.println("The cart is almost full.");
        }
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemOrdered[i] == disc) {
                // Shift elements to fill the removed item's position
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemOrdered[j] = itemOrdered[j + 1];
                }
                itemOrdered[qtyOrdered - 1] = null; // Set the last element to null
                qtyOrdered--;
                System.out.println("The disc has been removed.");
                return;
            }
        }
        System.out.println("The disc is not found in the cart.");
    }

    public float totalCost() {
        float total = 0.0f;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemOrdered[i].getCost();
        }
        return total;
    }
    
////    Create a new method with the same name but a different type of parameter.
//    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
//        for (DigitalVideoDisc disc : dvdList) {
//            addDigitalVideoDisc(disc);
//        }
//    }
    
////    Add a method addDigitalVideoDisc which allows to pass an arbitrary number of arguments for dvd
//    public void addDigitalVideoDisc(DigitalVideoDisc... dvds) {
//        for (DigitalVideoDisc disc : dvds) {
//            addDigitalVideoDisc(disc);
//        }
//    }
    
//    Create new method has two parameters as following
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addDigitalVideoDisc(dvd1);
        addDigitalVideoDisc(dvd2);
    }
    
//    Create a new method to print the list of ordered items of a cart, the price of each item, and the total price.
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < qtyOrdered; i++) {
            DigitalVideoDisc disc = itemOrdered[i];
            System.out.println((i + 1) + ". DVD - " + disc.getTitle() + " - " + disc.getCategory() + " - " + disc.getDirector() + " - " + disc.getLength() + ": " + disc.getCost() + " $");
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***************************************************");
    }
    
//    Search for DVDs in the cart by ID and display the search results. 
	public void search(int id) {
		System.out.println("Your search result: ");
		int count = 0;
		for (DigitalVideoDisc d: itemOrdered) {
			if (d == null) {
				break;
			} 
			if (d.isMatch(id)) {
				count+=1;
				System.out.print(count+". ");
				System.out.println(d);
			}
		}
		if (count == 0) {
			System.out.println("no match found");
		}
	}
    
//	Search for DVDs in the cart by title and print the results
	public void search(String title) {
		System.out.println("Your search result: ");
		int count = 0;
		for (DigitalVideoDisc d: itemOrdered) {
			if (d == null) {
				break;
			} 
			if (d.isMatch(title)) {
				count+=1;
				System.out.print(count+". ");
				System.out.println(d);
			}
		}
		if (count == 0) {
			System.out.println("no match found");
		}
	}

}
