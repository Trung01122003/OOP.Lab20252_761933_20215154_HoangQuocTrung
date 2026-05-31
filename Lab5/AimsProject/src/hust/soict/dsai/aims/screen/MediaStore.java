package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.exception.PlayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(String.format("%.2f", media.getCost()) + " $");
        cost.setFont(new Font("Arial", Font.PLAIN, 14));
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnAddToCart = new JButton("Add to cart");
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.addMedia(MediaStore.this.media);
                JOptionPane.showMessageDialog(null, 
                    "The media \"" + MediaStore.this.media.getTitle() + "\" has been added to the cart.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        container.add(btnAddToCart);

        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");
            btnPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Play the media (which might throw PlayerException)
                        ((Playable) MediaStore.this.media).play();
                        
                        // Show popup containing play details
                        String playInfo = "Playing Media: " + MediaStore.this.media.getTitle();
                        if (MediaStore.this.media instanceof hust.soict.dsai.aims.media.DigitalVideoDisc) {
                            playInfo += "\nLength: " + ((hust.soict.dsai.aims.media.DigitalVideoDisc) MediaStore.this.media).getLength();
                        } else if (MediaStore.this.media instanceof hust.soict.dsai.aims.media.CompactDisc) {
                            playInfo += "\nArtist: " + ((hust.soict.dsai.aims.media.CompactDisc) MediaStore.this.media).getArtist();
                            playInfo += "\nLength: " + ((hust.soict.dsai.aims.media.CompactDisc) MediaStore.this.media).getLength();
                        }
                        
                        JOptionPane.showMessageDialog(null, playInfo, "Playing", JOptionPane.INFORMATION_MESSAGE);
                    } catch (PlayerException ex) {
                        JOptionPane.showMessageDialog(null, 
                            ex.getMessage(), 
                            "Illegal Media Length", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            container.add(btnPlay);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
