package com.example.entregable1;

public class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void increaseStock() {

        stock++;
    }

    public void decreaseStock() {
        if (stock > 0) {
            stock--;
        }
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }

    public String decreaseStock(int quantity) {
        if (stock >= quantity) {
            stock -= quantity;
            return "Disminución exitosa";
        } else {
            return "Stock Insuficionte ";
        }
    }
}
