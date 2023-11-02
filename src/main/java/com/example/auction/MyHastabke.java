package com.example.auction;

public class MyHastabke {
    int[] hashTable;

    public MyHastabke(int size){
        hashTable = new int [size];
    }

    public void add(String i) {
        int loc = hashFunction(i.length()), home = loc;
        while(hashTable[loc]!=0) {
            System.out.println("Location "+loc+" is full. Probing...");
            loc=(loc+1)%hashTable.length;
            if(loc==home) {
                System.out.println("Hashtable is full!");
                break; //Full!
            }
        }
        hashTable[loc]= i.length();
    }
    public int findPosition(String i) {
        int loc=hashFunction(i.length()),home=loc;

        while(hashTable[loc]!=i.length()) {
            System.out.println("Location "+loc+" does not contain "+ i +". Probing...");
            loc=(loc+1)%hashTable.length;
            if(loc==home) {
                System.out.println("Hashtable does not contain "+ i);
                return -1;
            }
        }
        return loc;
    }

    public int deletePosition(String i){
        int loc=hashFunction(i.length()),home=loc;

        while(hashTable[loc]!=i.length()) {
            System.out.println("Location "+loc+" does not contain "+i+". Probing...");
            loc=(loc+1)%hashTable.length;
            if(loc==home) {
                System.out.println("Position has been deleted "+i);
                return -1;
            }
        }
        i = "";
        hashTable[loc]= i.length();
        return loc;
    }

    public int hashFunction(int k) {
        return k%hashTable.length;
    }

    public void showHashTable() {
        System.out.println("Hash Table (Using Linear Probing)\n=====================");
        for(int i=0;i<hashTable.length;i++)
            System.out.println(i+". "+hashTable[i]);
        System.out.println();
    }

}
