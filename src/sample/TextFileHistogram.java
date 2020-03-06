package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

//CSCI2020U-Assignment 1-Question 4
//Java program by Nicolas Belair 100709799
//Takes values for Investment Amount, Years, Annual Interest Rate and Future Value from the user,
//calculates and returns the future value of their investment.

public class TextFileHistogram extends Application {

    @Override
    public void start(Stage stage) {
        GridPane bottomPane = new GridPane();
        bottomPane.setHgap(5);
        bottomPane.setPadding(new Insets(3, 3, 3, 3));

        //create and add labels and text fields to gridpane
        bottomPane.add(new Label("File Name"), 0,0);
        TextField fileNameField = new TextField();
        fileNameField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                String fileName=fileNameField.getText();







                VBox vbox = new VBox();
                vbox.getChildren().addAll(bottomPane);

                Scene scene = new Scene(vbox);

                stage.setTitle("Investment Value Calculator");
                stage.setScene(scene);
                stage.sizeToScene();
                stage.show();
            }
        });
        //pane.add(fileNameField, 0, 1);

    }// end start()

    public static void main(String[] args) {
        Application.launch(args);
    }// end main()

    private String getTextFromFile(String fileName) throws Exception{
        File textFile= new File(fileName);
        if(!textFile.exists()) {
            System.out.println("Source file '" + textFile + "' does not exist");
            System.exit(0);
        }

        Scanner input = new Scanner(textFile);

        while(input.hasNext()) {
            String text = "";
            text += input.nextLine();
        }
        input.close();
    }

}
