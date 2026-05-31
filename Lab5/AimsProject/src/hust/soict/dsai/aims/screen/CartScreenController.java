package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CartScreenController implements Initializable {

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, Integer> colMediaId;
    @FXML private TableColumn<Media, String>  colMediaTitle;
    @FXML private TableColumn<Media, String>  colMediaCategory;
    @FXML private TableColumn<Media, Float>   colMediaCost;

    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Label  lblTotalCost;

    @FXML private RadioButton radioBtnFilterById;
    @FXML private RadioButton radioBtnFilterByName;
    @FXML private TextField   tfFilter;

    private Cart cart;
    private Store store;

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind table columns to Media properties
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // Listen to row selection changes
        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> obs, Media oldSel, Media newSel) {
                if (newSel != null) {
                    btnRemove.setVisible(true);
                    btnPlay.setVisible(newSel instanceof Playable);
                } else {
                    btnPlay.setVisible(false);
                    btnRemove.setVisible(false);
                }
            }
        });

        // Filter listener
        tfFilter.textProperty().addListener((obs, oldVal, newVal) -> filterMedias(null));

        // Mutual exclusion for filter radio buttons
        ToggleGroup group = new ToggleGroup();
        radioBtnFilterById.setToggleGroup(group);
        radioBtnFilterByName.setToggleGroup(group);
        radioBtnFilterByName.setSelected(true);
    }

    public void loadData() {
        if (cart != null) {
            tblMedia.setItems(cart.getItemsOrdered());
            updateTotalCost();
        }
    }

    void updateTotalCost() {
        if (cart != null) {
            lblTotalCost.setText(String.format("Total: %.2f $", cart.totalCost()));
        }
    }

    @FXML
    void filterMedias(ActionEvent event) {
        if (cart == null) return;
        String filterText = tfFilter.getText().trim().toLowerCase();
        if (filterText.isEmpty()) {
            tblMedia.setItems(cart.getItemsOrdered());
            return;
        }

        ObservableList<Media> filtered = FXCollections.observableArrayList();
        if (radioBtnFilterById.isSelected()) {
            try {
                int id = Integer.parseInt(filterText);
                for (Media m : cart.getItemsOrdered()) {
                    if (m.getId() == id) filtered.add(m);
                }
            } catch (NumberFormatException ignored) {}
        } else {
            for (Media m : cart.getItemsOrdered()) {
                if (m.getTitle() != null && m.getTitle().toLowerCase().contains(filterText)) {
                    filtered.add(m);
                }
            }
        }
        tblMedia.setItems(filtered);
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected instanceof Playable) {
            try {
                ((Playable) selected).play();
                showInfo("Playing: " + selected.getTitle(), "Now Playing");
            } catch (PlayerException e) {
                showError(e.getMessage());
            }
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selected = tblMedia.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cart.removeMedia(selected);
            updateTotalCost();
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            showInfo("Your cart is empty!", "Cart Empty");
            return;
        }
        showInfo(String.format("Order placed! Total: %.2f $\nThank you for shopping at AIMS!", cart.totalCost()), "Order Placed");
        cart.emptyCart();
        updateTotalCost();
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
    }

    @FXML
    void viewStore(ActionEvent event) {
        // This is handled by CartScreen.java (Swing side)
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Illegal Media Length");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showInfo(String msg, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
