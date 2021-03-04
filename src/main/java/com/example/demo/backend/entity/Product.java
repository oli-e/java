package com.example.demo.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Product extends AbstractEntity implements Cloneable {

    @NotNull
    @NotEmpty
    private String productName = "";

    @NotNull
    @NotEmpty
    private String productPrice = "";

    @NotNull
    private String productDescription = "";

    @NotNull
    @NotEmpty
    private String productImage = "";

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductPrice(String productPrice){
        this.productPrice = productPrice;
    }

    public String getProductPrice(){
        return productPrice;
    }

    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }

    public String getProductDescription(){
        return productDescription;
    }

    public void setProductImage(String productImage){
        this.productImage = productImage;
    }

    public String getProductImage(){
        return productImage;
    }

}