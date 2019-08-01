package com.example.khangit.project_group3.model;

public class ModelProduct {
    public int Id;
    public String nameProduct;
    public String imageProduct;

    public ModelProduct(int id, String nameProduct, String imageProduct) {
        Id = id;
        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }
}
