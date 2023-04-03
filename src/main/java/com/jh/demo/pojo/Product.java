package com.jh.demo.pojo;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String pname;
    private BigDecimal price;
    private int nums;

    public Product() {
    }

    public Product(int id, String pname, BigDecimal price, int nums) {
        this.id = id;
        this.pname = pname;
        this.price = price;
        this.nums = nums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }
}
