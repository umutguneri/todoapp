package com.example.umutguneri.todoapptestapp.Model;

public class Product {
    private String title;
    private String description;
    private String Price;

    public Product(){

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Override
    public String toString() {
        return this.title + "\n" + this.description + "\n" + "Price:" + this.Price ;
    }
}
