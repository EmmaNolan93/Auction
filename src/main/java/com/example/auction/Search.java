package com.example.auction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class Search {
    Stage stage;
    Parent root;
    Boolean lot = false;
    Boolean bidder = false;
    @FXML
    private TextField s;
    @FXML
    private ListView<String> table, table2;
    @FXML
    private Button  r;

    public  void searchLot(){ // Searches through the Lots tha matches the Entered Info
        lot = true;
        bidder = false;
        if (table.getItems() != null) {
            table.getItems().clear();
        }
        while ( AddAuctionLotController.list.head != null) {
            if (AddAuctionLotController.list.head.getContents().getTitle().equals(s.getText()) || AddAuctionLotController.list.head.getContents().getType().equals(s.getText()) || AddAuctionLotController.list.head.getContents().getYear().equals(s.getText())) {
                table.getItems().add(AddAuctionLotController.list.head.getContents().toString());
            }
            AddAuctionLotController.list.head = AddAuctionLotController.list.head.next;
        }
        AddAuctionLotController.list.head = AddAuctionLotController.list.current;
        while (SellController.list.head !=null){
            if(SellController.list.head.getContents().getTitle().equals(s.getText()) || SellController.list.head.getContents().getType().equals(s.getText()) || SellController.list.head.getContents().getYear().equals(s.getText())){
                table.getItems().add(SellController.list.head.getContents().toString());
            }
            SellController.list.head = SellController.list.head.next;
        }
        SellController.list.head = SellController.list.current;
    }

    public void bid() { // gets the bid made by a bidder or a bid on a auction lot
        while( lot && AddAuctionLotController.list.head != null && !AddAuctionLotController.list.head.getContents().toString().equals(table.getSelectionModel().getSelectedItem())){
            AddAuctionLotController.list.head = AddAuctionLotController.list.head.next;
        }
        while (bidder && BidderController.list.head != null && !BidderController.list.head.getContents().toString().equals(table.getSelectionModel().getSelectedItem())){
            BidderController.list.head = BidderController.list.head.next;
        }
        while (BidderController.list.head != null ) {
            if (BidderController.list.head.getContents().list.head == null) {
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
            }
            if (table2.getItems() != null) {
                table2.getItems().clear();
            }
            while(BidderController.list.head.getContents().list.head != null) {
                if (BidderController.list.head.getContents().list.head.getContents().getTitle().equals(AddAuctionLotController.list.head.getContents().getTitle()) && lot|| BidderController.list.head.getContents().list.head.getContents().getName().equals(BidderController.list.head.getContents().getName()) && bidder) {
                    Objects.requireNonNull(table2.getItems()).add(BidderController.list.head.getContents().list.head.getContents().string());
                }
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.head.next;
            }
            BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
            BidderController.list.head = BidderController.list.head.next;
        }
        System.out.println("Done");
        AddAuctionLotController.list.head = AddAuctionLotController.list.current;
        BidderController.list.head = BidderController.list.current;
        if(BidderController.list.head != null){
            BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
        }
    }

    public  void searchBidder() { // Searches through Bidders that matches the  entered info
        bidder = true;
        lot = false;
        if (table.getItems() != null) {
            table.getItems().clear();
        }
        while (BidderController.list.head != null) {
            if (BidderController.list.head.getContents().getName().equals(s.getText()) || BidderController.list.head.getContents().getAddress().equals(s.getText())) {
                table.getItems().add(BidderController.list.head.getContents().toString());
            }
            BidderController.list.head = BidderController.list.head.next;
        }
        BidderController.list.head = BidderController.list.current;
    }
    public void goBacks(ActionEvent e) throws Exception {
        if (e.getSource() == r) {
            stage = (Stage) r.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
