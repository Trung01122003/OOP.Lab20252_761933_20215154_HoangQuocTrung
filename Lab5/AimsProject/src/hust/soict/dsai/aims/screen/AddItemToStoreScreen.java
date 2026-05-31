package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;

    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;

    public AddItemToStoreScreen(Store store, Cart cart, String titleName) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(titleName);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            new StoreScreen(store, cart);
            dispose();
        });
        menu.add(viewStoreItem);

        JMenuItem viewCartItem = new JMenuItem("View cart");
        viewCartItem.addActionListener(e -> {
            new CartScreen(cart);
            dispose();
        });
        menu.add(viewCartItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel title = new JLabel("AIMS - Add Item to Store");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.BLUE);
        header.add(title);
        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        tfTitle = addField("Title:", center);
        tfCategory = addField("Category:", center);
        tfCost = addField("Cost:", center);

        addSpecificFields(center);

        JButton btnAdd = new JButton("Add Item");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdd.addActionListener(e -> {
            try {
                String title = tfTitle.getText().trim();
                String category = tfCategory.getText().trim();
                String costStr = tfCost.getText().trim();

                if (title.isEmpty() || category.isEmpty() || costStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all common fields (Title, Category, Cost)!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                float cost = Float.parseFloat(costStr);
                if (cost < 0) {
                    JOptionPane.showMessageDialog(this, "Cost cannot be negative!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Media media = createMedia(title, category, cost);
                if (media != null) {
                    store.addMedia(media);
                    JOptionPane.showMessageDialog(this, "Item successfully added to store!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new StoreScreen(store, cart);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cost must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(btnAdd);
        center.add(Box.createVerticalGlue());
        center.add(btnPanel);

        return center;
    }

    protected JTextField addField(String labelText, JPanel panel) {
        JPanel row = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setPreferredSize(new Dimension(100, 25));
        JTextField tf = new JTextField(20);
        tf.setFont(new Font("Arial", Font.PLAIN, 14));
        row.add(label, BorderLayout.WEST);
        row.add(tf, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        return tf;
    }

    protected abstract void addSpecificFields(JPanel panel);
    protected abstract Media createMedia(String title, String category, float cost) throws Exception;
}
