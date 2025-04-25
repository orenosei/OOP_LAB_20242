package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
    private List<String> authors;

    public Book(String title, String category, float cost, List<String> authors) {
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
