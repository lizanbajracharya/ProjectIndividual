package com.example.projectindividual.model;

public class Product {
    private String _id;
    private String ItemName;
    private String ItemImage;
    private String ItemDescription;
    private String Rate;
    private String Category;
    private String Quantity;

    public Product(String id, String itemName, String itemImage, String itemDescription, String rate, String quantity, String category) {
        Category = category;
        _id = id;
        ItemName = itemName;
        ItemImage = itemImage;
        ItemDescription = itemDescription;
        Rate = rate;
        Quantity = quantity;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemImage() {
        return ItemImage;
    }

    public void setItemImage(String itemImage) {
        ItemImage = itemImage;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}