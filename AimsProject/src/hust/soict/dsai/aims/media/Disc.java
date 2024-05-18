package hust.soict.dsai.aims.media;

public class Disc extends Media {
    private int length;
    private String director;

    public Disc() {
        super();
    }

    public Disc(int id, String title, String category, float cost, int length, String director) {
        super();
        this.setId(id);
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
        this.length = length;
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
