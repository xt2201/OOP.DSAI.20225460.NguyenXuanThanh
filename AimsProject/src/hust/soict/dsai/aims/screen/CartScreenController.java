package hust.soict.dsai.aims.screen;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.AuthorNumberException;
import hust.soict.dsai.aims.exception.DVDLengthException;
import hust.soict.dsai.aims.exception.InputException;
import hust.soict.dsai.aims.exception.NegativeCostException;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.exception.TrackNumberException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;


public class CartScreenController {
	private Cart cart;
	private Store store;
	private FilteredList<Media> filteredItems;
	private boolean filterById = true;
	private Media playObj;
	private Runnable windowCloser; 
	private float lucky = 0f;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;
    
    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField tfFilter;
    
    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;
    
    @FXML
    private Label costLabel;

	public CartScreenController(Store store, Cart cart) {
		super();
		this.store = store;
		this.cart = cart;
		
	}
	
	@FXML
	private void initialize( ) {
		
		colMediaTitle.setCellValueFactory(
				new PropertyValueFactory<Media, String>("title"));
		colMediacategory.setCellValueFactory(
				new PropertyValueFactory<Media, String>("category"));
		colMediaCost.setCellValueFactory(
				new PropertyValueFactory<Media, Float>("cost"));
		
		// this is for bonus exercise 10.
		filteredItems = new FilteredList<Media>(this.cart.getItemsOrdered());
		tblMedia.setItems(filteredItems);
		costLabel.setText(String.format("%.2f", cart.totalCost() - lucky)+"");
		
		btnPlay.setVisible(false);
		btnRemove.setVisible(false);
		
		tblMedia.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Media>() {
					
				@Override
				public void changed(ObservableValue<? extends Media> observable, Media oldValue,
						Media newValue) {
					if (newValue != null) {
						updateButtonBar(newValue);
					}
				}
			}
		);
		
		this.cart.getItemsOrdered().addListener(new ListChangeListener<Media>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Media> m) {
				costLabel.setText(String.format("%.2f", cart.totalCost()));
			}
		});
		
		tfFilter.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue) {
				showFilteredMedia(newValue);
			}
		});
	}

	protected void updateButtonBar(Media media) {
		btnRemove.setVisible(true);
		if (media instanceof Playable) {
			btnPlay.setVisible(true);
			playObj = media;
		} else {
			btnPlay.setVisible(false);
		}
	}
	
	@FXML
	void btnRemovePressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		cart.removeMedia(media);
	}
	
	@FXML
	void btnPlayPressed(ActionEvent even) {
		try {
			((Playable) playObj).play();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Playing your track");
			alert.setHeaderText("Player");
			alert.setContentText(playObj.getTitle()+" is playing");
			alert.showAndWait();
		} catch (PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Illegal length error");
			alert.setHeaderText("ERROR");
			alert.setContentText("ERROR: non-positive length!");
			alert.showAndWait();
		}
	}
	
	void showFilteredMedia(String str) {
		if (filterById == true)
			filteredItems.setPredicate(i -> (i.getId()+"").contains(str.toLowerCase()));
		else if (filterById == false)
			filteredItems.setPredicate(i -> i.getTitle().toLowerCase().contains(str.toLowerCase()));
	}
	
	@FXML
	void radioBtnTitlePressed(ActionEvent event) {
		filterById = false;
	}
	
	@FXML
	void radioBtnIdPressed(ActionEvent event) {
		filterById = true;
	}
	
	@FXML
	void placeOrderPressed(ActionEvent event) {
		this.cart.getItemsOrdered().clear();
	}
	
	@FXML 
	void switchStore(ActionEvent evt) {
		windowCloser.run();
		new StoreScreen(store, cart);
	}
	
	@FXML
	void addBook(ActionEvent evt) {
		Runnable r = () -> SwingUtilities.invokeLater(
	            () -> {
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
	            }
	        );
		r.run();
	}
	
	@FXML
	void addCD(ActionEvent evt) {
		Runnable r = () -> SwingUtilities.invokeLater(
	            () -> {
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
	            }
	        );
		r.run();
	}
	
	@FXML
	void addDVD(ActionEvent evt) {
		Runnable r = () -> SwingUtilities.invokeLater(
	            () -> {
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
					} catch (InputException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(new JFrame(), "ERROR: Wrong input!", "Illegal input",
								JOptionPane.ERROR_MESSAGE);
					}
	        		store.addMedia(dvd.getInput());
	            }
	        );
		r.run();
	}
	
	@FXML
	void sortCost(ActionEvent e) {
		cart.sortByCostTitle();
	}
	
	@FXML
	void sortTitle(ActionEvent e) {
		cart.sortByTitleCost();
	}
	
	@FXML
	void getLuckyItem(ActionEvent e) {
		Random rand = new Random();
		Media m = cart.getItemsOrdered().get(rand.nextInt(cart.getItemsOrdered().size()));
		System.out.println(m);
		lucky = m.getCost();
		costLabel.setText(String.format("%.2f", cart.totalCost() - lucky)+"");
	}

	public void setWindowCloser(Runnable windowCloser) {
		this.windowCloser = windowCloser;
	}
}