package main;

import java.util.ArrayList;

public class ProductReport
{
    private static ArrayList<Sale> sales = new ArrayList<>();

    public static void generateSupplyReport()
    {
        System.out.println("Generating Supply Report");
    }

    public static void generateSalesReport(String startDate, String endDate)
    {
        System.out.println("Generating Sales Report");
    }

    public static void getBestSellers()
    {
        System.out.println("Getting Products by Revenue");
    }
}
