package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;

import java.util.Stack;

public class Store {
    private Stack<Media> itemsInStore;

    public Stack<Media> getItemsInStore() {
        return itemsInStore;
    }

    public Store() {
        this.itemsInStore = new Stack<>();
    }

    public void addMedia(Media item){
        itemsInStore.push(item);

    }
    public void removeMedia(Media item){
        itemsInStore.remove(item);
    }

    public void displayStore() {
        System.out.println("\n\n********************************STORE********************************");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-40s %-25s %-15s %-15s\n", "No.", "Type", "Title", "Category", "ID", "Cost (VND)");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        int count = 1;
        for (Media media : itemsInStore) {
            System.out.printf("%-5d %-20s %-40s %-25s %-15d %-15.2f\n",
                    count++,
                    media.getClass().getSimpleName(),
                    media.getTitle(),
                    media.getCategory(),
                    media.getId(),
                    media.getCost());
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("*************************************************************************\n\n*");
    }

}
