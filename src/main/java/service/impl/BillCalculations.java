package service.impl;

import models.Bill;
import models.DiscountCard;
import models.Product;

import java.util.ArrayList;

public class BillCalculations {


    public static float totalPricePerProduct(Product product) {
        if (product.isDiscount() && product.getQuantity() >= 5) {
            float total = product.getPrice() * product.getQuantity();
            return total - total * 10 / 100;
        } else return product.getPrice() * product.getQuantity();
    }


    public static float totalWithOutDiscount(Bill bill) {
        float total = 0;
        ArrayList<Product> products = bill.getProducts();
        for (Product p : products) {
            total += totalPricePerProduct(p);
        }
        return total;
    }

    public static float totalWithDiscount(Bill bill) {
        float total = totalWithOutDiscount(bill);
        DiscountCard card = bill.getDiscountCard();
        return total - (total * (card == null ? 0 : card.getDiscount()) / 100);
    }
}
