package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.exception.CartFullException;
import hust.soict.hedspi.aims.exception.MediaNotFoundException;
import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.naming.LimitExceededException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private int qtyOrdered = 0;
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    public void addMedia(Media media) throws CartFullException {
        if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
            throw new CartFullException("Cart is full (Max: " + MAX_NUMBERS_ORDERED + ")");
        }
        itemsOrdered.add(media);
        qtyOrdered++;
    }

    public void removeMedia(Media media) throws MediaNotFoundException {
        if (!itemsOrdered.contains(media)) {
            throw new MediaNotFoundException("Media not found: " + media.getTitle());
        }
        itemsOrdered.remove(media);
        qtyOrdered--;
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("\n\n********************************CART********************************");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-40s %-25s %-15s %-15s\n", "No.", "Type", "Title", "Category", "ID", "Cost (VND)");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.printf("%-5d %-20s %-40s %-25s %-15d %-15.2f\n",
                    (i + 1),
                    itemsOrdered.get(i).getClass().getSimpleName(),
                    itemsOrdered.get(i).getTitle(),
                    itemsOrdered.get(i).getCategory(),
                    itemsOrdered.get(i).getId(),
                    itemsOrdered.get(i).getCost());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Total cost: %.2f VND\n", totalCost());
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("********************************************************************\n\n");

    }
    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        double totalCost = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            double price = itemsOrdered.get(i).getCost();

            System.out.println(itemsOrdered.get(i).printData());
            totalCost += price;
        }

        System.out.printf("Total cost: %.2f $\n", totalCost);
        System.out.println("***************************************************");
    }


    public void searchByTitle(String title) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered.get(i).getTitle().equals(title)) {
                System.out.println(itemsOrdered.get(i).printData());
                found = true;
            }
        }
        if(!found) System.out.println("Media which has title " + title + " not found");
    }

    public void searchByID(int id) {
        for (int i = 0; i < qtyOrdered; i++) {
            if(itemsOrdered.get(i).getId() == id){
                System.out.println(itemsOrdered.get(i).printData());
                return;
            }
        }
        System.out.println("Media which has id " + id + " not found");
    }

}
