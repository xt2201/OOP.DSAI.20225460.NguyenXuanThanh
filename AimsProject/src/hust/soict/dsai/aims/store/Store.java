package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import java.util.List;
import hust.soict.dsai.aims.media.Media;

public class Store {
    private List<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("The media has been added to the store.");
        } else {
            System.out.println("The media is already in the store.");
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("The media has been removed from the store.");
        } else {
            System.out.println("The media is not found in the store.");
        }
    }

    public void printStore() {
        System.out.println("Store:");
        for (Media media : itemsInStore) {
            System.out.println(media.toString());
        }
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }
}
