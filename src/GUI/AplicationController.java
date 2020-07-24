package GUI;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AplicationController {

//    Clear Button
    @FXML
    Button clearButton;
    @FXML
    public void clearWorld(){
        world.getChildren().clear();
    }
//    Save button
    @FXML
    Button saveButton;

    //    Circle
    @FXML
    Button circle;
    // Circle variables:
    boolean drawCircle = false;
    boolean setRadius = false;
    Circle c;

    @FXML
    public void DrawCircle() {
        if (!drawCircle) {
            drawCircle = true;
            drawLine = false;
            drawPencil = false;
        } else {
            drawCircle = false;
            setRadius = false;
        }
    }


//        Line
    @FXML
    Button lineButton;
    boolean drawLine = false;
    boolean setLineLength = false;
    Line line;

    @FXML
    public void drawLine() {
        if (!drawLine) {

            drawCircle = false;
            drawLine = true;
            drawPencil = false;

        } else {
            drawLine = false;
            setLineLength = false;
        }
    }

//        Pencil
    @FXML
    Button PencilButton;
    boolean drawPencil = false;
    boolean fistPointSet = false;



    @FXML
    public void drawPencil() {

        if (!drawPencil) {
            drawCircle = false;
            drawLine = false;
            drawPencil = true;
        } else {
            drawPencil = false;
            fistPointSet = false;
        }
    }


    @FXML
    Pane world;


    @FXML
    public void inintliazie() {

    }

    @FXML
    public void PaneActions(Event e) {
        e = (MouseEvent) e;
        double xCenter = ((MouseEvent) e).getX();
        double yCenter = ((MouseEvent) e).getY();

        if (drawCircle) {
            if (!setRadius) {
                c = new Circle(xCenter, yCenter, 1);

                world.getChildren().add(c);
                setRadius = true;
            } else {
                double radius = calculateDistanceBetweenClicks(c.getCenterX(), c.getCenterY(), xCenter, yCenter);
                c.setRadius(radius);
                c.setFill(Color.TRANSPARENT);
                c.setStrokeWidth(2);
                c.setStroke(Color.RED);
                setRadius = false;
            }
        }

        if (drawLine) {
            if (!setLineLength) {
                line = new Line(xCenter, yCenter, xCenter + 1, yCenter + 1);
                world.getChildren().add(line);
                setLineLength = true;
            } else {
                line.setEndX(xCenter);
                line.setEndY(yCenter);
                setLineLength = false;
            }
        }



    }

    @FXML
    public void drawPencilOnPane(Event e ){
         e =(MouseEvent)e;
         double x = ((MouseEvent) e).getX();
         double y = ((MouseEvent) e).getY();
             if (drawPencil) {
                 if (!fistPointSet) {
                     Circle c = new Circle(x,y,2);
                     world.getChildren().add(c);
                     fistPointSet = true;
                 } else {

                     fistPointSet = false;
                 }
             }

    }

    public double calculateDistanceBetweenClicks(double xFirst, double yFirst, double xSecond, double ySecond) {

        return Math.sqrt(Math.pow(xFirst - xSecond, 2) + Math.pow(yFirst - ySecond, 2));
    }


    @FXML
    public void savaAsPng(){
        WritableImage image = world.snapshot( new SnapshotParameters(),null);

        File file = new File("paint.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image,null),"png",file);

        } catch (IOException e){
            System.out.println("Poblem creating files");
        }
    }


}
