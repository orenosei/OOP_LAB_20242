package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MediaStore extends JPanel {
    private Media media;

public MediaStore(Media media) {
    this.media = media;
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    String mediaType = "";
    if (media instanceof Book) {
        mediaType = "Book: ";
    } else if (media instanceof CompactDisc) {
        mediaType = "CD: ";
    } else if (media instanceof DigitalVideoDisc) {
        mediaType = "DVD: ";
    } else {
        mediaType = "Media: ";
    }

    JLabel title = new JLabel(mediaType + media.getTitle());
    title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
    title.setAlignmentX(CENTER_ALIGNMENT);

    JLabel category = new JLabel("Category: " + media.getCategory());
    category.setFont(new Font(category.getFont().getName(), Font.PLAIN, 15));
    category.setAlignmentX(CENTER_ALIGNMENT);

    JLabel cost = new JLabel("Cost: " + media.getCost() + " $");
    cost.setFont(new Font(cost.getFont().getName(), Font.PLAIN, 15));
    cost.setAlignmentX(CENTER_ALIGNMENT);

    JPanel additionalInfo = new JPanel();
    additionalInfo.setLayout(new BoxLayout(additionalInfo, BoxLayout.Y_AXIS));
    additionalInfo.setAlignmentX(CENTER_ALIGNMENT);
    additionalInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));


    if (media instanceof DigitalVideoDisc) {
        DigitalVideoDisc dvd = (DigitalVideoDisc) media;
        additionalInfo.add(new JLabel("Director: " + dvd.getDirector()));
        additionalInfo.add(new JLabel("Length: " + dvd.getLength() + " minutes"));
    } else if (media instanceof CompactDisc) {
        CompactDisc cd = (CompactDisc) media;
        additionalInfo.add(new JLabel("Artist: " + cd.getArtist()));
        additionalInfo.add(new JLabel("Length: " + cd.getLength() + " minutes"));

        additionalInfo.add(new JLabel("Tracks:"));
        for (Track track : cd.getTracks()) {
            additionalInfo.add(new JLabel("  - " + track.getTitle() + " (" + track.getLength() + " minutes)"));
        }
    } else if (media instanceof Book) {
        Book book = (Book) media;
        additionalInfo.add(new JLabel("Authors: " + String.join(", ", book.getAuthors())));
    }

    JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    if (media instanceof Playable) {
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> showPlayDialog((Playable) media));
        container.add(playButton);
    }


    this.add(title);
    this.add(Box.createRigidArea(new Dimension(0, 10)));
    this.add(category);
    this.add(cost);
    this.add(Box.createRigidArea(new Dimension(0, 10)));
    this.add(additionalInfo);
    this.add(Box.createRigidArea(new Dimension(0, 10)));
    this.add(container);

    this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1   ));
}


    private void showPlayDialog(Playable playable) {
        StringBuilder content = new StringBuilder();
        if (playable instanceof DigitalVideoDisc) {
            DigitalVideoDisc dvd = (DigitalVideoDisc) playable;
            content.append("Title: ").append(dvd.getTitle()).append("\n");
            content.append("Length: ").append(dvd.getLength()).append(" minutes\n");
            content.append("Director: ").append(dvd.getDirector()).append("\n");
        } else if (playable instanceof CompactDisc) {
            CompactDisc cd = (CompactDisc) playable;
            content.append("Title: ").append(cd.getTitle()).append("\n");
            content.append("Artist: ").append(cd.getArtist()).append("\n");
            content.append("Tracks:\n");
            List<Track> tracks = cd.getTracks();
            for (int i = 0; i < tracks.size(); i++) {
                Track track = tracks.get(i);
                content.append("  ").append(i + 1).append(". ")
                        .append(track.getTitle()).append(" - ")
                        .append(track.getLength()).append(" minutes\n");
            }
        } else {
            content.append("Cannot play this media type.");
        }

        JOptionPane.showMessageDialog(this,
                content.toString(),
                "Play Media",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
