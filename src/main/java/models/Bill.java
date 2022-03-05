package models;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class Bill {

    static {
        initCommonInfo();
    }

    private static Long id = 0L;
    private final Date billDate;
    private DiscountCard discountCard;
    private ArrayList<Product> products;
    private static String shopName;
    private static String tel;
    private static String address;

    public Bill(DiscountCard discountCar, ArrayList<Product> products) {
        id++;
        this.billDate = new Date();
        this.discountCard = discountCar;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public Date getBillDate() {
        return billDate;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCars(DiscountCard discountCars) {
        this.discountCard = discountCars;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        Bill.shopName = shopName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        Bill.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        Bill.address = address;
    }

    private static void initCommonInfo() {
        Properties properties = new Properties();


        try {
            FileReader reader = new FileReader("src/main/resources/config.properties");
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        shopName = properties.getProperty("shopName");
        tel = properties.getProperty("tel");
        address = properties.getProperty("address");

    }

}

