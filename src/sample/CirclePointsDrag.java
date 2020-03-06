package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;

//CSCI2020U-Assignment 1-Question 3
//Java program by Nicolas Belair 100709799
//Displays a circle with three vertices (points) on it, which are draggable along the circle's circumference.
//draws a triangle with the three vertices as its corners, and displays the angles of each corner

public class CirclePointsDrag extends Application {
    @Override
    public void start(Stage stage) {
        //initialize 300x300 pane
        Pane pane = new Pane();
        pane.setPrefSize(300,300);

        //create circle on which the vertices will travel
        Circle bigCircle = new Circle(150,150, 120);
        bigCircle.setFill(Color.WHITE);
        bigCircle.setStroke(Color.BLACK);

        //create vertices
        Circle vertex1 = new Circle(150, 30, 5);
        vertex1.setFill(Color.RED);
        Circle vertex2 = new Circle(46, 210, 5);
        vertex2.setFill(Color.RED);
        Circle vertex3 = new Circle(254, 210, 5);
        vertex3.setFill(Color.RED);

        //create edges that run between vertices
        Line edge12 = new Line(150,30,46,210);
        Line edge13 = new Line(150,30, 254, 210);
        Line edge23 = new Line(46, 210, 254, 210);

        //create text boxes that display the angles between the edges meeting in a particular vertex
        Text angle1 = new Text(155,30, calculateAngle(edge13, edge12, edge23));
        Text angle2 = new Text(51,210, calculateAngle(edge23, edge12, edge13));
        Text angle3 = new Text(259,210, calculateAngle(edge13, edge23, edge12));


        //if vertex 1 is moved
        vertex1.setOnMouseDragged(event -> {
            //get by how much the vertex has been moved
            double dx = event.getX()-150;
            double dy = event.getY()-150;
            //adjust values so the vertex stays on the circle
            double newX = 120*(dx/Math.sqrt(dx*dx+dy*dy))+150;
            double newY = 120*(dy/Math.sqrt(dx*dx+dy*dy))+150;

            moveVertex1(vertex1, edge12, edge13, newX, newY);

            //update angle displays
            angle1.setX(newX+5);
            angle1.setY(newY);
            angle1.setText(calculateAngle(edge13, edge12, edge23));
            angle2.setText(calculateAngle(edge23, edge12, edge13));
            angle3.setText(calculateAngle(edge13, edge23, edge12));
        });

        //if vertex 2 is moved
        vertex2.setOnMouseDragged(event -> {
            //get by how much the vertex has been moved
            double dx = event.getX()-150;
            double dy = event.getY()-150;
            //adjust values so the vertex stays on the circle
            double newX = 120*(dx/Math.sqrt(dx*dx+dy*dy))+150;
            double newY = 120*(dy/Math.sqrt(dx*dx+dy*dy))+150;

            moveVertex2(vertex2, edge12, edge23, newX, newY);

            //update angle displays
            angle2.setX(newX+5);
            angle2.setY(newY);
            angle1.setText(calculateAngle(edge13, edge12, edge23));
            angle2.setText(calculateAngle(edge23, edge12, edge13));
            angle3.setText(calculateAngle(edge13, edge23, edge12));
        });

        //if vertex 3 is moved
        vertex3.setOnMouseDragged(event -> {
            //get by how much the vertex has been moved
            double dx = event.getX()-150;
            double dy = event.getY()-150;
            //adjust values so the vertex stays on the circle
            double newX = 120*(dx/Math.sqrt(dx*dx+dy*dy))+150;
            double newY = 120*(dy/Math.sqrt(dx*dx+dy*dy))+150;

            moveVertex3(vertex3, edge13, edge23, newX, newY);

            //update angle displays
            angle3.setX(newX+5);
            angle3.setY(newY);
            angle1.setText(calculateAngle(edge13, edge12, edge23));
            angle2.setText(calculateAngle(edge23, edge12, edge13));
            angle3.setText(calculateAngle(edge13, edge23, edge12));
        });

        //add all visual elements to pane
        pane.getChildren().addAll(bigCircle, vertex1, vertex2, vertex3, edge12, edge13, edge23, angle1, angle2, angle3);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Draggable Triangle Vertices Along Circle");
        stage.show();
    }// end start()


    public static void main(String[] args) {
        Application.launch(args);
    }// end main()


    //calculates angle between two edges meeting at a vertex
    private String calculateAngle(Line adj1, Line adj2, Line op) {
        double adj1Len = edgeLength(adj1);
        double adj2Len = edgeLength(adj2);
        double opLen = edgeLength(op);

        //format so the final output has 1 decimal place
        DecimalFormat df = new DecimalFormat("###.#");
        //calculate angle using cosine law, return angle
        return String.valueOf(df.format(Math.toDegrees(Math.acos((opLen*opLen-adj1Len*adj1Len-adj2Len*adj2Len)/(-2*adj1Len*adj2Len)))));
    }// end calculateAngle


    //calculates length of an edge using Pythagorean theorem
    private double edgeLength(Line v) {
        return Math.sqrt(Math.pow(v.getEndX()-v.getStartX(),2)+Math.pow(v.getEndY()-v.getStartY(),2));
    }// end edgeLength()


    //moveVertex functions; adjust the position of a vertex, as well as the ends of any edge touching them
    private void moveVertex1(Circle vertex1, Line edge12, Line edge13, double newX, double newY) {
        //move vertex
        vertex1.setCenterX(newX);
        vertex1.setCenterY(newY);

        //move edge ends terminating in moved vertex
        edge12.setStartX(newX);
        edge12.setStartY(newY);
        edge13.setStartX(newX);
        edge13.setStartY(newY);
    }// end moveVertex1

    private void moveVertex2(Circle vertex2, Line edge12, Line edge23, double newX, double newY) {
        //move vertex
        vertex2.setCenterX(newX);
        vertex2.setCenterY(newY);

        //move edge ends terminating in moved vertex
        edge12.setEndX(newX);
        edge12.setEndY(newY);
        edge23.setStartX(newX);
        edge23.setStartY(newY);
    }// end moveVertex2

    private void moveVertex3(Circle vertex3, Line edge13, Line edge23, double newX, double newY) {
        //move vertex
        vertex3.setCenterX(newX);
        vertex3.setCenterY(newY);

        //move edge ends terminating in moved vertex
        edge13.setEndX(newX);
        edge13.setEndY(newY);
        edge23.setEndX(newX);
        edge23.setEndY(newY);
    }// end moveVertex3
    // end moveVertex functions
}
