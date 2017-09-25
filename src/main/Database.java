package main;

import java.util.*;

public class Database
{
    private static HashMap<String, Product> ProductHash = new HashMap<>();
    private static HashMap<String, StaffAccount> AccountHash = new HashMap<>();
    private static HashMap<String, Discount> DiscountHash = new HashMap<>();

    public static boolean addObject(String key, Object value)
    {
        if(value instanceof Product) {
            ProductHash.put(key, ((Product) value));
            return true;
        }

        else if(value instanceof StaffAccount) {
            AccountHash.put(key, ((StaffAccount) value));
            return true;
        }

        else if(value instanceof Discount) {
            DiscountHash.put(key, ((Discount) value));
            return true;
        }

        return false;
    }

    public static Object getByID(String key, Class<?> type)
    {
        Object object = null;

        if(type == Product.class)
            object = ProductHash.get(key);
        else if(type == StaffAccount.class)
            object = AccountHash.get(key);
        else if(type == Discount.class)
            object = DiscountHash.get(key);

        return object;
    }

    public static boolean removeObject(String key, Class type)
    {
        if(type == Product.class)
            if (ProductHash.remove(key) != null)
                return true;
        else if(type == StaffAccount.class)
            if (AccountHash.remove(key) != null)
                return true;

        else if(type == Discount.class)
            if (DiscountHash.remove(key) != null)
                return true;

        return false;
    }

    public static ArrayList<Product> listAllProducts()
    {
        ArrayList<Product> products = null;

        Collection<Product> values = ProductHash.values();
        products = new ArrayList<>(values);

        return products;
    }

    public static ArrayList<StaffAccount> listAllAccounts()
    {
        ArrayList<StaffAccount> products = null;

        Collection<StaffAccount> values = AccountHash.values();
        products = new ArrayList<>(values);

        return products;
    }
}
