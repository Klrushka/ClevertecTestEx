package service.impl;

import service.interfaces.DataValidator;

import java.io.FileWriter;

public class DataValidatorImp implements DataValidator {

    @Override
    public void addToInvalidData(String string) {
        // When merge with gradle need to change path!
        try (FileWriter fileWriter = new FileWriter("InvalidData.txt")){
            fileWriter.write(string + "\n");
        } catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public boolean isIdValid(String string) {
        String regex = "^\\d{1,2}|100";
        if (string.matches(regex)) {
            return true;
        } else {
            addToInvalidData(string);
            return false;
        }
    }

    @Override
    public boolean isNameValid(String string) {
        String regex = "^([A-Z]|[\u0410-\u042f])([a-z]|[\u0430-\u044f]){2,30}";
        if (string.matches(regex)) {
            return true;
        } else {
            addToInvalidData(string);
            return false;
        }
    }

    @Override
    public boolean isPriceValid(String string) {
        String regex = "^(\\d{1,2}|100)\\.\\d{1,2}";
        if (string.matches(regex)) {
            return true;
        } else {
            addToInvalidData(string);
            return false;
        }
    }

    @Override
    public boolean isQuantityValid(String string) {
        String regex = "(1\\d)|(\\d)|20";
        if (string.matches(regex)) {
            return true;
        } else {
            addToInvalidData(string);
            return false;
        }

    }
}
