package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
	
	private List<String> authors = new ArrayList<String>();
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String title, String category, float cost) {
		super(title, category, cost);
	}

	public boolean addAuthor(String authorName) {
		if (authors.contains(authorName)) 
			return false;
		authors.add(authorName);
		return true;
	}
	
	public boolean removeAuthor(String authorName) {
		if (authors.contains(authorName)) {
			authors.remove(authorName);
			return true;
		}
		return false;
	}
	
	@Override 
	public String toString() {
	//to String method for printing out in Ex6 lab03
		String author_list = new String();
		for (String author : authors)
			author_list += author;
		return "Book  - "+this.getId()+" - "+this.getTitle()+" - "+this.getCategory()+ " -  by: " + author_list +" - "+this.getCost()+" $";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
            return false;
        if(!(obj instanceof Book))
            return false;
        return (((Media) obj).getTitle() == this.getTitle()); 
	}
}
