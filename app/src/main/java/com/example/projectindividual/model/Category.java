package com.example.projectindividual.model;

public class Category {
    private String Image;
    private String CategoryName, _id;


    public Category(String Image, String CategoryName, String _id) {
        this.Image = Image;
        this.CategoryName = CategoryName;
        this._id = _id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName = categoryName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
