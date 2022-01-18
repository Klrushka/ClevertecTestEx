package Models;

import data.Products;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;


    public Product(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
        Products product = Products.getProductById(id);
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
