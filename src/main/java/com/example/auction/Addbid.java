package com.example.auction;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Addbid {
    private String Name;
    private  int Amount;
    private String url;
    private String title;
    private int highestBid = 0;

    @Override
    public String toString(){
        //return "User Email  - " + BidderController.list.head.getContents().getEmail() + " User Bid - " + BidderController.list.head.getContents().list.head.getContents().getAmount() + " Auction Lot - " + BidderController.list.head.getContents().list.head.getContents().getTitle();
        return Name;
    }

    public Addbid(int amount, String url, String title, String Name) {
        this.Amount = amount;
        this.url = url;
        this.title = title;
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public String string(){
        return "Bid " +
                "Name = '" + Name + '\'' +
                ", Amount = " + Amount +
                ", title = '" + title + '\'' +
                ", highestBid = " + highestBid;
    }


}
