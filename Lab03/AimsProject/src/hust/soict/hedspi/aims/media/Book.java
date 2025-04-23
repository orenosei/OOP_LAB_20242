package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
    private List<String> authors = new ArrayList<String>();

    public Book(int id, String title, String category, float cost, List<String> authors) {
        super(id, title, category, cost);
        this.authors = authors;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String author){
        if(!authors.contains(author)) authors.add(author);
        else System.out.println("Author " + author + " is already in the list");
    }
    public void removeAuthor(String author){
        if(authors.contains(author)) authors.remove(author);
        else System.out.println("Author " + author + " is not in the list");
    }

    @Override
    public String toString() {
        return "Book - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getCost() + " $";
    }
}
