package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.IllegalItemException;

import java.util.Comparator;

public class Media implements Comparable<Media>{
    private int id;
    private String title;
    private String category;
    private float cost;
    private static int nbMedia = 0;


    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();


    public Media(String title, String category, float cost) throws IllegalItemException {
        if (title == null || title.isBlank()) {
            throw new IllegalItemException("Title is null");
        }
        if (cost < 0) {
            throw new IllegalItemException("Cost is illegal: " + cost);
        }
        this.title = title;
        this.category = category;
        this.cost = cost;
        nbMedia++;
        this.id = nbMedia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Media other)) return false;
        return this.title.equals(other.title)
                && Float.compare(this.cost, other.cost) == 0;
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null Media");
        }

        int titleCompare = this.title.compareTo(other.title);
        if (titleCompare != 0) {
            return titleCompare;
        }

        return Float.compare(this.cost, other.cost);
    }

    public String printData(){return "";}
}
