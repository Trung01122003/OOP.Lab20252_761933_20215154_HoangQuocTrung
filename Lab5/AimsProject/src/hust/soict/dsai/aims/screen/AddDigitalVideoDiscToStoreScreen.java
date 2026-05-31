package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "AIMS - Add DVD to Store");
    }

    @Override
    protected void addSpecificFields(JPanel panel) {
        tfDirector = addField("Director:", panel);
        tfLength = addField("Length (min):", panel);
    }

    @Override
    protected Media createMedia(String title, String category, float cost) throws Exception {
        String director = tfDirector.getText().trim();
        String lengthStr = tfLength.getText().trim();
        int length = 0;
        if (!lengthStr.isEmpty()) {
            length = Integer.parseInt(lengthStr);
            if (length < 0) {
                throw new Exception("Length cannot be negative!");
            }
        }
        return new DigitalVideoDisc(title, category, director, length, cost);
    }
}
