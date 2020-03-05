package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//CSCI2020U-Assignment 1-Question 1
//Java program by Nicolas Belair 100709799
//Selects 3 different cards from a total of 54, displays them

public class ThreeCards extends Application {
    @Override
    public void start(Stage stage) {
        //initialize arrays for:
        //the three image file addresses
        String[] images = new String[3];
        //the two previous card numbers, to make sure we don't get repeat cards
        int[] prevNums = new int[2];

        //generate image file names for 3 different cards out of 54
        for(int i=0; i<3; i++) {
            //generate random integer between 1 and 54
            int num = (int)(Math.random() * (54 - i))+1;
            //first card
            if (i == 0) {
                prevNums[0] = num;
                images[0] = ("sample/cards/" + num + ".png");
            }
            //second card
            if (i == 1) {
                //skip value of previous card if necessary
                if (num >= prevNums[0]) {
                    num++;
                }
                prevNums[1] = num;
                images[1] = ("sample/cards/" + num + ".png");
            }
            //third card
            if (i == 2) {
                //skip value(s) of previous cards if necessary
                if(num>=prevNums[0]) {
                    num++;
                }
                if(num>=prevNums[1]) {
                    num++;
                }
                images[2] = ("sample/cards/" + num + ".png");
            }
        }// end for loop

        //load the three card image files
        Image card1 = new Image(images[0]);
        ImageView view1 = new ImageView();
        view1.setImage(card1);

        Image card2 = new Image(images[1]);
        ImageView view2 = new ImageView();
        view2.setImage(card2);

        Image card3 = new Image(images[2]);
        ImageView view3 = new ImageView();
        view3.setImage(card3);


        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(view1, view2, view3);

        Scene scene = new Scene(hBox);

        stage.setTitle("Three Cards");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }// end start()


    public static void main(String[] args) {
        Application.launch(args);
    }// end main()
}