package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton eraseBtn;

    @FXML
    private ToggleGroup identical;

    @FXML
    private RadioButton penBtn;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        if (event.getX() >= 0 && event.getX() <= drawingAreaPane.getWidth() && event.getY() >= 0 && event.getY() <= drawingAreaPane.getHeight()){
            if (penBtn.isSelected()) {
                Circle newCircle = new Circle(event.getX(), event.getY(), 4, Color.LIGHTPINK);
                drawingAreaPane.getChildren().add(newCircle);
            } else if (eraseBtn.isSelected()) {
                Circle newCircle = new Circle(event.getX(), event.getY(), 10, Color.WHITE);
                drawingAreaPane.getChildren().add(newCircle);
            }
        }

    }
}
