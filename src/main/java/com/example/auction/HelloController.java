package com.example.auction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloController {
    Stage stage;
    Parent root;
    @FXML
    private Label welcomeText;
    @FXML
    private Button A, bid, addbid, Sell, Edit;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome!");
    }

    public void goToAuction(ActionEvent e) throws  Exception{
        if(e.getSource()== A){
            stage = (Stage) A.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AuctionLot.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToBidder(ActionEvent e) throws  Exception{
        if(e.getSource()== bid){
            stage = (Stage) bid.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Bidder.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToAddBid(ActionEvent e) throws  Exception{
        if(e.getSource()== addbid){
            stage = (Stage) addbid.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Addbid.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToSell(ActionEvent e) throws  Exception{
        if(e.getSource()== Sell){
            stage = (Stage) Sell.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Sell.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToEdit(ActionEvent e) throws  Exception{
        if(e.getSource()== Edit){
            stage = (Stage) Edit.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditLot.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}