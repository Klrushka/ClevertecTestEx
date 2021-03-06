package service.interfaces;

import models.DiscountCard;
import models.Product;

import java.util.ArrayList;

public interface InputDataHandler {
    ArrayList<Product> getProducts(String[] args);

    boolean isCard(String str);

    String[] getFile(String[] args);

    boolean isFile(String str);

    DiscountCard getCard(String[] args);
}
