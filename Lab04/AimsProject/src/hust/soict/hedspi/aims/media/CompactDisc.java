package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private List<Track> tracks;

    public String getArtist() {
        return artist;
    }


    public void addTrack(Track track){
        if(!tracks.contains(track)) tracks.add(track);
        else System.out.println("Track " + track.getTitle() + " is already in the list");
    }

    public void removeTrack(Track track){
        if(tracks.contains(track)) tracks.remove(track);
        else System.out.println("Track " + track.getTitle() + " is not in the list");
    }

    public List<Track> getTracks(){
        return tracks;
    }
    public void setTracks(List<Track> tracks){
        this.tracks = tracks;
    }
    public void setTrack(int index, Track track){
        if(index < tracks.size() && index >= 0){}
    }

    public int getLength(){
        int length = 0;
        for(Track track : tracks){
            length += track.getLength();
        }
        return length;
    }


    public CompactDisc(String title, String category, String director, int length, float cost, String artist) {
        super(title, category, cost, length, director);
        this.artist = artist;
        this.tracks = new ArrayList<>();
    }

    public CompactDisc(String title, String category, String director, int length, float cost, String artist, List<Track> tracks) {
        super(title, category, cost, length, director);
        this.artist = artist;
        this.tracks = tracks;
    }


    @Override
    public void play() {
        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("CD length: " + this.getLength());
        for (Track track : tracks) {
            track.play();
            System.out.println("--------------------------------------");
        }
    }

    @Override
    public String toString() {
        return "CD - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getCost() + " $, Artist: " + this.getArtist() + ", Total Tracks: " + this.tracks.size() + ", Total Length: " + this.getLength() + " minutes";
    }

    @Override
    public String printData(){
        String data = "CD detail:\n";
        data += "Title: " + this.getTitle() + "\n";
        data += "Category: " + this.getCategory() + "\n";
        data += "Artist: " + this.getArtist() + "\n";
        data += "Total Tracks: " + this.tracks.size() + "\n";
        data += "Length: " + this.getLength() + "\n";
        data += "Cost: " + this.getCost() + "\n";
        return data;
    }

}
