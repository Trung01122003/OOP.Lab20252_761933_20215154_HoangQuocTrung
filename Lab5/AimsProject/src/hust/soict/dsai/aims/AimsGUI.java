package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AimsGUI extends JFrame {
    private Store store;
    private Cart cart;

    private DefaultListModel<Media> listModel;
    private JList<Media> mediaJList;
    private JTextArea detailsArea;

    public AimsGUI() {
        this.store = new Store();
        this.cart = new Cart();
        prepopulate();
        initUI();
    }

    private void prepopulate() {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        Book book1 = new Book("Harry Potter", "Fantasy", 20.00f);
        book1.addAuthor("J.K. Rowling");
        store.addMedia(book1);

        CompactDisc cd1 = new CompactDisc("Greatest Hits", "Music", "Various", 15.00f, "Queen");
        Track track1 = new Track("Bohemian Rhapsody", 6);
        Track track2 = new Track("We Will Rock You", 3);
        cd1.addTrack(track1);
        cd1.addTrack(track2);
        store.addMedia(cd1);
    }

    private void initUI() {
        setTitle("AIMS - GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel main = new JPanel(new BorderLayout(10,10));
        main.setBorder(new EmptyBorder(10,10,10,10));

        listModel = new DefaultListModel<>();
        for (Media m : store.getItemsInStore()) {
            listModel.addElement(m);
        }

        mediaJList = new JList<>(listModel);
        mediaJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mediaJList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getTitle() + "  -  $" + value.getCost());
            if (isSelected) label.setBackground(list.getSelectionBackground());
            label.setOpaque(true);
            return label;
        });

        JScrollPane listScroll = new JScrollPane(mediaJList);
        listScroll.setPreferredSize(new Dimension(350, 400));

        JPanel right = new JPanel(new BorderLayout(8,8));
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        right.add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAddToCart = new JButton("Add to Cart");
        JButton btnPlay = new JButton("Play");
        JButton btnViewCart = new JButton("View Cart");
        buttons.add(btnAddToCart);
        buttons.add(btnPlay);
        buttons.add(btnViewCart);
        right.add(buttons, BorderLayout.SOUTH);

        main.add(listScroll, BorderLayout.WEST);
        main.add(right, BorderLayout.CENTER);

        add(main);

        mediaJList.addListSelectionListener(e -> {
            Media m = mediaJList.getSelectedValue();
            if (m != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Title: ").append(m.getTitle()).append("\n");
                sb.append("Category: ").append(m.getCategory()).append("\n");
                sb.append("Cost: $").append(m.getCost()).append("\n");
                if (m instanceof Book) {
                    Book b = (Book) m;
                    sb.append("Authors: ").append(String.join(", ", b.getAuthors())).append("\n");
                } else if (m instanceof CompactDisc) {
                    CompactDisc cd = (CompactDisc) m;
                    sb.append("Artist: ").append(cd.getArtist()).append("\n");
                    sb.append("Length: ").append(cd.getLength()).append("\n");
                } else if (m instanceof DigitalVideoDisc) {
                    DigitalVideoDisc d = (DigitalVideoDisc) m;
                    sb.append("Director: ").append(d.getDirector()).append("\n");
                    sb.append("Length: ").append(d.getLength()).append("\n");
                }
                detailsArea.setText(sb.toString());
            } else {
                detailsArea.setText("");
            }
        });

        btnAddToCart.addActionListener(e -> {
            Media m = mediaJList.getSelectedValue();
            if (m != null) {
                cart.addMedia(m);
                JOptionPane.showMessageDialog(this, "Added to cart: " + m.getTitle());
            }
        });

        btnPlay.addActionListener(e -> {
            Media m = mediaJList.getSelectedValue();
            if (m != null && m instanceof Playable) {
                try {
                    ((Playable) m).play();
                    JOptionPane.showMessageDialog(this, "Playing: " + m.getTitle(), "Now Playing", JOptionPane.INFORMATION_MESSAGE);
                } catch (hust.soict.dsai.aims.exception.PlayerException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Illegal Media Length", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "This media cannot be played.");
            }
        });


        btnViewCart.addActionListener(e -> showCartDialog());
    }

    private void showCartDialog() {
        JDialog dialog = new JDialog(this, "Cart", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);

        DefaultListModel<Media> cartModel = new DefaultListModel<>();
        for (Media m : cart.getItemsOrdered()) cartModel.addElement(m);
        JList<Media> cartList = new JList<>(cartModel);
        cartList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getTitle() + "  -  $" + value.getCost());
            label.setOpaque(true);
            return label;
        });

        JPanel p = new JPanel(new BorderLayout(8,8));
        p.add(new JScrollPane(cartList), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRemove = new JButton("Remove");
        JButton btnPlace = new JButton("Place Order");
        bottom.add(btnRemove);
        bottom.add(btnPlace);
        p.add(bottom, BorderLayout.SOUTH);

        btnRemove.addActionListener(e -> {
            Media sel = cartList.getSelectedValue();
            if (sel != null) {
                cart.removeMedia(sel);
                cartModel.removeElement(sel);
            }
        });

        btnPlace.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Order placed. Total: $" + cart.totalCost());
            cart.emptyCart();
            dialog.dispose();
        });

        dialog.add(p);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AimsGUI gui = new AimsGUI();
            gui.setVisible(true);
        });
    }
}
