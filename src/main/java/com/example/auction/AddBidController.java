package com.example.auction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Objects;



public class AddBidController {
    Stage stage;
    Parent root;
    String p;
    boolean b = false;
    boolean vC = false;
    @FXML
    private ListView<String> view, view2;
    @FXML
    private ImageView img;
    @FXML
    private TextField bD, amount, askPrice;
    @FXML
    private Label highestBid;
    @FXML
    private Button r;

    public void initialize() {
        table();
    }

    public void submits() { // Makes sure everything is filled out before the data is added to the linked list.
        int empty = String.valueOf(amount.getText()).equals("") ? 0 :
                String.valueOf(img.getImage()).equals("null") ? 0 :
                        String.valueOf(bD.getText()).equals("") ? 0 : 1;
        if (empty == 1 && b && vC) {
            while(BidderController.list.head != null && !BidderController.list.head.getContents().getEmail().equals(bD.getText())){
                BidderController.list.head = BidderController.list.head.next;
            }
            if(BidderController.list.head != null) {
                Addbid bid = new Addbid(Integer.parseInt(amount.getText()), p, String.valueOf(view.getSelectionModel().getSelectedItem()), BidderController.list.head.getContents().getName());
                //String id = BidderController.list.head.getContents().getName();
                //addVP(id, bid);
                BidderController.list.head.getContents().list.addElement(bid);//Adds the Bid made by the correct Bidder to the Add bid linked list which is attacthed to the Bidder
                try {
                    BidderController.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                b = false;
                vC = false;
                //BidderController.list.head = BidderController.list.current;
            }
            else {
                System.out.println("No such email exists");
                bD.setText(null);
            }
        } else {
            System.out.println("Everything must be filled in");
        }
        BidderController.list.head = BidderController.list.current;
        //BidderController.list.head.getContents().list.head.next = BidderController.list.head.getContents().list.current;
    }

    public void userEmail(KeyEvent keyEvent) { // Makes sure the Email name has not been entered before but must press enter to check
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (BidderController.isValid(bD.getText())) {
                vC = true;
                System.out.println("Valid Email ");
            }
            if (!vC) {
                System.out.println("Invalid Email");
            }
        }
    }

   /* public void addbid(Addbid bid) {
        while (BidderController.list.head != null && !BidderController.list.head.getContents().getEmail().equals(bD.getText())) {
            BidderController.list.head = BidderController.list.head.next;
        }
        if (BidderController.list.head.getContents().list.head != null) {
            //highestBid.setText(String.valueOf(BidderController.list.head.getContents().list.head.getContents().getHighestBid()));
            BidderController.list.head.getContents().list.head.getContents().setHighestBid(Integer.parseInt(amount.getText()));
        }
        BidderController.list.head.getContents().addBid(bid);
        BidderController.list.head = BidderController.list.current;
        //System.out.println(BidderController.list.head.getContents().list.head.getContents().toString());
    }*/

    /*public static void addVP(String id, Addbid p) { // Creates the appointments
        boolean flag = false;
        while (!flag) {
            while( BidderController.list.head != null && !BidderController.list.head.getContents().getName().equals(id)){
                BidderController.list.head = BidderController.list.head.next;
            }
            if(BidderController.list.head != null){
                BidderController.list.head.getContents().addBid(p);
                System.out.println(BidderController.list.head.getContents().printVR());
                flag = true;
            }
        }
        BidderController.list.head = BidderController.list.current;
    }*/

    public void table() { // Adds Auction lots to the list
        AddAuctionLotController.list.head = AddAuctionLotController.list.current;
        if (AddAuctionLotController.list.head == null) {
            System.out.println("No are no Auction lots added to the system");
        }
        while (AddAuctionLotController.list.head != null) {
            view.getItems().add(AddAuctionLotController.list.head.getContents().getTitle());
            AddAuctionLotController.list.head = AddAuctionLotController.list.head.next;
        }
        System.out.println("Done");
        AddAuctionLotController.list.head = AddAuctionLotController.list.current;
    }

    public void table2() { // Adds any bids made on the auction lot to the table 2
        if (view2.getItems() != null) {
            view2.getItems().clear();
        }
        while (BidderController.list.head != null ) {
            if (BidderController.list.head.getContents().list.head == null) {
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
                //System.out.println("No are no current Bids");
            }
            // BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
            while (BidderController.list.head.getContents().list.head != null && !BidderController.list.head.getContents().list.head.getContents().getUrl().equals(p)) {
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.head.next;
            }
            if (BidderController.list.head.getContents().list.head != null) {
                Objects.requireNonNull(view2.getItems()).add(BidderController.list.head.getContents().list.head.getContents().getName());
                //BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
            } else {
                System.out.println("No are no current Bids on this lot");
            }
            //BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
            BidderController.list.head = BidderController.list.head.next;
        }
        System.out.println("Done");
        BidderController.list.head = BidderController.list.current;
        BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
    }

   /* public void table2() {
        add(view, view2);
    } // Add boots to the cb based on which vc is chosen form the above choiceBox

    static void add(ListView<String> view, ListView<String> view2) {
        int i = view.getSelectionModel().getSelectedIndex();
        int count = 0;
        count(view2, i, count, AddAuctionLotController.list.head);
    }

    static void count(ListView<String> view2, int i, int count,LinkedList<AddAuctionLot>.node<AddAuctionLot> head) {
        while (head != null && count != i) {
            count++;
            head = head.next;
        }
        if (head != null) {
            //VaccinationCenter vc = add.getContents();
            //BoothNode bb = vc.head;
            while (BidderController.list.head.getContents().list.head != null) {
                view2.getItems().add(BidderController.list.head.getContents().list.head.getContents().getName());
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.head.next;
            }
            BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
        }
    }*/

