package com.example.auction;

public class Bidder {
    private String Name;
    private  String Address;
    private  int tel;
    private  String email;

     LinkedList<Addbid> list = new LinkedList<>();
    public Bidder(String name, String address, int tel, String email) {
        Name = name;
        Address = address;
        this.tel = tel;
        this.email = email;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addBid(Addbid bid){
        list.addElement(bid);
    }
    public void delBid(String Name) { // Dels booths
        boolean found = false;
        if (list.head.getContents().getName().equals(Name)){
            list.delVA(list.head);
        }
        else {
            while (!found) {
                if (list.head.next.getContents().toString().equals(Name)) {
                    found = true;
                    ///list.head.next = list.head.next.next;
                    list.delVA(list.head.next);
                }
                list.head = list.head.next;
            }
        }
    }

    /*public  String printVA(){
        StringBuilder p = new StringBuilder();
        while(list.head != null){
            p.append(list.head.getContents().toString()).append("\n");
            list.head = list.head.next;
        }
        list.head = list.current;
        return p.toString();
    }

    public String printVR(){
        return "Bidder Name - " + BidderController.list.head.getContents().getName() + " Bid details - " + printVA();
    }*/

    @Override
    public String toString() {
        return "Bidder " +
                " Name = '" + Name + '\'' +
                ", Address = '" + Address + '\'' +
                ", tel = " + tel +
                ", email = '" + email;
    }
}
