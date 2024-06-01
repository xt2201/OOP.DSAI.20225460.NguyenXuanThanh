package hust.soict.dsai.aims.screen;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import hust.soict.dsai.aims.exception.DVDLengthException;
import hust.soict.dsai.aims.exception.InputException;
import hust.soict.dsai.aims.exception.NegativeCostException;
import hust.soict.dsai.aims.media.*;

public class AddDVD {
	DigitalVideoDisc dvd;
	
	public AddDVD() {}
	
	public void addDVD() throws InputException {
		JPanel p = new JPanel();
		JTextField title = new JTextField(10);
		JTextField category = new JTextField(10);
		JTextField cost = new JTextField(10);
		JTextField director = new JTextField(10);
		JTextField length = new JTextField(10);
		
		p.add(new JLabel("Title :"));
		p.add(title);
		p.add(new JLabel("Category : "));
		p.add(category);
		p.add(new JLabel("Cost : "));
		p.add(cost);
		p.add(new JLabel("Director : "));
		p.add(director);
		p.add(new JLabel("Length : "));
		p.add(length);
		
		JOptionPane.showConfirmDialog(null, p, "Input Media : ", JOptionPane.OK_CANCEL_OPTION);
		
		
		try {
			float inp_cost =  Float.parseFloat(cost.getText());
			if (inp_cost >= 0) {
				int inp_length = Integer.parseInt(length.getText());
				
				if (inp_length >= 0) {
					dvd = new DigitalVideoDisc(title.getText(), category.getText(), director.getText(), Integer.parseInt(length.getText()), Float.parseFloat(cost.getText()));
				} else {
					throw new DVDLengthException();
				}
			} else {
				throw new NegativeCostException();
			}
		} catch (NumberFormatException exp) {
			throw new InputException();
		} catch (NullPointerException exp) {
			throw new InputException();
		}
	}
	
	public DigitalVideoDisc getInput() {
		return dvd;
	}
}