    public void pic() { // Sets the image to the matching Lot that is selected
        while (AddAuctionLotController.list.head != null && !AddAuctionLotController.list.head.getContents().getTitle().equals(view.getSelectionModel().getSelectedItem())) {
            AddAuctionLotController.list.head = AddAuctionLotController.list.head.next;
        }
        if (AddAuctionLotController.list.head != null) {
            p = AddAuctionLotController.list.head.getContents().getUrl();
            Image image1 = new Image(p);
            img.setImage(image1);
            askPrice.setText(String.valueOf(AddAuctionLotController.list.head.getContents().getPrice()));

        }
        System.out.println("Done");
        AddAuctionLotController.list.head = AddAuctionLotController.list.current;
    }

    public void highestBid() { // Gets the Highest Bid
        while (BidderController.list.head != null) {
            while (BidderController.list.head.getContents().list.head != null && !BidderController.list.head.getContents().list.head.getContents().getTitle().equals(view.getSelectionModel().getSelectedItem())) {
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.head.next;
            }
            if (BidderController.list.head.getContents().list.head != null) {
                if (BidderController.list.head.getContents().list.head.getContents().getHighestBid() == 0) {
                    highestBid.setText(String.valueOf(BidderController.list.head.getContents().list.head.getContents().getAmount()));
                } else {
                    highestBid.setText(String.valueOf(BidderController.list.head.getContents().list.head.getContents().getHighestBid()));
                }
                if (Integer.parseInt(highestBid.getText()) < Integer.parseInt(amount.getText())) {
                    System.out.println("This bid has been approved ");
                    b = true;
                } else {
                    System.out.println("Cannot accept bid as it is less than the current highest bid");
                }
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
            }
            BidderController.list.head = BidderController.list.head.next;
        }
        if (BidderController.list.head == null) {
            System.out.println("There is no current bid on this lot");
            b = true;
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

    public void selectBid() { // Gets the Bidder info that made the bid
        while (BidderController.list.head != null) {
            //BidderController.list.head = BidderController.list.head.next;
            while (BidderController.list.head.getContents().list.head != null && !BidderController.list.head.getContents().list.head.getContents().getName().equals(view2.getSelectionModel().getSelectedItem())) {
                BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.head.next;
            }
            if (BidderController.list.head.getContents().list.head != null) {
                bD.setText(BidderController.list.head.getContents().getEmail());
                amount.setText(String.valueOf(BidderController.list.head.getContents().list.head.getContents().getAmount()));
            }
            BidderController.list.head = BidderController.list.head.next;
        }
        BidderController.list.head = BidderController.list.current;
        BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
    }

    public void withDrawBid() { // Withdraws the bid if it matches any email and bid made
        int empty = String.valueOf(bD.getText()).equals("") ? 0 :
                String.valueOf(amount.getText()).equals("") ? 0 : 1;
        if (empty == 1) {
            while (BidderController.list.head != null) {
                //BidderController.list.head = BidderController.list.head.next;
                if (BidderController.list.head.getContents().list.head == null) {
                    BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
                    //System.out.println("No are no current Bids");
                }
                while (BidderController.list.head.getContents().list.head != null && BidderController.list.head.getContents().list.head.getContents().getAmount() != Integer.parseInt(amount.getText())) {
                    BidderController.list.head = BidderController.list.head.next;
                }
                if (BidderController.list.head.getContents().list.head != null) {
                    BidderController.list.head.getContents().delBid(String.valueOf(BidderController.list.head.getContents().list.head.getContents().getName()));
                    //highestBid.setText(null);
                    try {
                        BidderController.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Deleted");
                }
                BidderController.list.head = BidderController.list.head.next;
            }
        }
        BidderController.list.head = BidderController.list.current;
        BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
    }
    public void withDrawLot(){ // withdraws the Selected Auction lot
        int empty = String.valueOf(img.getImage()).equals("null") ? 0 :1;
        if(empty == 1){
            while(AddAuctionLotController.list.head != null && !AddAuctionLotController.list.head.getContents().getUrl().equals(p)){
                AddAuctionLotController.list.head = AddAuctionLotController.list.head.next;
            }
            while (BidderController.list.head != null) {
                //BidderController.list.head = BidderController.list.head.next;
                while (BidderController.list.head.getContents().list.head != null && BidderController.list.head.getContents().list.head.getContents().getUrl().equals(p)) {
                    BidderController.list.head = BidderController.list.head.next;
                }
                if (BidderController.list.head.getContents().list.head != null) {
                    BidderController.list.head.getContents().delBid(String.valueOf(BidderController.list.head.getContents().list.head.getContents().getName()));
                    System.out.println("Deleted");
                    try {
                        AddAuctionLotController.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BidderController.list.head.getContents().list.head = BidderController.list.head.getContents().list.current;
                }
                BidderController.list.head = BidderController.list.head.next;
            }
            if(AddAuctionLotController.list.head != null){
                AddAuctionLotController.list.delVA(AddAuctionLotController.list.head);
            }
        }
        BidderController.list.head = BidderController.list.current;
        AddAuctionLotController.list.head = AddAuctionLotController.list.current;
    }

}
