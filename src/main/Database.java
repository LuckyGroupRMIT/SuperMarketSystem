package main;

import java.util.*;

public class Database
{
    private static HashMap<String, ProductType> ProductHash = new HashMap<>();
    private static HashMap<String, StaffAccount> AccountHash = new HashMap<>();
    private static HashMap<String, Discount> DiscountHash = new HashMap<>();

    public static boolean addObject(String key, Object value)
    {
        if(value instanceof ProductType) {
            ProductHash.put(key, ((ProductType) value));
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

        if(type == ProductType.class)
            object = ProductHash.get(key);
        else if(type == StaffAccount.class)
            object = AccountHash.get(key);
        else if(type == Discount.class)
            object = DiscountHash.get(key);

        return object;
    }

    public static boolean removeObject(String key, Class type)
    {
        if(type == ProductType.class)
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

    public static ArrayList<ProductType> listAllProducts()
    {
        Collection<ProductType> values = ProductHash.values();
        return new ArrayList<>(values);
    }

    public static ArrayList<StaffAccount> listAllAccounts()
    {
        Collection<StaffAccount> values = AccountHash.values();
        return new ArrayList<>(values);
    }
}
