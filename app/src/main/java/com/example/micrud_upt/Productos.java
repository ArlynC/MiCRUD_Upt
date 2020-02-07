package com.example.micrud_upt;

public class Productos {
    private String name;
    private int price;
    private String _id;
    public Productos(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public Productos(String name, int price, String _id) {
        this.name = name;
        this.price = price;
        this._id=_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return _id;
    }
    public void setId(String _id) {
        this._id = _id;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
