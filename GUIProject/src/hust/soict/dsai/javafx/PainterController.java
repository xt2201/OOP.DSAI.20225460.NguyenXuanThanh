package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PainterController {
	
	private Color col = Color.BLACK; // storing the color of the pen, eraser
	private int siz; // storing the size of the pen, eraser
	
    @FXML
    private Pane drawingAreaPane;

    @FXML
    void clearButtonPressed(ActionEvent event) {
    	drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
    	Circle newCircle = new Circle(event.getX(),
    			event.getY(), siz, col);
    	
    	drawingAreaPane.getChildren().add(newCircle);
    	
    	// the following lines of code are for prevent circles go out of the pane
    	Rectangle clipRect = new Rectangle(drawingAreaPane.getWidth(), drawingAreaPane.getHeight());
    	clipRect.heightProperty().bind(drawingAreaPane.heightProperty());
    	clipRect.widthProperty().bind(drawingAreaPane.widthProperty());
    	drawingAreaPane.setClip(clipRect);

    }
    
    @FXML
    void eraserOn(ActionEvent event) {
    	col = Color.WHITE;
    	siz = 30;

    }
    
    @FXML
    void penOn(ActionEvent event) {
    	col = Color.BLACK;
    	siz = 4;

    }

}

