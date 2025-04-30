package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable{

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost, length, director);
    }

    public DigitalVideoDisc(String title) {
        this(title, "Unknown", "Unknown", 0, 0.0f);
    }

    @Override
    public String toString() {
        return "DVD - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getDirector() + " - " + this.getLength() + " minutes - " + this.getCost() + " $";
    }

    @Override
    public void play(){
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength() + " minutes");
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
