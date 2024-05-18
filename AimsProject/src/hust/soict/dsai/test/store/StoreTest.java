package hust.soict.dsai.test.store;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;

public class StoreTest {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<>();

        // Create some media
        DigitalVideoDisc dvd = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        CompactDisc cd = new CompactDisc(1, "Greatest Hits", "Music", 15.95f, 60, "Various Artists", "Queen");
        Book book = new Book(2, "Effective Java", "Programming", 45.00f, new ArrayList<>());

        cd.addTrack(new Track("Bohemian Rhapsody", 6));
        cd.addTrack(new Track("Another One Bites the Dust", 3));

        // Add media to the list
        mediae.add(dvd);
        mediae.add(cd);
        mediae.add(book);

        // Iterate through the list and print out the information
        for (Media m : mediae) {
            System.out.println(m.toString());
        }
    }
}
