package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.*;
import java.util.*;

public class ProductReport
{

    public static void generateSupplyReport()  throws Exception
    {
        String text = getSupplyText();
        String fileName = "supplyreport";
        File newFile = new File("./" + fileName + ".png");
        Font font = new Font("Tahoma", Font.PLAIN, 12);

        FontRenderContext frc = new FontRenderContext(null, false, true);

        Rectangle2D bounds = font.getStringBounds(text, frc);

        int w = (int) bounds.getWidth();
        int h = text.split("\r\n|\r|\n").length * 24;

        BufferedImage image = new BufferedImage(w, h,   BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.BLACK);
        g.setFont(font);

        float x = (float) bounds.getX();
        float y = (float) -bounds.getY();

        for(String line: text.split("\n"))
            g.drawString(line, x, y+= g.getFontMetrics().getHeight());

        g.dispose();

        ImageIO.write(image, "png", newFile);
    }

    private static String getSupplyText()
    {
        ArrayList<ProductType> productList = Database.listAllProducts();
        String text = "";

        text = text.concat(new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + "\n\n" +
                "\tid\t\t|\tname\t|\tstock level\t\t|\trestock level\n" +
                "\t---------------------------------------------------------\n");
        for(ProductType product : productList)
        {
            String prodString = "\t" + product.getProductID()
                    + "\t|\t" + product.getName()
                    + "\t|\t" + product.getCurrentStock()
                    + "\t|\t" + product.getRestock() + "\n";
            text = text.concat(prodString);
        }

        return text;
    }

    public static double getTotalRevenue(String startDate, String endDate)
    {
        double totalRevenue = 0;

        try {
            ArrayList<Sale> sales = getSaleForDates(startDate, endDate);

            for (Sale sale:sales)
            {
                totalRevenue += sale.getTotal();
            }
        }catch (ParseException p) {
            p.printStackTrace();
        }

        return totalRevenue;
    }

    public static ArrayList<Sale> getSaleForDates(String startDate, String endDate) throws ParseException
    {
        ArrayList<Sale> sales = Database.listAllSales();
        ArrayList<Sale> dates = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date formStartDate = dateFormat.parse(startDate);
        Date formEndDate = dateFormat.parse(endDate);

        sales.sort(Comparator.comparing(Sale::getCurrentDate));

        for(Sale sale: sales)
        {
            if(sale.getCurrentDate().after(formStartDate) || sale.getCurrentDate().before(formEndDate))
                dates.add(sale);
        }

        return dates;
    }

    public static double getProductRevenue(ProductType product)
    {
        ArrayList<Sale> sales = Database.listAllSales();

        double totalRevenue = 0;

        sales.sort(Comparator.comparing(Sale::getCurrentDate));

        for (Sale sale:sales)
        {
            ArrayList<Purchase> purchases = sale.getPurchases();
            for (Purchase purchase: purchases)
            {
                if(purchase.getProduct().getName().equalsIgnoreCase(product.getName()))
                    totalRevenue += purchase.getUndiscountedPrice();
            }
        }

        return totalRevenue;
    }
}