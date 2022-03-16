package ru.clevertec;

import ru.clevertec.data.Data;
import ru.clevertec.models.Bill;
import ru.clevertec.models.Product;
import ru.clevertec.service.impl.BillCreatorImpl;
import ru.clevertec.service.impl.BillToPdfImpl;
import ru.clevertec.service.impl.InputDataHandlerImpl;
import ru.clevertec.service.interfaces.BillCreator;
import ru.clevertec.service.interfaces.BillToPdf;
import ru.clevertec.service.interfaces.InputDataHandler;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Invalid arguments");
        } else {

            InputDataHandler dataHandler = new InputDataHandlerImpl();

            Data.init(dataHandler.getFile(args));

            BillToPdf billToPdf = new BillToPdfImpl();

            ArrayList<Product> products = dataHandler.getProducts(args);

            BillCreator billCreator = new BillCreatorImpl();

            Bill bill = new Bill(dataHandler.getCard(args), products);

            billCreator.printBill(bill);

            billToPdf.createPdf(bill);
        }
    }
}
