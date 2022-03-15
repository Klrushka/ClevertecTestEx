package ru.clevertec.data;


import com.opencsv.CSVReader;
import ru.clevertec.models.DiscountCard;
import ru.clevertec.models.Product;
import ru.clevertec.service.impl.DataValidatorImp;
import ru.clevertec.service.interfaces.DataValidator;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;


public class Data {
    public static final LinkedList<Product> PRODUCTS = new LinkedList<>();
    public static final LinkedList<DiscountCard> CARDS = new LinkedList<>();

    public static Product getProduct(int id) {
        return PRODUCTS.get(id);
    }

    public static DiscountCard getCard(int id) {
        return CARDS.get(id);
    }

    public static void initFromCode() {
        PRODUCTS.add(new Product(0, "Donec", 12.3f, false));
        PRODUCTS.add(new Product(1, "Diam", 1.5f, true));
        PRODUCTS.add(new Product(2, "Cursus", 2.3f, true));
        CARDS.add(new DiscountCard(0, 1f));
        CARDS.add(new DiscountCard(1, 2f));
        CARDS.add(new DiscountCard(2, 3f));
    }

    public static void initFromFile(String[] str) {

        boolean exitFlag = false;

        try {
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/" + str[0]));

            DataValidator validator = new DataValidatorImp();

            List<String[]> rawData = reader.readAll();

            for (String[] fields : rawData) {
                if (validator.isValidProduct(fields)) {
                    PRODUCTS.add(new Product(Integer.parseInt(fields[0]), fields[1], Float.parseFloat(fields[2]),
                            Boolean.parseBoolean(fields[3])));
                } else {
                    exitFlag = true;
                }

            }

            reader.close();

            reader = new CSVReader(new FileReader("src/main/resources/" + str[1]));

            rawData = reader.readAll();

            for (String[] fields : rawData) {

                CARDS.add(new DiscountCard(Integer.parseInt(fields[0]), Integer.parseInt(fields[1])));

            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exitFlag) {
            System.out.println("Invalid data check \"InvalidData.txt\" file");
            System.exit(0);
        }

    }

    public static void init(String[] str) {
        if (str != null) {
            initFromFile(str);
        } else {
            initFromCode();
        }
    }


}
