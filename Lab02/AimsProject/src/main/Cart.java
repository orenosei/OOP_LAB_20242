package main;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private int qtyOrdered = 0;
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];

    public void addDigitalVideoDisc(DigitalVideoDisc disc){
        if(qtyOrdered < MAX_NUMBERS_ORDERED){
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("The disc " + disc.getTitle() +" has been added");
        }
        else{
            System.out.println("Cart is full");
        }
    }

//    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList){
//        for (DigitalVideoDisc disc : dvdList) {
//            if (qtyOrdered < MAX_NUMBERS_ORDERED) {
//                addDigitalVideoDisc(disc);
//            } else {
//                System.out.println("Cart is full");
//                break;
//            }
//        }
//    }

    public void addDigitalVideoDisc(DigitalVideoDisc... dvds) {
        for (DigitalVideoDisc disc : dvds) {
            if (qtyOrdered < MAX_NUMBERS_ORDERED) {
                addDigitalVideoDisc(disc);
            } else {
                System.out.println("Cart is full");
                break;
            }
        }
    }

    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            addDigitalVideoDisc(dvd1);
        } else {
            System.out.println("Cart is full. Cannot add " + dvd1.getTitle());
        }

        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            addDigitalVideoDisc(dvd2);
        } else {
            System.out.println("Cart is full. Cannot add " + dvd2.getTitle());
        }
    }


    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].equals(disc)) {
                found = true;
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;
                System.out.println("The DVD \"" + disc.getTitle() + "\" has been removed from the cart.");
                break;
            }
        }
        if (!found) {
            System.out.println("The DVD \"" + disc.getTitle() + "\" is not in the cart.");
        }
    }


    public float totalCost(){
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("----------------------------------------------------");
        System.out.printf("%-5s %-30s %-10s\n", "No.", "Title", "Cost (VND)");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.printf("%-5d %-30s %-10.2f\n", (i + 1), itemsOrdered[i].getTitle(), itemsOrdered[i].getCost());
        }
        System.out.println("----------------------------------------------------");
    }

}
