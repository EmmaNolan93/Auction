package com.example.auction;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class AddAuctionLotController{
    Stage stage;
    Parent root;
    String e;
    Boolean n = false;
    Boolean p = false;
    private String a;
    @FXML
    private javafx.scene.image.ImageView img;
    @FXML
    private Button b;
    @FXML
    private Button r;
    @FXML
    private TextField title;
    @FXML
    private  TextField Description;
    @FXML
    private TextField Price, Date;
    @FXML
    private ChoiceBox Type;
    @FXML
    private CheckBox box1, box3;
    @FXML
    private ListView<String> view;

    public void initialize() {
        box1.setVisible(false);
        box3.setVisible(false);
        Type.getItems().addAll("ceramics", "Furniture");
        table();
    }

    static LinkedList<AddAuctionLot> list = new LinkedList<>();
    public void submits(){ // Makes sure everything is filled out before the data is added to the linked list.
        int empty = String.valueOf(title.getText()).equals("")? 0:
                String.valueOf(Description.getText()).equals("")? 0:
                        //String.valueOf(Type.getValue()).equals("null")? 0:
                        String.valueOf(Date.getText()).equals("")? 0:
                                String.valueOf(img.getImage()).equals("null")? 0:
                                        String.valueOf(Price.getText()).equals("")? 0:1;
        if(empty == 1 && p && n ) {
            AddAuctionLot auctionLotController = new AddAuctionLot(title.getText(), Description.getText(), String.valueOf(Type.getValue()), Integer.parseInt(Price.getText()),  a, String.valueOf(Date.getText()));
            list.addElement(auctionLotController);// Adds the Auction lot to the aduction lot linked list;
            table();//Restarts the table to show the added object
            p = false;// Resets the Boolean Value
            n = false;
            try {
                save();//Saves the new added object to a file
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Everything must be filled in");
        }
    }
    public void start(javafx.event.ActionEvent e) throws Exception
    {
        if(e.getSource() == b)
        {
            FileChooser file = new FileChooser();
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            file.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);// Makes sure that onlu JPG nad PNG files are choosen e.g only images
            File file1 = file.showSaveDialog(b.getScene().getWindow());
            System.out.println(file1);
            Image image1 = new Image(file1.toURI().toString());
            a = file1.toURI().toString();
            img.setImage(image1);//Shows the selected Image

        }

    }
    public static boolean isValidName(String s){
        String regex="[A-Za-z\\s]+";
        return s.matches(regex);//returns true if input and regex matches otherwise false;
    }
    @FXML
    public void name(){  // Method to make sure the name is valid e.g. it doesn't have any numbers in it
        title.setOnKeyReleased(event -> {
            if( isValidName(title.getText() ) ){
                System.out.println("Valid Name");
                n = true;
            }else{
                System.out.println("Invalid User Name");
            }
            box1.setVisible(n);
            box1.setSelected(n);
        });
    }
    public void priceNum() { // Makes sure only nums are entered for phone num
        Price.setOnKeyReleased(event -> {
            if (Price.getText().matches("\\d+")) {
                System.out.println("Valid Input");
                p = true;
            } else {
                System.out.println("Invalid Input..!");
                p = false;
            }
            box3.setVisible(p);
            box3.setSelected(p);

        });
    }
    public void table() { // gets all Added Auction lots
        if (list.head == null) {
            System.out.println("No are no Auctions added to the system");
        }
        if (view.getItems() != null) {
            view.getItems().clear();
        }
        while (list.head != null) {
            Objects.requireNonNull(view.getItems()).add(list.head.getContents().getTitle());
            list.head = list.head.next;
        }
        System.out.println("Done");
        list.head = list.current;
    }

    public void selectLot() { // Gets the data on the selected Auction lot form the table in order to be updated
        while (list.head != null && !list.head.getContents().getTitle().equals(view.getSelectionModel().getSelectedItem())) {
            list.head = list.head.next;
        }
        if (list.head != null) {
            title.setText(list.head.getContents().getTitle());
            Description.setText(list.head.getContents().getDescription());
            Price.setText(String.valueOf(list.head.getContents().getPrice()));
            Date.setText(String.valueOf(list.head.getContents().getYear()));
            Type.setValue(String.valueOf(list.head.getContents().getType()));
            e =  list.head.getContents().getUrl();
            Image image1 = new Image(e);
            img.setImage(image1);
        }
        list.head = list.current;
    }
    public void goBacks(ActionEvent e) throws  Exception{
        if(e.getSource()== r){
            stage = (Stage) r.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void update(){ // Updates any changes to the Auction lot
        while (list.head != null && !list.head.getContents().getTitle().equals(view.getSelectionModel().getSelectedItem())) {
            list.head = list.head.next;
        }
        if (list.head != null) {
            list.head.getContents().setTitle(title.getText());
            list.head.getContents().setYear(Date.getText());
            list.head.getContents().setDescription(Description.getText());
            list.head.getContents().setPrice(Integer.parseInt(Price.getText()));
            e = img.getImage().getUrl();
            list.head.getContents().setUrl(e);
            list.head.getContents().setType(String.valueOf(Type.getValue()));
            try {
                save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        list.head = list.current;
    }
    public static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY);
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("AuctionLot.xml"));
        list.head = (LinkedList<AddAuctionLot>.node<AddAuctionLot>) is.readObject();
        is.close();
    }

    public static void save() throws Exception {
        XStream xStream = new XStream(new DomDriver());
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("AuctionLot.xml"));
        out.writeObject(list.head);
        out.close();
    }
}

