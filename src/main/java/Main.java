import Models.Product;
import service.InputDataHandler;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Product> products = InputDataHandler.getProducts(args);

        products.forEach(System.out::println);
    }
}
