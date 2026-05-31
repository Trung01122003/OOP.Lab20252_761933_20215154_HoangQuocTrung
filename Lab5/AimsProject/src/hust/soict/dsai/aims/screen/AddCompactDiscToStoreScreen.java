package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfArtist;

    public AddCompactDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "AIMS - Add CD to Store");
    }

    @Override
    protected void addSpecificFields(JPanel panel) {
        tfDirector = addField("Director:", panel);
        tfArtist   = addField("Artist:", panel);
    }

    @Override
    protected Media createMedia(String title, String category, float cost) throws Exception {
        String director = tfDirector.getText().trim();
        String artist   = tfArtist.getText().trim();
        if (artist.isEmpty()) {
            throw new Exception("Artist field cannot be empty!");
        }
        return new CompactDisc(title, category, director, cost, artist);
    }
}
