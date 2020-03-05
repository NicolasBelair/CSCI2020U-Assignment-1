package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

//CSCI2020U-Assignment 1-Question 2
//Java program by Nicolas Belair 100709799
//Takes values for Investment Amount, Years, Annual Interest Rate and Future Value from the user,
//calculates and returns the future value of their investment.

public class InvestValCalculator extends Application {
    @Override
    public void start(Stage stage) {
        //initialize gridpane
        GridPane pane = new GridPane();
        pane.setHgap(5);
        pane.setVgap(4);
        pane.setPadding(new Insets(7, 5, 5, 5));

        //create and add labels and text fields to gridpane
        pane.add(new Label("Investment Amount"), 0,0);
        TextField invAmount = new TextField();
        invAmount.setAlignment(Pos.CENTER_RIGHT);
        pane.add(invAmount, 1, 0);

        pane.add(new Label("Years"), 0,1);
        TextField yearsVal = new TextField();
        yearsVal.setAlignment(Pos.CENTER_RIGHT);
        pane.add(yearsVal, 1,1);

        pane.add(new Label("Annual Interest Rate"), 0, 2);
        TextField annualIntRate = new TextField();
        annualIntRate.setAlignment(Pos.CENTER_RIGHT);
        pane.add(annualIntRate, 1, 2);

        pane.add(new Label("Future Value"), 0,3);
        TextField futureVal = new TextField();
        futureVal.setAlignment(Pos.CENTER_RIGHT);
        futureVal.setDisable(true); //disabled so futureVal is read-only for the user
        pane.add(futureVal, 1,3);


        //button the user presses to calculate the future value
        Button btnCalculate = new Button("Calculate");
        //event handler class for the afformentioned calculate button
        class CalcHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                //get the values the user entered from the textFields
                double investmentAmount = parseDouble(invAmount.getText());
                int years = parseInt(yearsVal.getText());
                double monthlyInterestRate = parseDouble(annualIntRate.getText())/1200;
                //format so the final output has 2 decimal places (cents)
                DecimalFormat df = new DecimalFormat("###.##");
                //calculate and output future value of investment
                String futureValue = String.valueOf(df.format(investmentAmount*Math.pow((1+monthlyInterestRate),(years*12))));
                futureVal.setText(futureValue);
            }
        }// end CalcHandler()
        btnCalculate.setOnAction(new CalcHandler());

        HBox buttonPane = new HBox(btnCalculate);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        pane.add(buttonPane, 1, 4);

        Scene scene = new Scene(pane);

        stage.setTitle("Investment Value Calculator");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }// end start()


    public static void main(String[] args) {
        Application.launch(args);
    }// end main()
}