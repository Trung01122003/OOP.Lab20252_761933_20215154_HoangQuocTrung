package hust.soict.dsai.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() {
        if (this.length <= 0) {
            System.out.println("The track " + this.title + " cannot be played because its length is 0 or less.");
        } else {
            System.out.println("Playing track: " + this.title);
            System.out.println("Track length: " + this.length);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Track)) {
            return false;
        }
        Track track = (Track) o;
        return this.title != null && this.title.equals(track.title) && this.length == track.length;
    }
}
