package ru.eroonda.shoppinglist.entity;

public class ShoppingList {
    private int id;
    private String name;
    private int count;
    private double price;
    private String sessionId;
    private boolean isChanging;

    public ShoppingList() {
    }

    public ShoppingList(int id, String name, int count, double price, String sessionId, boolean isChanging) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.sessionId = sessionId;
        this.isChanging = isChanging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChanging() {
        return isChanging;
    }

    public void setChanging(boolean changing) {
        isChanging = changing;
    }

}
