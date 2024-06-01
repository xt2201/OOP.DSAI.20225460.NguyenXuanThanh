package hust.soict.dsai.aims.screen;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.exception.AuthorNumberException;
import hust.soict.dsai.aims.exception.InputException;
import hust.soict.dsai.aims.exception.NegativeCostException;
import hust.soict.dsai.aims.media.Book;

public class AddBook {
	Book b;
	
	public AddBook() {}
	
	public void addBook() throws InputException {
		JPanel p = new JPanel();
		JTextField title = new JTextField(10);
		JTextField category = new JTextField(10);
		JTextField cost = new JTextField(10);
		JTextField n = new JTextField(10);
		
		p.add(new JLabel("Title :"));
		p.add(title);
		p.add(new JLabel("Category : "));
		p.add(category);
		p.add(new JLabel("Cost : "));
		p.add(cost);
		p.add(new JLabel("Number of authors: "));
		p.add(n);
		
		
		
		JOptionPane.showConfirmDialog(null, p, "Input Media : ", JOptionPane.OK_CANCEL_OPTION);
		
		try {
			float inp_cost =  Float.parseFloat(cost.getText());
			if (inp_cost >= 0) {
				int inp_n = Integer.parseInt(n.getText());
				b = new Book(title.getText(), category.getText(),inp_cost);
				
				if (inp_n > 0) {
					for (int i = 0; i < inp_n; i++) {
						String m = JOptionPane.showInputDialog((i+1)+". Author name", "John Doe");
						b.addAuthor(m);
					}
				} else {
					throw new AuthorNumberException();
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
	
	public Book getInput() {
		return b;
	}
}
