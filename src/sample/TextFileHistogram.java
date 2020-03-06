package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

//CSCI2020U-Assignment 1-Question 4
//Java program by Nicolas Belair 100709799
//Takes text file url from user and displays amount of time each letter of the alphabet
//is encountered in a bar chart

public class TextFileHistogram extends Application {
    @Override
    public void start(Stage stage) {
        //initialize bar chart that has an x-axis category for each letter of the English alphabet
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(
                "A","B","C","D","E","F","G","H","I","J","K","L","M",
                "N","O","P","Q","R","S","T","U","V","W","X","Y","Z")));
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Occurrences in Text File");
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        //initialize HBox
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.TOP_CENTER);
        bottomBox.setSpacing(5);
        bottomBox.setPadding(new Insets(3, 3, 3, 3));

        //create and add label and text field to HBox
        TextField fileNameField = new TextField();
        fileNameField.setText("C://");
        bottomBox.getChildren().addAll(new Label("File Name"), fileNameField);

        //if the user presses and releases the ENTER key inside the textField
        fileNameField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                //pull input text file name
                String fileName=fileNameField.getText();
                //reset textField
                fileNameField.setText("C://");

                //attempt to pull text from text file
                String fileText = "";
                try {
                    fileText = getTextFromFile(fileName);
                }
                //if text file is invalid
                catch (Exception e) {
                    System.exit(0);
                }

                //array that stores how many times each letter is encountered
                int[] density = new int[26];
                //parse obtained text for letters, count each occurrence of each letter from A-Z
                for(int i=0; i<fileText.length(); i++) {
                    int num = (int)fileText.charAt(i);

                    //upper case
                    if(num>=65 && num<=90) {
                        density[num-65]++;
                    }
                    //lower case
                    else if(num>=97 && num<=122) {
                        density[num-97]++;
                    }
                }

                //clear bar chart's previous data(if any) before giving it new data
                barChart.getData().clear();
                //generate new bar chart data
                XYChart.Series s1 = new XYChart.Series();
                s1.setName(fileName);
                for(int i=0; i<26; i++) {
                    s1.getData().add(new XYChart.Data(String.valueOf((char) (i + 65)), density[i]));
                }
                //add data to bar chart
                barChart.getData().add(s1);
            }
        });// end ENTER key event

        //create VBox to hold the bar chart and the HBox
        VBox vbox = new VBox();
        vbox.setPrefWidth(700);
        vbox.getChildren().addAll(barChart, bottomBox);

        Scene scene = new Scene(vbox);

        stage.setTitle("Text File Density Histogram Generator");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }// end start()


    public static void main(String[] args) {
        Application.launch(args);
    }// end main()


    //function that attempts to pull text from text file of name fileName
    //returns said text as a concatenated String
    private String getTextFromFile(String fileName) throws Exception {
        File textFile= new File(fileName);
        //if textFile is invalid, exit
        if(!textFile.exists()) {
            System.exit(0);
        }
        //get text data from textFile if it is valid
        Scanner input = new Scanner(textFile);
        String text = "";
        while(input.hasNext()) {
            text += input.nextLine();
        }
        return text;
    }// end getTextFromFile()
}
