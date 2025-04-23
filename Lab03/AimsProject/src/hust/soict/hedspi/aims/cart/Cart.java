package hust.soict.hedspi.aims.cart;

import java.util.ArrayList;

import hust.soict.hedspi.aims.media.Media;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private int qtyOrdered = 0;
    public int getQtyOrdered() {
        return qtyOrdered;
    }
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();
    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void addMedia(Media media){
        if(qtyOrdered < MAX_NUMBERS_ORDERED){
            itemsOrdered.add(media);
            qtyOrdered++;
            System.out.println(media.getTitle() + " added to cart");
        }
        else System.out.println("Cart is full");
    }

    public void removeMedia(Media media){
        if(itemsOrdered.contains(media)){
            itemsOrdered.remove(media);
            qtyOrdered--;
            System.out.println(media.getTitle() + " removed from cart");
        }
        else System.out.println(media.getTitle() + " not found");
    }

    public float totalCost(){
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered.get(i).getCost();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-15s %-40s %-20s %-15s %-15s\n", "No.", "Type", "Title", "Category", "ID", "Cost (VND)");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.printf("%-8d %-15s %-40s %-20s %-15d %-15.2f\n",
                    (i + 1),
                    itemsOrdered.get(i).getClass().getSimpleName(),
                    itemsOrdered.get(i).getTitle(),
                    itemsOrdered.get(i).getCategory(),
                    itemsOrdered.get(i).getID(),
                    itemsOrdered.get(i).getCost());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("Total cost: %.2f VND\n", totalCost());
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }
    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        double totalCost = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            double price = itemsOrdered.get(i).getCost();

            System.out.println("DVD - " +  itemsOrdered.get(i).toString());
            totalCost += price;
        }

        System.out.printf("Total cost: %.2f $\n", totalCost);
        System.out.println("***************************************************");
    }


    public void searchByTitle(String title) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered.get(i).getTitle().equals(title)) {
                System.out.println(itemsOrdered.get(i).toString());
                found = true;
            }
        }
        if(!found) System.out.println("DVD which has title " + title + " not found");
    }

    public void searchByID(int id) {
        for (int i = 0; i < qtyOrdered; i++) {
            if(itemsOrdered.get(i).getID() == id){
                System.out.println(itemsOrdered.get(i).toString());
                return;
            }
        }
        System.out.println("DVD which has id " + id + " not found");
    }

}
