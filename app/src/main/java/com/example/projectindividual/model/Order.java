package com.example.projectindividual.model;

public class Order {
    private String Product;
    private String Rate;
    private String Location;
    private String ContactInfo;

    public Order(String product, String rate, String location, String contactInfo) {
        Product = product;
        Rate = rate;
        Location = location;
        ContactInfo = contactInfo;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getContactInfo() {
        return ContactInfo;
    }

    public void setContactInfo(String contactInfo) {
        ContactInfo = contactInfo;
    }
}
