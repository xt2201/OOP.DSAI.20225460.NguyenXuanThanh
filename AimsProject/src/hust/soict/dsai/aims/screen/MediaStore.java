package hust.soict.dsai.aims.screen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;


public class MediaStore extends JPanel {
	private static final long serialVersionUID = 1L;
	private Media media;
	private Cart cart;
	private Store store;
	private Runnable windowCloser;
	
	public MediaStore(Media media,Store store, Cart cart) {
		this.media = media;
		this.cart = cart;
		this.store = store;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel(media.getTitle());
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel cost = new JLabel(""+media.getCost()+" $");
		cost.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		ButtonListener btnListener = new ButtonListener();
		JButton addButton = new JButton("Add to cart");
		addButton.addActionListener(btnListener);
		container.add(addButton);
		
		JButton removeButton = new JButton("Remove from store");
		removeButton.addActionListener(btnListener);
		container.add(removeButton);
		
		if (media instanceof Playable) {
			JButton playButton = new JButton("Play");
			playButton.addActionListener(btnListener);
			container.add(playButton);
		}
		
		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(cost);
		this.add(Box.createVerticalGlue());
		this.add(container);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void setWindowCloser(Runnable windowCloser) {
		this.windowCloser = windowCloser;
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			if (s.equals("Play")) {
				try {
					((Playable) media).play();
					JDialog d = new JDialog();
		            // create a label
		            JLabel l = new JLabel("Playing " + media.getTitle());
		            d.add(l);
		            d.setSize(300, 300);
		            // set visibility of dialog
		            d.setVisible(true);
				} catch (PlayerException exp) {
					// TODO Auto-generated catch block
					exp.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), "ERROR: Media length is non-positive!", "Illegal Media length",
							JOptionPane.ERROR_MESSAGE);
				}
				
			} else if (s.equals("Add to cart")) {
				//this try-catch is for handling thread exception 
				//since the cart object moves from AWT thread to Javafx thread 
				//and it cause some exception
				try { 
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							cart.addMedia(media);		
						}
					});
				} catch(Exception exp) {
					cart.addMedia(media);
				}
			} else if (s.equals("Remove from store")) {
				store.removeMedia(media);
				windowCloser.run();
			}
		}
	}
}
