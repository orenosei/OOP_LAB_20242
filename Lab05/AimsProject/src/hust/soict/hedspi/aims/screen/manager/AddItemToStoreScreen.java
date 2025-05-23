package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;
    protected Store store;

    public AddItemToStoreScreen(Store store) {
        this.store = store;
        setTitle("Add Item to Store");
        setSize(600, 800);
        setLocationRelativeTo(null);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createMainPanel(), BorderLayout.CENTER);
        cp.add(createFooter(), BorderLayout.SOUTH);

        setVisible(true);
    }

    protected abstract void handleSubmit();


    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        return north;
    }

    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStore = new JMenuItem("Return to Store");
        viewStore.addActionListener(e -> {
            dispose();
            new StoreManagerScreen(store);
        });
        menu.add(viewStore);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem tfAddBook = new JMenuItem("Add Book");
        tfAddBook.addActionListener(e -> {
            dispose();
            new AddBookToStoreScreen(store);
        });
        smUpdateStore.add(tfAddBook);

        JMenuItem tfAddCD = new JMenuItem("Add CD");
        tfAddCD.addActionListener(e -> {
            dispose();
            new AddCompactDiscToStoreScreen(store);
        });
        smUpdateStore.add(tfAddCD);

        JMenuItem tfAddDVD = new JMenuItem("Add DVD");
        tfAddDVD.addActionListener(e -> {
            dispose();
            new AddDigitalVideoDiscToStoreScreen(store);
        });
        smUpdateStore.add(tfAddDVD);

        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }


    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));

        JButton submitButton = new JButton("Add to Store");
        submitButton.addActionListener(e -> handleSubmit());
        footer.add(new JLabel(""));
        footer.add(submitButton);

        return footer;
    }

    JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));

        mainPanel.add(new JLabel("Title: "));
        tfTitle = new JTextField(10);
        mainPanel.add(tfTitle);

        mainPanel.add(new JLabel("Category: "));
        tfCategory = new JTextField(10);
        mainPanel.add(tfCategory);

        mainPanel.add(new JLabel("Cost: "));
        tfCost = new JTextField(10);
        mainPanel.add(tfCost);

        return mainPanel;
    }
}
