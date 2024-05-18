package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(String title) {
        super();
        this.setTitle(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        this(title);
        this.setCategory(category);
        this.setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this(title, category, cost);
        this.setDirector(director);
        this.setLength(length);
    }

    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

    @Override
    public String toString() {
        return "DigitalVideoDisc [title=" + getTitle() + ", category=" + getCategory() + ", director=" + getDirector() +
                ", length=" + getLength() + ", cost=" + getCost() + "]";
    }
}
