package ru.clevertec.service.impl;

import ru.clevertec.service.interfaces.DataValidator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class DataValidatorImp implements DataValidator {

    @Override
    public void addToInvalidData(String string) {

        Properties properties = new Properties();

        try (FileReader reader = new FileReader("src/main/resources/config.properties")){
            properties.load(reader);
            try( FileWriter fileWriter = new FileWriter(properties.getProperty("invalidData"),true)){
                fileWriter.write(string + "\n");
            }
        } catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public boolean isIdValid(String string) {
        String regex = "^([1-9]{1,2})|100";
        if (string.matches(regex)) {
            return true;
        } else {
            addToInvalidData("id: " +  string);
            return false;
        }
    }

    @Override
    public boolean isNameValid(String string) {
        String regex = "^([A-Z]|[\u0410-\u042f])([a-z]|[\u0430-\u044f]){2,30}";
        if (string.matches(regex)) {
            return true;
        } else {
            addToInvalidData("name: " + string);
            return false;
        }
    }

    @Override
    public boolean isPriceValid(String string) {
        String regex = "^(\\d{1,2}|100)\\.\\d{2}";
        if (string.matches(regex)) {
            return true;
        } else {
            addToInvalidData("price: " + string);
            return false;
        }
    }

    @Override
    public boolean isQuantityValid(String string) {
        String regex = "(1\\d)|(\\d)|20";
        if (string.matches(regex)) {
            return true;
        } else {
            addToInvalidData("quantity: " + string);
            return false;
        }

    }

    @Override
    public boolean isValidProduct(String[] strings) {
        boolean id = isIdValid(strings[0]);
        boolean name = isNameValid(strings[1]);
        boolean price = isPriceValid(strings[2]);

        return (id && name && price);
    }
}
