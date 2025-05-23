package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.IllegalItemException;

import java.util.List;
import java.util.NoSuchElementException;

public class Book extends Media{
    private List<String> authors;

    public Book(String title, String category, float cost, List<String> authors) throws IllegalItemException {
        super(title, category, cost);
        this.authors = authors;
    }

    public String getAuthors() {
        String authorsString = "";
        for (String author : authors) {
            authorsString += author + ",";
        }
        return authorsString.substring(0, authorsString.length() - 1);
    }



    public void addAuthor(String author) {
        if (authors.contains(author)) {
            throw new IllegalArgumentException("Author '" + author + "' already exists");
        }
        authors.add(author);
    }

    public void removeAuthor(String author) {
        if (!authors.contains(author)) {
            throw new NoSuchElementException("Author '" + author + "' not found");
        }
        authors.remove(author);
    }


    @Override
    public String toString() {
        return "Book - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getCost() + " $" + this.getAuthors();
    }

    @Override
    public String printData(){
        String data = "Book detail:\n";
        data += "Title: " + this.getTitle() + "\n";
        data += "Category: " + this.getCategory() + "\n";
        data += "Cost: " + this.getCost() + "\n";
        data += "Authors: " + this.getAuthors() + "\n";
        return data;
    }
}
