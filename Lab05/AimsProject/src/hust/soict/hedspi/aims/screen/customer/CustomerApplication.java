package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class CustomerApplication extends Application {

    public static Store store = new Store();
    public static Cart cart = new Cart();

    @Override
    public void start(Stage stage) throws IOException {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
//        final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
//        CartController cartController = new CartController(cart);
//        fxmlLoader.setController(cartController);

        Parent root = fxmlLoader.load();
        stage.setTitle("Hello!");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void main(String[] args) throws IllegalItemException {
        store = new Store();
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

        mediaList.add(new Book("Death Note", "Manga", 10.99f, Collections.singletonList("Tsugumi Ohba")));
        mediaList.add(new Book("Attack on Titan", "Manga", 11.99f, Collections.singletonList("Hajime Isayama")));
        mediaList.add(new Book("Naruto", "Manga", 9.99f, Collections.singletonList("Masashi Kishimoto")));
        mediaList.add(new Book("One Piece", "Manga", 12.99f, Collections.singletonList("Eiichiro Oda")));
        mediaList.add(new Book("Bleach", "Manga", 13.99f, Collections.singletonList("Tite Kubo")));

        mediaList.add(new DigitalVideoDisc("Demon Slayer: Mugen Train", "Action", "Ufotable", 117, 21.99f));
        mediaList.add(new DigitalVideoDisc("Howl's Moving Castle", "Fantasy", "Studio Ghibli", 119, 18.99f));
        mediaList.add(new DigitalVideoDisc("A Silent Voice", "Drama", "Kyoto Animation", 130, 22.99f));
        mediaList.add(new DigitalVideoDisc("Weathering With You", "Romance", "Makoto Shinkai", 113, 24.99f));
        mediaList.add(new DigitalVideoDisc("My Neighbor Totoro", "Fantasy", "Studio Ghibli", 86, 16.99f));

        CompactDisc onePieceCD = new CompactDisc("One Piece OST", "Music", "Kohei Tanaka", 0, 13.99f, "Kohei Tanaka");
        onePieceCD.addTrack(new Track("We Are!", 3));
        onePieceCD.addTrack(new Track("Overtaken", 4));
        mediaList.add(onePieceCD);

        CompactDisc narutoCD = new CompactDisc("Naruto OST", "Music", "Toshio Masuda", 0, 12.99f, "Toshio Masuda");
        narutoCD.addTrack(new Track("Sadness and Sorrow", 5));
        narutoCD.addTrack(new Track("Naruto's Theme", 4));
        mediaList.add(narutoCD);

        CompactDisc ghibliCD = new CompactDisc("Studio Ghibli Classics", "Music", "Joe Hisaishi", 0, 15.99f, "Joe Hisaishi");
        ghibliCD.addTrack(new Track("One Summer's Day", 6));
        ghibliCD.addTrack(new Track("Path of the Wind", 4));
        mediaList.add(ghibliCD);

        CompactDisc evangelionCD = new CompactDisc("Evangelion OST", "Music", "Shiro Sagisu", 0, 14.99f, "Shiro Sagisu");
        evangelionCD.addTrack(new Track("A Cruel Angel's Thesis", 5));
        evangelionCD.addTrack(new Track("Thanatos", 6));
        mediaList.add(evangelionCD);


        for (Media media : mediaList) {
            store.addMedia(media);
        }

//        cart = new Cart();
//        cart.addMedia(new Media("No Game No Life", "Light Novel", 15.99f));
//        cart.addMedia(new Media( "Sword Art Online", "Light Novel", 18.99f));
//        cart.addMedia(new Media( "Re:Zero", "Light Novel", 20.99f));

        launch();
    }
}