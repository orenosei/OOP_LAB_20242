package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfLength;
    private JTextField tfArtist;
    private List<Track> tracks;
    private JTextArea taTracksPreview;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        tracks = new ArrayList<>();
        setTitle("Add Compact Disc to Store");
    }

    @Override
    protected void handleSubmit() {
        try {
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            float cost = Float.parseFloat(tfCost.getText().trim());
            String director = tfDirector.getText().trim();
            int length = Integer.parseInt(tfLength.getText().trim());
            String artist = tfArtist.getText().trim();

            if (title.isEmpty() || category.isEmpty() || director.isEmpty() || artist.isEmpty() || tracks.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty and at least one track must be added!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CompactDisc newCd = new CompactDisc(title, category, director, length, cost, artist, tracks);
            store.addMedia(newCd);
            JOptionPane.showMessageDialog(this, "Compact Disc added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Reset fields
            tfTitle.setText("");
            tfCategory.setText("");
            tfCost.setText("");
            tfDirector.setText("");
            tfLength.setText("");
            tfArtist.setText("");
            tracks.clear();
            updateTrackPreview();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cost, Length must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalItemException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel mainPanel = super.createMainPanel();
        mainPanel.setLayout(new GridLayout(8, 2, 5, 5));

        mainPanel.add(new JLabel("Director: "));
        tfDirector = new JTextField(20);
        mainPanel.add(tfDirector);

        mainPanel.add(new JLabel("Length: "));
        tfLength = new JTextField(20);
        mainPanel.add(tfLength);

        mainPanel.add(new JLabel("Artist: "));
        tfArtist = new JTextField(20);
        mainPanel.add(tfArtist);

        mainPanel.add(new JLabel("Tracks preview: "));
        taTracksPreview = new JTextArea(5, 20);
        taTracksPreview.setEditable(false);
        mainPanel.add(new JScrollPane(taTracksPreview));

        JButton btnAddTracks = new JButton("Add Tracks");
        btnAddTracks.addActionListener(e -> openTrackDialog());
        mainPanel.add(new JLabel(""));
        mainPanel.add(btnAddTracks);

        return mainPanel;
    }

    private void openTrackDialog() {
        JDialog trackDialog = new JDialog(this, "Manage Tracks", true);
        trackDialog.setSize(400, 300);
        trackDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField tfTrackTitle = new JTextField();
        JTextField tfTrackLength = new JTextField();
        inputPanel.add(new JLabel("Track Title: "));
        inputPanel.add(tfTrackTitle);
        inputPanel.add(new JLabel("Track Length: "));
        inputPanel.add(tfTrackLength);

        JButton btnAdd = new JButton("Add Track");

        inputPanel.add(btnAdd);


        panel.add(inputPanel, BorderLayout.NORTH);

        // Add track
        btnAdd.addActionListener(e -> {
            try {
                String trackTitle = tfTrackTitle.getText().trim();
                int trackLength = Integer.parseInt(tfTrackLength.getText().trim());
                if (trackTitle.isEmpty()) {
                    JOptionPane.showMessageDialog(trackDialog, "Track title cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Track newTrack = new Track(trackTitle, trackLength);
                tracks.add(newTrack);
                updateTrackPreview();
                tfTrackTitle.setText("");
                tfTrackLength.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(trackDialog, "Track length must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        trackDialog.add(panel);
        trackDialog.setVisible(true);
    }

    private void updateTrackPreview() {
        StringBuilder preview = new StringBuilder();
        for (Track track : tracks) {
            preview.append(track.getTitle()).append(" - ").append(track.getLength()).append("s\n");
        }
        taTracksPreview.setText(preview.toString());
    }
}
