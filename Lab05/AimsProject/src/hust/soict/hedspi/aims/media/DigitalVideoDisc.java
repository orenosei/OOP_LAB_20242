package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.IllegalItemException;
import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable{

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) throws IllegalItemException {
        super(title, category, cost, length, director);
        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Director cannot be null/empty");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative: " + length);
        }
    }

    public DigitalVideoDisc(String title) throws IllegalItemException {
        this(title, "Unknown", "Unknown", 0, 0.0f);
    }

    @Override
    public String toString() {
        return "DVD - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getDirector() + " - " + this.getLength() + " minutes - " + this.getCost() + " $";
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength() + " minutes");
    }

    @Override
    public String play(boolean returnDetails) throws PlayerException {
        play();
        return String.format(
                "Playing DVD: %s\nDirector: %s\nLength: %d minutes",
                getTitle(),
                getDirector(),
                getLength()
        );
    }

    @Override
    public String printData(){
        String data = "DVD detail:\n";
        data += "Title: " + this.getTitle() + "\n";
        data += "Category: " + this.getCategory() + "\n";
        data += "Director: " + this.getDirector() + "\n";
        data += "Length: " + this.getLength() + "\n";
        data += "Cost: " + this.getCost() + "\n";
        return data;
    }
}
