package com.example.auction;


import java.util.Iterator;

public class LinkedList<F> {

    public node<F> head = null;
    public node<F> current = null;

    class node<N> {
        public node next = null;
        private N contents;

        public N getContents() {
            return contents;
        }

        public void setContents(N e) {
            contents = e;
        }
    }

    public void addElement(F e) { //Add element to head of list
        node<F> fn = new node<>();
        fn.setContents(e);
        fn.next = head;
        head = fn;
        current = fn;
        System.out.println(head);
    }

    public void clear() { //Empty list
        head = null;
    }

   /* public void delElement(F e, t){
        node<F> b = head;
        head = b.next;
    }*/

    public void delVA(LinkedList<F>.node<F> e) { // Dels vaccination appointments
        //VAppointmentNode b = headi;
        //boolean found = false;
        if (e == current) {
            head = head.next;
            current = head;

        } else {
            current.next = current.next.next;
            head.next = head.next.next;
        }
    }
}