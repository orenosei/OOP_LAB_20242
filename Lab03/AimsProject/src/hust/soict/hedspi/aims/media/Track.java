package hust.soict.hedspi.aims.media;

public class Track implements Playable{
    private String title;
    private int length;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Track(){
        this.title = "Unknown";
        this.length = 0;
    }
    public Track(String title, int length){
        this.title = title;
        this.length = length;
    }

    @Override
    public void play(){
        System.out.println("Playing track " + this.getTitle());
        System.out.println("Track length: " + this.getLength() + " seconds");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        return this.title.equals(track.title) && this.length == track.length;
    }
}
