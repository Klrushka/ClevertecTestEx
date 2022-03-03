import data.Data;
import models.Bill;
import models.Product;
import service.impl.BillCreatorImpl;
import service.impl.BillToPdfImpl;
import service.impl.InputDataHandlerImpl;
import service.interfaces.BillCreator;
import service.interfaces.BillToPdf;
import service.interfaces.InputDataHandler;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

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
