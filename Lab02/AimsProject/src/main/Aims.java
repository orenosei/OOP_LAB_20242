package main;

public class Aims {
    public static void main(String[] args) {
//        System.out.println("Hello, World!");
        Cart anOrder = new Cart();

        DigitalVideoDisc disc1 = new DigitalVideoDisc("Attack on Titan","Drama","Isayama Hajime",235,20.20f);
        DigitalVideoDisc disc2 = new DigitalVideoDisc("Gotoubun no Hanayome","Romance","Haruba Negi",100,17.33f);
        DigitalVideoDisc disc3 = new DigitalVideoDisc("Nisekoi","School life","Komi Naoshi",550,50.00f);
        DigitalVideoDisc disc4 = new DigitalVideoDisc("Your Name", "Romance", "Makoto Shinkai", 107, 15.50f);
        DigitalVideoDisc disc5 = new DigitalVideoDisc("Demon Slayer", "Action", "Koyoharu Gotouge", 170, 22.75f);
        DigitalVideoDisc disc6 = new DigitalVideoDisc("One Piece", "Adventure", "Eiichiro Oda", 1200, 45.99f);
        DigitalVideoDisc disc7 = new DigitalVideoDisc("Spirited Away", "Fantasy", "Hayao Miyazaki", 125, 30.00f);
        DigitalVideoDisc disc8 = new DigitalVideoDisc("Death Note", "Thriller", "Tsugumi Ohba", 900, 40.80f);
        DigitalVideoDisc disc9 = new DigitalVideoDisc("Steins;Gate", "Sci-Fi", "Chiyomaru Shikura", 150, 18.90f);
        DigitalVideoDisc disc10 = new DigitalVideoDisc("Naruto Shippuden", "Action", "Masashi Kishimoto", 500, 25.00f);

        anOrder.addDigitalVideoDisc(disc1);
        anOrder.addDigitalVideoDisc(disc2);
        anOrder.addDigitalVideoDisc(disc3);
        anOrder.addDigitalVideoDisc(disc4);
        anOrder.addDigitalVideoDisc(disc5);

        anOrder.removeDigitalVideoDisc(disc3);

        anOrder.addDigitalVideoDisc(disc6);
        anOrder.addDigitalVideoDisc(disc7);
        anOrder.addDigitalVideoDisc(disc8);

        anOrder.removeDigitalVideoDisc(disc9);

        anOrder.displayCart();
        System.out.printf("%35s: %.2f VND\n", "Total Cost", anOrder.totalCost());

    }
}