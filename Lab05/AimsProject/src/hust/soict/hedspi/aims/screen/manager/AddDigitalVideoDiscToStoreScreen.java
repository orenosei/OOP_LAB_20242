package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add Digital Video Disc to Store");
    }

    @Override
    protected void handleSubmit() {
        try {
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            float cost = Float.parseFloat(tfCost.getText().trim());
            String director = tfDirector.getText().trim();
            int length = Integer.parseInt(tfLength.getText().trim());

            if (title.isEmpty() || category.isEmpty() || director.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DigitalVideoDisc newDvd = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(newDvd);
            JOptionPane.showMessageDialog(this, "Digital Video Disc added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);


            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
            tfDirector.setText("");
            tfLength.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cost and Length must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalItemException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = super.createMainPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 5, 5));

        mainPanel.add(new JLabel("Director: "));
        tfDirector = new JTextField(10);
        mainPanel.add(tfDirector);

        mainPanel.add(new JLabel("Length: "));
        tfLength = new JTextField(10);
        mainPanel.add(tfLength);

        return mainPanel;
    }
}
