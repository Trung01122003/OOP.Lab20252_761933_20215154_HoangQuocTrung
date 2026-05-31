package hust.soict.dsai.aims.exception;

public class PlayerException extends Exception {
    public PlayerException() {
        super("ERROR: Illegal media length!");
    }

    public PlayerException(String message) {
        super(message);
    }
}
