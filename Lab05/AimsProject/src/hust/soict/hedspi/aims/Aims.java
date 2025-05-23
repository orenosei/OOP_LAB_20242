package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.CartFullException;
import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.exception.MediaNotFoundException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import java.util.ArrayList;
import java.util.Collections;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();

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
        System.out.println("1. See a media's details");
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
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }
    
    public static void updateStoreMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add a media");
        System.out.println("2. Delete a media");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2: ");
    }

    public static void viewStore() throws CartFullException {
        store.displayStore();
        storeMenu();
        String inputStore = System.console().readLine();

        while (!inputStore.equals("0")) {
            switch (inputStore) {
                case "1":
                    seeAMediaDetails();
                    break;
                case "2":
                    addAMediaToCart();
                    break;
                case "3":
                    playAMedia();
                    break;
                case "4":
                    cart.displayCart();
                    //cartMenu();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
            store.displayStore();
            storeMenu();
            inputStore = System.console().readLine();
        }
    }

    public static void seeAMediaDetails() throws CartFullException {
        System.out.println("Enter the title of the media: ");
        String title = System.console().readLine();

        boolean found = false;
        for (Media media : store.getItemsInStore()) {
            if (media.getTitle().equals(title)) {
                System.out.println(media.printData());
                found = true;
                mediaDetailsMenu();
                String inputMediaDetails = System.console().readLine();
                while (!inputMediaDetails.equals("0")) {
                    switch (inputMediaDetails) {
                        case "1":
                            cart.addMedia(media);
                            System.out.println("Number of medias in cart: " + cart.getQtyOrdered());
                            break;
                        case "2":
                            if (media instanceof Playable) {
                                try {
                                    ((Playable) media).play();
                                } catch (PlayerException e) {
                                    System.err.println("Error Message: " + e.getMessage());
                                    System.err.println("Exception Details: " + e.toString());
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("This media is not playable");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                    mediaDetailsMenu();
                    inputMediaDetails = System.console().readLine();
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Media not found!");
        }
    }

    public static void addAMediaToCart() throws CartFullException {
        boolean found = false;
        System.out.println("Enter the title of the media: ");
        String title = System.console().readLine();
        for (Media media : store.getItemsInStore()) {
            if (media.getTitle().equals(title)) {
                cart.addMedia(media);
                System.out.println("Number of medias in cart: " + cart.getQtyOrdered() );
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Media not found!");
        }
    }

    public static void playAMedia() {
        boolean found = false;
        System.out.println("Enter the title of the media: ");
        String title = System.console().readLine();
        for (Media media : store.getItemsInStore()) {
            if (media.getTitle().equals(title)) {
                if (media instanceof Playable) {
                    try {
                        ((Playable) media).play();
                    } catch (PlayerException e) {
                        System.err.println("Error Message: " + e.getMessage());
                        System.err.println("Exception Details: " + e.toString());
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("This media is not playable");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Media not found!");
        }
    }

    public static void updateStore() throws IllegalItemException {
        updateStoreMenu();
        String inputUpdateStore = System.console().readLine();

        while (!inputUpdateStore.equals("0")) {
            switch (inputUpdateStore) {
                case "1":
                    System.out.println("Enter media type (1-Book, 2-DVD, 3-CD): ");
                    String type = System.console().readLine();
                    System.out.println("Enter title: ");
                    String title = System.console().readLine();
                    System.out.println("Enter category: ");
                    String category = System.console().readLine();
                    System.out.println("Enter cost: ");
                    float cost = Float.parseFloat(System.console().readLine());

                    Media media = null;
                    switch (type) {
                        case "1":
                            System.out.println("Enter author: ");
                            String author = System.console().readLine();
                            media = new Book(title, category, cost, Collections.singletonList(author));
                            break;
                        case "2":
                            System.out.println("Enter director: ");
                            String director = System.console().readLine();
                            System.out.println("Enter length: ");
                            int length = Integer.parseInt(System.console().readLine());
                            media = new DigitalVideoDisc(title, category, director, length, cost);
                            break;
                        case "3":
                            System.out.println("Enter artist: ");
                            String artist = System.console().readLine();
                            System.out.println("Enter director: ");
                            director = System.console().readLine();
                            media = new CompactDisc(title, category, director, 0, cost, artist);
                            break;
                        default:
                            System.out.println("Invalid media type!");
                            break;
                    }
                    if (media != null) {
                        store.addMedia(media);
                        System.out.println("Media added successfully");
                    }
                    break;

                case "2":
                    System.out.println("Enter title to remove: ");
                    title = System.console().readLine();
                    boolean found = false;
                    for (Media m : store.getItemsInStore()) {
                        if (m.getTitle().equals(title)) {
                            store.removeMedia(m);
                            System.out.println("Media removed successfully");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Media not found!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            updateStoreMenu();
            inputUpdateStore = System.console().readLine();
        }
    }

    public static void seeCurrentCart() throws MediaNotFoundException {
        cart.displayCart();
        cartMenu();
        String inputCart = System.console().readLine();

        while (!inputCart.equals("0")) {
            switch (inputCart) {
                case "1":
                    System.out.println("Filter by (1-ID, 2-Title): ");
                    String filterType = System.console().readLine();
                    if (filterType.equals("1")) {
                        System.out.println("Enter ID to filter: ");
                        int id = Integer.parseInt(System.console().readLine());
                        cart.searchByID(id);
                    } else if (filterType.equals("2")) {
                        System.out.println("Enter title to filter: ");
                        String title = System.console().readLine();
                        cart.searchByTitle(title);
                    }
                    break;
                case "2":
                    System.out.println("Sort by (1-Title cost, 2-Cost title): ");
                    String sortType = System.console().readLine();
                    ArrayList<Media> mediaInCart = (ArrayList<Media>) cart.getItemsOrdered();
                    if (sortType.equals("1")) {
                        mediaInCart.sort(Media.COMPARE_BY_TITLE_COST);
                    } else if (sortType.equals("2")) {
                        mediaInCart.sort(Media.COMPARE_BY_COST_TITLE);
                    }
                    cart.displayCart();
                    break;
                case "3":
                    System.out.println("Enter title to remove: ");
                    String title = System.console().readLine();
                    for (Media media : cart.getItemsOrdered()) {
                        if (media.getTitle().equals(title)) {
                            cart.removeMedia(media);
                            break;
                        }
                    }
                    break;
                case "4":
                    System.out.println("Enter title to play: ");
                    title = System.console().readLine();
                    for (Media media : cart.getItemsOrdered()) {
                        if (media.getTitle().equals(title)) {
                            if (media instanceof Playable) {
                                try {
                                    ((Playable) media).play();
                                } catch (PlayerException e) {
                                    System.err.println("Error Message: " + e.getMessage());
                                    System.err.println("Exception Details: " + e.toString());
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("This media is not playable");
                            }
                            break;
                        }
                    }
                    break;
                case "5":
                    System.out.println("Order created!");
                    System.out.println("Empty current cart!");
                    cart = new Cart();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            cartMenu();
            inputCart = System.console().readLine();
        }
    }

    public void initializeStore() throws CartFullException, MediaNotFoundException, IllegalItemException {
        ArrayList<Media> mediaList = new ArrayList<>();

        mediaList.add(new Book("No Game No Life", "Light Novel", 15.99f, Collections.singletonList("Yuu Kamiya")));
        mediaList.add(new Book( "Sword Art Online", "Light Novel", 18.99f, Collections.singletonList("Reki Kawahara")));
        mediaList.add(new Book( "Re:Zero", "Light Novel", 20.99f, Collections.singletonList("Tappei Nagatsuki")));

        mediaList.add(new DigitalVideoDisc("Your Name", "Romance", "Makoto Shinkai", 112, 25.99f));
        mediaList.add(new DigitalVideoDisc("Spirited Away", "Fantasy", "Studio Ghibli", 125, 19.99f));

        CompactDisc kimiNoNaWaCD = new CompactDisc( "Kimi no Na wa OST", "Music", "RADWIMPS", 0, 12.99f, "RADWIMPS");
        kimiNoNaWaCD.addTrack(new Track("Zenzenzense", 4));
        kimiNoNaWaCD.addTrack(new Track("Sparkle", 6));
        mediaList.add(kimiNoNaWaCD);

        CompactDisc attackOnTitanCD = new CompactDisc("Attack on Titan OST", "Music", "Hiroyuki Sawano", 0, 14.99f, "Hiroyuki Sawano");
        attackOnTitanCD.addTrack(new Track("Vogel im Käfig", 5));
        attackOnTitanCD.addTrack(new Track("Call Your Name", 7));
        mediaList.add(attackOnTitanCD);

        for (Media media : mediaList) {
            store.addMedia(media);
        }

        String inputProgram = "";
        while (!inputProgram.equals("0")) {
            showMenu();
            inputProgram = System.console().readLine();

            switch (inputProgram) {
                case "1":
                    viewStore();
                    break;
                case "2":
                    updateStore();
                    break;
                case "3":
                    seeCurrentCart();
                    break;
                default:
                    if(!inputProgram.equals("0")) System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        try {
            Aims aims = new Aims();
            aims.initializeStore();
        } catch (Exception e) {
            System.err.println("Critical Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}