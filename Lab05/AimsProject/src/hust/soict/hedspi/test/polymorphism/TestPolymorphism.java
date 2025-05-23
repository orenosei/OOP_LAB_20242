package hust.soict.hedspi.test.polymorphism;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.media.*;

import java.util.ArrayList;
import java.util.Collections;

public class TestPolymorphism {
    public static void main(String[] args) throws IllegalItemException {
        ArrayList<Media> mediaList = new ArrayList<>();

        mediaList.add(new Book("No Game No Life", "Light Novel", 15.99f, Collections.singletonList("Yuu Kamiya")));
        mediaList.add(new Book( "Sword Art Online", "Light Novel", 18.99f, Collections.singletonList("Reki Kawahara")));
        mediaList.add(new Book( "Re:Zero - Starting Life in Another World", "Light Novel", 20.99f, Collections.singletonList("Tappei Nagatsuki")));

        mediaList.add(new DigitalVideoDisc("Your Name", "Romance", "Makoto Shinkai", 112, 25.99f));
        mediaList.add(new DigitalVideoDisc("Spirited Away", "Fantasy", "Studio Ghibli", 125, 19.99f));

        CompactDisc kimiNoNaWaCD = new CompactDisc( "Kimi no Na wa OST", "Music", "RADWIMPS", 0, 12.99f, "RADWIMPS");
        kimiNoNaWaCD.addTrack(new Track("Zenzenzense", 4));
        kimiNoNaWaCD.addTrack(new Track("Sparkle", 6));
        mediaList.add(kimiNoNaWaCD);

        CompactDisc attackOnTitanCD = new CompactDisc( "Attack on Titan OST", "Music", "Hiroyuki Sawano", 0, 14.99f, "Hiroyuki Sawano");
        attackOnTitanCD.addTrack(new Track("Vogel im Käfig", 5));
        attackOnTitanCD.addTrack(new Track("Call Your Name", 7));
        mediaList.add(attackOnTitanCD);

        for (Media media : mediaList) {
            System.out.println(media.toString());
        }
    }
}
