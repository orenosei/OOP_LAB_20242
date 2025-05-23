package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.IllegalItemException;

public class Disc extends Media{
    private int length;
    private String director;


    public Disc(String title, String category, float cost, int length, String director) throws IllegalItemException {
        super(title, category, cost);
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative: " + length);
        }
        if (director == null || director.isBlank()) {
            throw new IllegalArgumentException("Director cannot be null/empty");
        }
        this.length = length;
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }


}
