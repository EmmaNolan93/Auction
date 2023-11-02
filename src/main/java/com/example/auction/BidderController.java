package com.example.auction;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.regex.Pattern;

public class BidderController {
    Stage stage;
    Parent root;
    Boolean vC = false;
    Boolean pn = false;
    Boolean n = false;
    int a;
    int h;
    @FXML
    private TextField Name;
    @FXML
    private TextField Address;
    @FXML
    private TextField Tel;
    @FXML
    private TextField Email;
    @FXML
    private javafx.scene.control.Button r, Cancel, update;
    @FXML
    private ListView<String> view;
    @FXML
    private CheckBox check1, check2, check3;
    @FXML
    private Label total;

    public void initialize() {
        check1.setVisible(false);
        check2.setVisible(false);
        check3.setVisible(false);
    }
    static LinkedList<Bidder> list = new LinkedList<>();
    MyHastabke c = new MyHastabke(10);
    public void submits() { // Makes sure everything is filled out before the data is added to the linked list.
        int empty = String.valueOf(Name.getText()).equals("") ? 0 :
                String.valueOf(Address.getText()).equals("") ? 0 :
                        String.valueOf(Tel.getText()).equals("") ? 0 :
                                String.valueOf(Email.getText()).equals("") ? 0 : 1;
        if (empty == 1 && vC && pn && n) {
            Bidder bid = new Bidder(Name.getText(), Address.getText(), Integer.parseInt(Tel.getText()), Email.getText());
            list.addElement(bid);// Adds a bidder to the System
            try {
                save();
            } catch (Exception e) {
                e.printStackTrace();
            }
            vC = false;
            pn = false;
            n = false;
            table();
        } else {
            System.out.println("Everything must be filled in");
        }
    }

    public void table() { // gets all Bidders and adds it to the view list
        if (list.head == null) {
            System.out.println("No are no bidder added to the system");
        }
        if (view.getItems() != null) {
            view.getItems().clear();
            a = 0;
        }
        while (list.head != null) {
            Objects.requireNonNull(view.getItems()).add(list.head.getContents().getEmail());
            a++;
            list.head = list.head.next;
        }
        total.setText(String.valueOf(a));
        System.out.println("Done");
        list.head = list.current;
    }

    public static boolean isValid(String email) { // Checks that ist a valid email
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();//returns true if input and regex matches otherwise false;

    }

    public static boolean isValidName(String s) { // Checks that its a valid Name e.g no numbers entered
        String regex = "[A-Za-z\\s]+";
        return s.matches(regex);//returns true if input and regex matches otherwise false;
    }

    public void phonenum() { // Makes sure only nums are entered for phone num
        if (Tel.getText().matches("\\d{8}|\\d{10}")) {
            System.out.println("Its Valid Number");
            //return true;
            pn = true;
        } else {
            System.out.println("Invalid Input..!");
            //return false;
            pn = false;
        }
        check2.setVisible(pn);
        check2.setSelected(pn);
    }

    public void name() {  // Method to make sure the name is valid e.g. it doesn't have any numbers in it
        Name.setOnKeyReleased(event -> {
            if (isValidName(Name.getText())) {
                n = true;
            } else {
                System.out.println("Invalid User Name");
                n = false;
            }
            check1.setVisible(n);
            check1.setSelected(n);
        });
    }

    public void userEmail(KeyEvent keyEvent) { // Makes sure the Email has not been entered before but must press enter to check
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (isValid(Email.getText())) {
                vC = true;
                System.out.println("Valid Email ");
            }
            if (!vC) {
                System.out.println("Invalid Email");
            }
            if (list.head != null) {
                while (list.head != null && !list.head.getContents().getEmail().equals(Email.getText())) {
                    list.head = list.head.next;
                }

                if (list.head != null && list.head.getContents().getEmail().equals(Email.getText())) {
                    System.out.println("This name is already in use");
                    list.head = list.current;
                    Email.setText(null);
                    vC = false;
                }
            }
            check3.setVisible(vC);
            check3.setSelected(vC);
            list.head = list.current;
        }
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
    public void selectBid() { // Gets the data on the selected Bidder
        while (BidderController.list.head != null && !BidderController.list.head.getContents().getEmail().equals(view.getSelectionModel().getSelectedItem())) {
            BidderController.list.head = BidderController.list.head.next;
        }
        if (BidderController.list.head != null) {
            Name.setText(BidderController.list.head.getContents().getName());
            Email.setText(BidderController.list.head.getContents().getEmail());
            Address.setText(BidderController.list.head.getContents().getAddress());
            Tel.setText(String.valueOf(BidderController.list.head.getContents().getTel()));
        }
        BidderController.list.head = BidderController.list.current;
    }
    public void update(){ // Updates the selected Bidder if any changes were made
        while (BidderController.list.head != null && !BidderController.list.head.getContents().getEmail().equals(view.getSelectionModel().getSelectedItem())) {
            BidderController.list.head = BidderController.list.head.next;
        }
        if (BidderController.list.head != null) {
            BidderController.list.head.getContents().setName(Name.getText());
            BidderController.list.head.getContents().setEmail(Email.getText());
            BidderController.list.head.getContents().setAddress(Address.getText());
            BidderController.list.head.getContents().setTel(Integer.parseInt(Tel.getText()));
            try {
                save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BidderController.list.head = BidderController.list.current;
    }
    public static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Bidder.xml"));
        list.head = (LinkedList<Bidder>.node<Bidder>) is.readObject();
        is.close();
    }

    public static void save() throws Exception {
        XStream xStream = new XStream(new DomDriver());
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("Bidder.xml"));
        out.writeObject(list.head);
        out.close();
    }
}
