package com.example.auction;



public class AddAuctionLot {
    private String title;
    private  String Description;
    private  String type;
    private  String year;
    private  int Price;
    private String url;

    public AddAuctionLot(String title, String description, String type, int price, String url,String year) {
        this.title = title;
        this.Description = description;
        this.type = type;
        this.year = year;
        this.Price = price;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return " Unsold Lot " +
                "title = '" + title + '\'' +
                ", Description ='" + Description + '\'' +
                ", type = '" + type + '\'' +
                ", year = '" + year + '\'' +
                ", Price = " + Price;
    }
}
