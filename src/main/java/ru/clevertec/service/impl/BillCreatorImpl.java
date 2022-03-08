package ru.clevertec.service.impl;

import ru.clevertec.models.Bill;
import ru.clevertec.models.Product;
import ru.clevertec.service.interfaces.BillCreator;

import static ru.clevertec.service.impl.BillCalculations.*;

public class BillCreatorImpl implements BillCreator {


    @Override
    public void printBill(Bill bill) {
        System.out.println(topBill(bill));
        System.out.println(bodyBill(bill));
        System.out.println(botBill(bill));
    }

    private static String topBill(Bill bill) {

        StringBuilder builder = new StringBuilder();

        builder
                .append(String.format(" %-10s%s\n", "", "CASH RECEIPT"))
                .append(String.format(" %-10s%s\n", "", bill.getShopName()))
                .append(String.format(" %-10s%s\n", "", bill.getAddress()))
                .append(String.format(" %-10sTel: %s\n", "", bill.getTel()))
                .append(String.format(" CASHIER: #%-10d DATE: %tD\n", bill.getId(), bill.getBillDate()))
                .append(String.format(" %-20s TIME: %tT\n", "", bill.getBillDate()))
                .append(String.format(" %s\n", "-----------------------------------------------"));
        return builder.toString();

    }

    private static String botBill(Bill bill) {

        StringBuilder builder = new StringBuilder();

        builder
                .append(String.format(" %s\n", "-----------------------------------------------"))
                .append(String.format(" %-35s $%.2f\n", "TOTAL", totalWithOutDiscount(bill)))
                .append(String.format(" %-35s $%.2f\n", "TO PAY", totalWithDiscount(bill)));

        return builder.toString();

    }

    private static String bodyBill(Bill bill) {

        StringBuilder builder = new StringBuilder();

        builder.append(String.format(" %6s %15s %8s %10s\n", "QTY", "DESCRIPTION", "PRICE", "TOTAL"));

        for (Product p : bill.getProducts()) {

            builder.append(String.format(" %6s %-3s %-14s %-10s $%.2f\n", p.getQuantity(), "", p.getName(), "$" + p.getPrice(),
                    totalPricePerProduct(p)));

            if (p.isDiscount() && p.getQuantity() >= 5) {

                builder.append(String.format(" %-36s $%.2f\n", "discount: ", p.getPrice() * p.getQuantity() * 10 / 100));

            }
        }

        return builder.toString();
    }

}
