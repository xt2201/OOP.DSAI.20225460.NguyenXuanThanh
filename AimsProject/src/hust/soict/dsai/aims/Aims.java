package hust.soict.dsai.aims;

import java.util.Scanner;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Playable;


public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void viewStore() {
        store.printStore();
        storeMenu();
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                seeMediaDetails();
                break;
            case 2:
                addMediaToCart();
                break;
            case 3:
                playMedia();
                break;
            case 4:
                seeCurrentCart();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void seeMediaDetails() {
        System.out.println("Enter the title of the media:");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            System.out.println(media.toString());
            mediaDetailsMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    System.out.println("Media added to cart.");
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void addMediaToCart() {
        System.out.println("Enter the title of the media to add to cart:");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Media added to cart.");
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void playMedia() {
        System.out.println("Enter the title of the media to play:");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null && media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Media not found or cannot be played.");
        }
    }

    public static void seeCurrentCart() {
        cart.printCart();
        cartMenu();
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                filterMediasInCart();
                break;
            case 2:
                sortMediasInCart();
                break;
            case 3:
                removeMediaFromCart();
                break;
            case 4:
                playMediaInCart();
                break;
            case 5:
                placeOrder();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static void updateStore() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                addMediaToStore();
                break;
            case 2:
                removeMediaFromStore();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void addMediaToStore() {
        System.out.println("Enter the type of media (1: Book, 2: DVD, 3: CD):");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the title:");
        String title = scanner.nextLine();
        System.out.println("Enter the category:");
        String category = scanner.nextLine();
        System.out.println("Enter the cost:");
        float cost = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        Media media = null;
        switch (type) {
            case 1:
                media = new Book();
                ((Book) media).setTitle(title);
                ((Book) media).setCategory(category);
                ((Book) media).setCost(cost);
                store.addMedia(media);
                break;
            case 2:
                media = new DigitalVideoDisc(title, category, cost);
                store.addMedia(media);
                break;
            case 3:
                media = new CompactDisc();
                ((CompactDisc) media).setTitle(title);
                ((CompactDisc) media).setCategory(category);
                ((CompactDisc) media).setCost(cost);
                store.addMedia(media);
                break;
            default:
                System.out.println("Invalid type.");
                return;
        }
        System.out.println("Media added to store.");
    }

    public static void removeMediaFromStore() {
        System.out.println("Enter the title of the media to remove from store:");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        if (media != null) {
            store.removeMedia(media);
            System.out.println("Media removed from store.");
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void filterMediasInCart() {
        System.out.println("Filter options:");
        System.out.println("1. By ID");
        System.out.println("2. By title");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                System.out.println("Enter the ID:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                cart.filterById(id);
                break;
            case 2:
                System.out.println("Enter the title:");
                String title = scanner.nextLine();
                cart.filterByTitle(title);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void sortMediasInCart() {
        System.out.println("Sort options:");
        System.out.println("1. By title");
        System.out.println("2. By cost");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                cart.sortByTitleCost();
                break;
            case 2:
                cart.sortByCostTitle();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        cart.printCart();
    }

    public static void removeMediaFromCart() {
        System.out.println("Enter the title of the media to remove from cart:");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Media removed from cart.");
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void playMediaInCart() {
        System.out.println("Enter the title of the media to play:");
        String title = scanner.nextLine();
        Media media = cart.searchByTitle(title);
        if (media != null && media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Media not found or cannot be played.");
        }
    }

    public static void placeOrder() {
        System.out.println("Order placed. Thank you!");
        cart.clear();
    }
}
