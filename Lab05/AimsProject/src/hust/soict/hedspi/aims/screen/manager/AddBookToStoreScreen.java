package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book to Store");
    }


    @Override
    protected void handleSubmit() {
        try {
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            float cost = Float.parseFloat(tfCost.getText().trim());
            String authorsText = tfAuthors.getText().trim();

            if (title.isEmpty() || category.isEmpty() || authorsText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<String> authors = new ArrayList<>();
            for (String author : authorsText.split(",")) {
                authors.add(author.trim());
            }

            Book newBook = new Book(title, category, cost, authors);
            store.addMedia(newBook);
            JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);


            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
            tfAuthors.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cost must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalItemException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = super.createMainPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 5, 5));

        mainPanel.add(new JLabel("Authors (separated by comma): "));
        tfAuthors = new JTextField(10);
        mainPanel.add(tfAuthors);

        return mainPanel;
    }
}
