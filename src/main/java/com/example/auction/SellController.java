package com.example.auction;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class SellController {
    String p;
    Stage stage;
    Parent root;
    @FXML
    private ImageView img;
    @FXML
    private TextField f;
    @FXML
    private Label Name, Bid;
    @FXML
    private Button r;
    static LinkedList<SoldAuctionLot> list = new LinkedList<>();

    public void submits(){ // Makes sure everything is filled out before the data is added to the linked list.
        int empty = String.valueOf(Name.getText()).equals("")? 0:
                String.valueOf(Bid.getText()).equals("")? 0:
                        String.valueOf(img.getImage()).equals("null")? 0:1;
        if(empty == 1 ) {
            while(AddAuctionLotController.list.head != null && !AddAuctionLotController.list.head.getContents().getUrl().equals(p)){
                AddAuctionLotController.list.head = AddAuctionLotController.list.head.next;
            }
            if(AddAuctionLotController.list.head !=null) {
                SoldAuctionLot lot = new SoldAuctionLot(Name.getText(), AddAuctionLotController.list.head.getContents().getTitle(), AddAuctionLotController.list.head.getContents().getType(), AddAuctionLotController.list.head.getContents().getDescription(), AddAuctionLotController.list.head.getContents().getYear(), Integer.parseInt(Bid.getText()), p);
                list.addElement(lot); // Adds to the sold lot linked list
                try {
                    save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AddAuctionLotController.list.delVA(AddAuctionLotController.list.head);// deletes the sold lot form the unsold lot auction linked list
                try {
                    AddAuctionLotController.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            AddAuctionLotController.list.head = AddAuctionLotController.list.current;
        }
        else {
            System.out.println("Everything must be filled in");
        }
    }

    public void pic(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            while (AddAuctionLotController.list.head != null && !AddAuctionLotController.list.head.getContents().getTitle().equals(f.getText()))
                AddAuctionLotController.list.head = AddAuctionLotController.list.head.next;
            if (AddAuctionLotController.list.head != null) {
                p = AddAuctionLotController.list.head.getContents().getUrl();
                Image image1 = new Image(p);
                img.setImage(image1);
                getHighestBid();
            }
        }
    }
    public void getHighestBid(){
        while (BidderController.list.head != null) {
            //BidderController.list.head = BidderController.list.head.next;
            if (BidderController.list.head.getContents().list.head == null) {
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
            }
            while (BidderController.list.head.getContents().list.head != null && !BidderController.list.head.getContents().list.head.getContents().getUrl().equals(p)) {
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.head.next;
            }
            if (BidderController.list.head.getContents().list.head != null) {
                Name.setText(BidderController.list.head.getContents().getEmail());
                Bid.setText(String.valueOf(BidderController.list.head.getContents().list.head.getContents().getAmount()));
            }
            else {
                System.out.println("This lot has no bids on it");
            }
            BidderController.list.head = BidderController.list.head.next;
        }
        BidderController.list.head = BidderController.list.current;
        BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
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
    public static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("AuctionSold.xml"));
        list.head = (LinkedList<SoldAuctionLot>.node<SoldAuctionLot>) is.readObject();
        is.close();
    }

    public static void save() throws Exception {
        XStream xStream = new XStream(new DomDriver());
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("AuctionSold.xml"));
        out.writeObject(list.head);
        out.close();
    }
}
