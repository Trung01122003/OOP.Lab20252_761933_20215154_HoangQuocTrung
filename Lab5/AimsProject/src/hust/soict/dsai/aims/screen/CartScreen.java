package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CartScreen extends JFrame {
    private Cart cart;
    private Store store;

    public CartScreen(Cart cart) {
        this(cart, null);
    }

    public CartScreen(Cart cart, Store store) {
        this.cart  = cart;
        this.store = store;

        setTitle("AIMS - Cart");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFXPanel fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.CENTER);

        // Initialize JavaFX content on the JavaFX thread
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                Scene scene = new Scene(loader.load());
                fxPanel.setScene(scene);

                CartScreenController controller = loader.getController();
                controller.setCart(cart);
                controller.setStore(store);
                controller.loadData();
            } catch (IOException e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(CartScreen.this,
                        "Failed to load cart.fxml: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE)
                );
            }
        });

        setVisible(true);
    }
}
