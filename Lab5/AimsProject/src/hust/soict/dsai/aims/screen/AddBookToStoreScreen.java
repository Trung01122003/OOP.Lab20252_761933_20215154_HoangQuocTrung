package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart, "AIMS - Add Book to Store");
    }

    @Override
    protected void addSpecificFields(JPanel panel) {
        tfAuthors = addField("Authors:", panel);
        JLabel hint = new JLabel("(Separate multiple authors with comma)");
        hint.setFont(new Font("Arial", Font.ITALIC, 11));
        hint.setForeground(Color.GRAY);
        JPanel hintPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hintPanel.add(hint);
        panel.add(hintPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    @Override
    protected Media createMedia(String title, String category, float cost) throws Exception {
        Book book = new Book(title, category, cost);
        String authorsStr = tfAuthors.getText().trim();
        if (!authorsStr.isEmpty()) {
            String[] authors = authorsStr.split(",");
            for (String author : authors) {
                String trimmed = author.trim();
                if (!trimmed.isEmpty()) {
                    book.addAuthor(trimmed);
                }
            }
        }
        return book;
    }
}
