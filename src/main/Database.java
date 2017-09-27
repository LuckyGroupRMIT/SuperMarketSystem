package main;

import java.io.*;
import java.util.*;

public class Database
{
    private static HashMap<String, ProductType> ProductHash = new HashMap<>();
    private static HashMap<String, StaffAccount> AccountHash = new HashMap<>();
    private static HashMap<String, Discount> DiscountHash = new HashMap<>();

    public static void saveAllMaps() throws IOException
    {
        ObjectOutputStream prodOut = new ObjectOutputStream(new FileOutputStream("product.dat"));
        ObjectOutputStream accOut = new ObjectOutputStream(new FileOutputStream("account.dat"));
//        ObjectOutputStream discOut = new ObjectOutputStream(new FileOutputStream("discount.dat"));

        prodOut.writeObject(ProductHash);
        accOut.writeObject(AccountHash);
//        discOut.writeObject(DiscountHash);

        prodOut.close();
        accOut.close();
//        discOut.close();

    }

    public static boolean initAllMaps() throws IOException
    {
        if(new File("product.dat").exists())
        {
            ObjectInputStream prodIn = new ObjectInputStream(new FileInputStream("product.dat"));
            try {
                Object prodRead = prodIn.readObject();
                if(prodRead instanceof HashMap)
                    ProductHash = (HashMap<String, ProductType>) prodRead;
            }catch (ClassNotFoundException cls){
                return false;
            }
        }

        if(new File("account.dat").exists())
        {
            ObjectInputStream accIn = new ObjectInputStream(new FileInputStream("account.dat"));
            try {
                Object accRead = accIn.readObject();
                if(accRead instanceof HashMap)
                    AccountHash = (HashMap<String, StaffAccount>) accRead;
            }catch (ClassNotFoundException cls){
                return false;
            }
        }

        if(new File("discount.dat").exists())
        {
            ObjectInputStream discIn = new ObjectInputStream(new FileInputStream("discount.dat"));
            try {
                Object discRead = discIn.readObject();
            if(discRead instanceof HashMap)
                DiscountHash = (HashMap<String, Discount>) discRead;
            }catch (ClassNotFoundException cls){
                return false;
            }
        }

        return true;
    }

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
        if(type.equals(ProductType.class))
            if (ProductHash.remove(key) != null)
                return true;
        if(type.equals(StaffAccount.class))
            if (AccountHash.remove(key) != null)
                return true;
        if(type.equals(Discount.class))
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

    public static void clearHashMap(Class type)
    {
        if(type.equals(ProductType.class))
            ProductHash.clear();
        if(type.equals(StaffAccount.class))
            AccountHash.clear();
        if(type.equals(Discount.class))
            DiscountHash.clear();
    }

    public static HashMap<String, StaffAccount> getAccountHash() {
        return AccountHash;
    }
}
