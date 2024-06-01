package hust.soict.dsai.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private Cart cart;
	private Store store;
	public static CartScreen cartInstance;
	
	public CartScreen(Store store_in, Cart cart_in) {
		super();
		this.store = store_in;
		this.cart = cart_in;
		JFXPanel fxPanel = new JFXPanel();
		this.add(fxPanel);
		
		this.setTitle("Cart");
		//this.setVisible(true);
		this.setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Runnable windowCloser = () -> SwingUtilities.invokeLater(
	            () -> this.setVisible(false)
	            
	        );
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(getClass()
							.getResource("/hust/soict/dsai/aims/screen/cart.fxml"));
					CartScreenController controller =
							new CartScreenController(store, cart);
					controller.setWindowCloser(windowCloser);
					loader.setController(controller);
					Parent root = loader.load();
					fxPanel.setScene(new Scene(root));
					
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
	}
}
