package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<>();

    public Book() {
        super();
    }

    public Book(int id, String title, String category, float cost, List<String> authors) {
        super();
        this.setId(id);
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
        this.authors = authors;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
        } else {
            System.out.println("Author already exists in the list.");
        }
    }

    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
        } else {
            System.out.println("Author not found in the list.");
        }
    }

    @Override
    public String toString() {
        return "Book [title=" + getTitle() + ", category=" + getCategory() + ", authors=" + authors + ", cost=" + getCost() + "]";
    }
}
