package com.example.auction;

public class SoldAuctionLot {
    private String title;
    private String type;
    private String email;
    private  String Description;
    private  String year;
    private  int Price;
    private String url;

    public SoldAuctionLot(String email, String title, String type,  String description, String year, int price, String url) {
        this.email = email;
        this.title = title;
        this.type = type;
        this.Description = description;
        this.year = year;
        this.Price = price;
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setTitle(String title) {
        this.email = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Sold Lot" +
                "title = '" + title + '\'' +
                ", type = '" + type + '\'' +
                ", email = '" + email + '\'' +
                ", Description = '" + Description + '\'' +
                ", year = '" + year + '\'' +
                ", Price = " + Price;
    }
}
