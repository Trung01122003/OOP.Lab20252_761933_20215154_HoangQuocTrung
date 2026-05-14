package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;

import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-populate some items for testing
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        
        Book book1 = new Book("Harry Potter", "Fantasy", 20.00f);
        book1.addAuthor("J.K. Rowling");
        store.addMedia(book1);

        CompactDisc cd1 = new CompactDisc("Greatest Hits", "Music", "Various", 15.00f, "Queen");
        Track track1 = new Track("Bohemian Rhapsody", 6);
        Track track2 = new Track("We Will Rock You", 3);
        cd1.addTrack(track1);
        cd1.addTrack(track2);
        store.addMedia(cd1);

        int choice;
        do {
            showMenu();
            choice = getIntInput();
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
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
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

    private static void viewStore() {
        store.print();
        int choice;
        do {
            storeMenu();
            choice = getIntInput();
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
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void updateStore() {
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        System.out.println("0. Back");
        int choice = getIntInput();
        if (choice == 1) {
            System.out.println("Enter type (1. Book, 2. DVD, 3. CD): ");
            int type = getIntInput();
            System.out.println("Enter title: ");
            String title = scanner.nextLine();
            System.out.println("Enter category: ");
            String category = scanner.nextLine();
            System.out.println("Enter cost: ");
            float cost = getFloatInput();

            Media m = null;
            if (type == 1) {
                m = new Book(title, category, cost);
            } else if (type == 2) {
                System.out.println("Enter director: ");
                String director = scanner.nextLine();
                System.out.println("Enter length: ");
                int length = getIntInput();
                m = new DigitalVideoDisc(title, category, director, length, cost);
            } else if (type == 3) {
                System.out.println("Enter director: ");
                String director = scanner.nextLine();
                System.out.println("Enter artist: ");
                String artist = scanner.nextLine();
                m = new CompactDisc(title, category, director, cost, artist);
            }
            if (m != null) store.addMedia(m);
        } else if (choice == 2) {
            System.out.println("Enter title to remove: ");
            String title = scanner.nextLine();
            Media m = store.searchByTitle(title);
            if (m != null) {
                store.removeMedia(m);
            } else {
                System.out.println("Media not found in store.");
            }
        }
    }

    private static void seeCurrentCart() {
        cart.print();
        int choice;
        do {
            cartMenu();
            choice = getIntInput();
            switch (choice) {
                case 1:
                    filterCart();
                    break;
                case 2:
                    sortCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromCart();
                    break;
                case 5:
                    System.out.println("An order is created. Cart will be emptied.");
                    cart.emptyCart();
                    return; // Go back after placing order
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void seeMediaDetails() {
        System.out.println("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media m = store.searchByTitle(title);
        if (m != null) {
            System.out.println(m.toString());
            int choice;
            do {
                mediaDetailsMenu();
                choice = getIntInput();
                switch (choice) {
                    case 1:
                        cart.addMedia(m);
                        break;
                    case 2:
                        if (m instanceof Playable) {
                            ((Playable) m).play();
                        } else {
                            System.out.println("This media cannot be played.");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 0);
        } else {
            System.out.println("Media not found.");
        }
    }

    private static void addMediaToCart() {
        System.out.println("Enter the title of the media to add to cart: ");
        String title = scanner.nextLine();
        Media m = store.searchByTitle(title);
        if (m != null) {
            cart.addMedia(m);
            // Count DVDs
            int dvdCount = 0;
            for (Media cartItem : cart.getItemsOrdered()) {
                if (cartItem instanceof DigitalVideoDisc) dvdCount++;
            }
            System.out.println("Number of DVDs in cart: " + dvdCount);
        } else {
            System.out.println("Media not found.");
        }
    }

    private static void playMedia() {
        System.out.println("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media m = store.searchByTitle(title);
        if (m != null) {
            if (m instanceof Playable) {
                ((Playable) m).play();
            } else {
                System.out.println("This media cannot be played.");
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    private static void filterCart() {
        System.out.println("1. By ID");
        System.out.println("2. By Title");
        int choice = getIntInput();
        if (choice == 1) {
            System.out.println("Enter ID: ");
            int id = getIntInput();
            cart.searchById(id);
        } else if (choice == 2) {
            System.out.println("Enter Title: ");
            String title = scanner.nextLine();
            cart.searchByTitle(title);
        }
    }

    private static void sortCart() {
        System.out.println("1. By Title");
        System.out.println("2. By Cost");
        int choice = getIntInput();
        if (choice == 1) {
            cart.sortByTitleCost();
            cart.print();
        } else if (choice == 2) {
            cart.sortByCostTitle();
            cart.print();
        }
    }

    private static void removeMediaFromCart() {
        System.out.println("Enter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media m = cart.searchByTitleRet(title);
        if (m != null) {
            cart.removeMedia(m);
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    private static void playMediaFromCart() {
        System.out.println("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media m = cart.searchByTitleRet(title);
        if (m != null) {
            if (m instanceof Playable) {
                ((Playable) m).play();
            } else {
                System.out.println("This media cannot be played.");
            }
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    private static int getIntInput() {
        try {
            int input = Integer.parseInt(scanner.nextLine());
            return input;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static float getFloatInput() {
        try {
            float input = Float.parseFloat(scanner.nextLine());
            return input;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
