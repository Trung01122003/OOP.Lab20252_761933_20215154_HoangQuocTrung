package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    public DigitalVideoDisc() {
        super();
    }

    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super(title, category, director, 0, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    @Override
    public void play() {
        if (this.getLength() <= 0) {
            System.out.println("The DVD " + this.getTitle() + " cannot be played because its length is 0 or less.");
        } else {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        }
    }

    @Override
    public String toString() {
        return "DVD - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getDirector() + " - " + this.getLength() + ": " + this.getCost() + " $";
    }

    public boolean isMatch(String title) {
        return this.getTitle() != null && this.getTitle().toLowerCase().contains(title.toLowerCase());
    }
}
