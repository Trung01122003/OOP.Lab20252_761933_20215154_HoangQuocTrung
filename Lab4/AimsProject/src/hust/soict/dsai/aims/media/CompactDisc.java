package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc() {
        super();
    }

    public CompactDisc(String title) {
        super(title);
    }

    public CompactDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public CompactDisc(String title, String category, String director, float cost, String artist) {
        super(title, category, director, 0, cost); // length will be calculated
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track added: " + track.getTitle());
        } else {
            System.out.println("Track already exists in CD.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track removed: " + track.getTitle());
        } else {
            System.out.println("Track not found in CD.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() {
        if (this.getLength() <= 0) {
            System.out.println("The CD " + this.getTitle() + " cannot be played because its length is 0 or less.");
        } else {
            System.out.println("Playing CD: " + this.getTitle() + " by " + this.artist);
            System.out.println("CD length: " + this.getLength());
            for (Track track : tracks) {
                track.play();
            }
        }
    }

    @Override
    public String toString() {
        return "CD - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getDirector() + " - " + this.artist + " - " + this.getLength() + ": " + this.getCost() + " $";
    }
}
