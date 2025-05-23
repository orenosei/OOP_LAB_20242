package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.MediaNotFoundException;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;


public class CartController {

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private Label costLabel;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TextField tfFilter;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia instanceof Playable) {
            try {
                String details = ((Playable) selectedMedia).play(true);
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

    @FXML
    void btnRemovePressed(ActionEvent event) throws MediaNotFoundException{
        try {
            Media media = tblMedia.getSelectionModel().getSelectedItem();
            cart.removeMedia(media);
        } catch (MediaNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Remove error", e.getMessage());
        }
    }


    @FXML
    void btnViewStorePressed(ActionEvent event) throws IOException{
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            ViewStoreController viewStoreController = new ViewStoreController(store, cart);
            fxmlLoader.setController(viewStoreController);

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Cart cart;
    private Store store;
    public CartController(Store store, Cart cart){
        this.cart = cart;
        this.store = store;
    }

    private FilteredList<Media> filteredList;
    private void showFilteredMedia(String keyword) {
        Predicate<Media> predicate = media -> {
            if (keyword == null || keyword.isEmpty()) return true;
            String lowerKeyword = keyword.toLowerCase();
            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(lowerKeyword);
            } else {
                return media.getTitle().toLowerCase().contains(lowerKeyword);
            }
        };
        filteredList.setPredicate(predicate);
    }


    void updateButonBar(Media media){
        if(media == null){
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        }
        else{
            btnRemove.setVisible(true);
            btnPlay.setVisible(media instanceof Playable);
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) throws IllegalStateException{
        try {
            if (cart.getItemsOrdered().isEmpty()) {
                throw new IllegalStateException("Cart is empty");
            }

            Optional<ButtonType> result = showConfirmation("Confirm", "Are you sure you want to place order?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                cart.getItemsOrdered().clear();
                updateTotalCost();
                showAlert(Alert.AlertType.INFORMATION, "Successful", "Order placed successfully");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error order placed", e.getMessage());
        }
    }


    private void updateTotalCost() {
        float total = cart.totalCost();
        costLabel.setText(String.format("%.2f $", total));
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Optional<ButtonType> showConfirmation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    @FXML
    public void initialize(){
        colMediaId.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        if(cart.getItemsOrdered() != null) tblMedia.setItems(cart.getItemsOrdered());

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                updateButonBar(newValue);
            }
        });

        updateTotalCost();

        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> {
            updateTotalCost();
        });

        filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredList);

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia(newValue);
        });

        filterCategory.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia(tfFilter.getText());
        });
    }

}
