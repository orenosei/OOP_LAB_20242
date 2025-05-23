package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class StoreManagerScreen extends JFrame {
    private static Store store;

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            dispose();
            new StoreManagerScreen(store);
        });
        menu.add(viewStore);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem tfAddBook = new JMenuItem("Add Book");
        tfAddBook.addActionListener(handleAddBookBtn());
        smUpdateStore.add(tfAddBook);

        JMenuItem tfAddCD = new JMenuItem("Add CD");
        tfAddCD.addActionListener(handleAddCDBtn());
        smUpdateStore.add(tfAddCD);

        JMenuItem tfAddDVD = new JMenuItem("Add DVD");
        tfAddDVD.addActionListener(handleAddDVDBtn());
        smUpdateStore.add(tfAddDVD);

        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    private ActionListener handleAddBookBtn() {
        return e -> {
            dispose();
            new AddBookToStoreScreen(store);
        };
    }

    private ActionListener handleAddCDBtn() {
        return e -> {
            dispose();
            new AddCompactDiscToStoreScreen(store);
        };
    }

    private ActionListener handleAddDVDBtn() {
        return e -> {
            dispose();
            new AddDigitalVideoDiscToStoreScreen(store);
        };
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JScrollPane createCenter() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 3, 2, 2)); // Số hàng linh hoạt, 3 cột cố định

        Stack<Media> mediaInStore = store.getItemsInStore();
        for (Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media);
            centerPanel.add(cell);
        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }


    public StoreManagerScreen(Store store) {
        StoreManagerScreen.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void initializeStore() throws IllegalItemException {
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
    }

    public static void main(String[] args) throws IllegalItemException {
        store = new Store();
        initializeStore();
        new StoreManagerScreen(store);

    }

}
