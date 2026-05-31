package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penBtn;

    @FXML
    private RadioButton eraserBtn;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // If coordinate is in the drawing area pane, draw a dot
        if (event.getX() >= 0 && event.getY() >= 0 && event.getX() <= drawingAreaPane.getWidth() && event.getY() <= drawingAreaPane.getHeight()) {
            Circle newCircle;
            if (penBtn.isSelected()) {
                newCircle = new Circle(event.getX(), event.getY(), 4, Color.BLACK);
            } else {
                // Eraser uses white ink (matching canvas color)
                newCircle = new Circle(event.getX(), event.getY(), 8, Color.WHITE);
            }
            drawingAreaPane.getChildren().add(newCircle);
        }
    }
}
