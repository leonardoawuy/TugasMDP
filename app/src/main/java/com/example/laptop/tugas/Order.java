package com.example.laptop.tugas;

import java.util.ArrayList;

class Order {
    String type;
    ArrayList<String> toping = new ArrayList<>();
    int qty,total;

    public Order(String type, ArrayList<String> toping, int qty, int total) {
        this.type = type;
        this.toping = toping;
        this.qty = qty;
        this.total = total;
    }

    public int getTotal() { return total; }

    public void setTotal(int total) { this.total = total; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public ArrayList<String> getToping() { return toping; }

    public void setToping(ArrayList<String> toping) { this.toping = toping; }

    public int getQty() { return qty; }

    public void setQty(int qty) { this.qty = qty; }
}
