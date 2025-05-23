package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.CartFullException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lbCost;

    @FXML
    private Label lbTitle;

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        try {
            cart.addMedia(media);
        } catch (CartFullException e) {
            showAlert(Alert.AlertType.ERROR, "Cart full, cannot add to cart", e.getMessage());
        }
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        if (media instanceof Playable) {
            try {
                String details = ((Playable) media).play(true);
                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Media playing",
                        details
                );
            } catch (PlayerException e) {
                showAlert(
                        Alert.AlertType.ERROR,
                        "Error playing media",
                        e.getMessage()
                );
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Cart cart;
    public ItemController(Cart cart){
        this.cart = cart;
    }

    private Media media;
    public void setData(Media media){
        this.media = media;
        lbTitle.setText(media.getTitle());
        lbCost.setText(String.format("%.2f $", media.getCost()));
        if(media instanceof Playable){
            btnPlay.setVisible(true);
        }
        else{
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

}
