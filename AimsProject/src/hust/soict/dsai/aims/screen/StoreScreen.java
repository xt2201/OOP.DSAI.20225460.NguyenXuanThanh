package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.AuthorNumberException;
import hust.soict.dsai.aims.exception.DVDLengthException;
import hust.soict.dsai.aims.exception.InputException;
import hust.soict.dsai.aims.exception.NegativeCostException;
import hust.soict.dsai.aims.exception.TrackNumberException;
import hust.soict.dsai.aims.media.*;
import javax.swing.*;

/*
 	Some features of this application:
 	- Switch between store and cart screen
 	- Add/remove media to/from store, cart
 	- Play media in store and cart
 	- Sort cart
 	- Filter(Search) in cart
 	- Lucky item (get a free discount for 1 random item in cart)
 	- Place order
 	- And some feature to prompt input error from user
 	
 */

public class StoreScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	private Store store;
	private Cart cart;
	
	public StoreScreen(Store store, Cart cart) {
		this.store = store;
		this.cart = cart; 
	
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setVisible(true);
		setTitle("Store");
		setSize(1024, 768); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	JMenuBar createMenuBar() {
		JMenu menu =  new JMenu("Options");
		
		JMenu smUpdateStore = new JMenu("Update Store");
		JMenuItem addBook = new JMenuItem("Add Book");
		addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBook b = new AddBook();
				try {
					b.addBook();
        		} catch (NegativeCostException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Media cost is non-positive!", "Illegal Media cost",
							JOptionPane.ERROR_MESSAGE);
				} catch (AuthorNumberException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Number of authors is non-positive!", "Illegal Author number",
							JOptionPane.ERROR_MESSAGE);
				} catch (InputException e3) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Wrong input!", "Illegal input",
							JOptionPane.ERROR_MESSAGE);
					e3.printStackTrace();
				}
				store.addMedia(b.getInput());
				new StoreScreen(store, cart);
				dispose();
			}
		});
		smUpdateStore.add(addBook);
		JMenuItem addCD = new JMenuItem("Add CD");
		addCD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCD cd = new AddCD();
				try {
					cd.addCD();
        		} catch (NegativeCostException e1) {
        			JOptionPane.showMessageDialog(new JFrame(), "ERROR: Media cost is non-positive!", "Illegal Media cost",
							JOptionPane.ERROR_MESSAGE);
        			e1.printStackTrace();
        		} catch (TrackNumberException e2) {
        			
        			JOptionPane.showMessageDialog(new JFrame(), "ERROR: Track number is non-positive!", "Illegal track number",
							JOptionPane.ERROR_MESSAGE);
        			e2.printStackTrace();
				} catch (InputException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Wrong input!", "Illegal input",
							JOptionPane.ERROR_MESSAGE);
				}
				store.addMedia(cd.getInput());
				new StoreScreen(store, cart);
				dispose();
			}
		});
		smUpdateStore.add(addCD);
		JMenuItem addDVD = new JMenuItem("Add DVD");
		addDVD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDVD dvd = new AddDVD();
				try {
					dvd.addDVD();
        		} catch (NegativeCostException e1) {
        			JOptionPane.showMessageDialog(new JFrame(), "ERROR: Media cost is non-positive!", "Illegal Media cost",
							JOptionPane.ERROR_MESSAGE);
        			e1.printStackTrace();
        		} catch (DVDLengthException e2) {
        			JOptionPane.showMessageDialog(new JFrame(), "ERROR: Media length is non-positive!", "Illegal Media length",
							JOptionPane.ERROR_MESSAGE);
        			e2.printStackTrace();
				} catch (InputException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Wrong input!", "Illegal input",
							JOptionPane.ERROR_MESSAGE);
				}
				store.addMedia(dvd.getInput());
				new StoreScreen(store, cart);
				dispose();
			}
		});
		smUpdateStore.add(addDVD);
		
		menu.add(smUpdateStore);
		menu.add(new JMenuItem("View store"));
		JMenuItem viewCart = new JMenuItem("View cart");
		viewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CartScreen(store, cart).setVisible(true);
				dispose();
			}
			
		});
		menu.add(viewCart);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);
		
		
		return menuBar;
	}
	
	JPanel createHeader() {
		
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);
		
		JButton switchCart = new JButton("View cart");
		switchCart.setPreferredSize(new Dimension(100, 50));
		switchCart.setMaximumSize(new Dimension(100, 50));
		switchCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CartScreen(store, cart).setVisible(true);
				dispose();
			}
			
		});
		
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(switchCart);
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return header;
	}
	
	JPanel createCenter() {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));
		
		ArrayList<Media> mediaInStore = store.getItemsInStore();
		
		Runnable windowCloser = () -> SwingUtilities.invokeLater(
	            () -> {
	            	this.dispose();
	            	new StoreScreen(store, cart);
	            }
	            
	        );
		for (int i = 0; i < mediaInStore.size(); i++) {
			MediaStore cell = new MediaStore(mediaInStore.get(i), store, cart);
			cell.setWindowCloser(windowCloser);
			center.add(cell);
		}
		
		return center;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create a Store instance and 3 DVDs
		Store store = new Store();	
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
				"Science Fiction", "George Lucas", 87, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
				"Animation", "Roger Allerts", 90,  18.99f);
		Book b1 = new Book("Aladin", "Tales", 20f);
		//here I add DVDs and test add method
 		store.addMedia(dvd1);
		store.addMedia(dvd2);
		store.addMedia(dvd3);
		store.addMedia(b1);
		
		store.printStore();

		
		// Creating book instances
        Media book1 = new Book("The Great Gatsby", "Fiction", 19.99f);
        Media book2 = new Book("Sapiens: A Brief History of Humankind", "Non-fiction", 15.99f);
        Media book3 = new Book("The Power of Now", "Self-help", 12.99f);

        // Adding books to the store
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);
        
     // Creating CD instances
        Media media1 = new CompactDisc("Abbey Road", "Rock", 19.99f, "The Beatles", "The Beatles");
        Media media2 = new CompactDisc("Thriller", "Pop", 14.99f, "John Landis", "Michael Jackson");
        Media media3 = new CompactDisc("Back in Black", "Rock", 12.99f, "Robert John \"Mutt\" Lange", "AC/DC");

        // Adding CDs to the store
        store.addMedia(media1);
        store.addMedia(media2);
        store.addMedia(media3);
		Cart cart = new Cart();
		
		new StoreScreen(store, cart).setVisible(true);

	}
	
}
