package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager
{
    public static boolean displayManagerMenu()
    {
        boolean exitProgram = false;
        Scanner reader = new Scanner(System.in);

        System.out.println("\nManager Menu\n");
        while (!exitProgram)
        {
            System.out.println("1. Change Product Details");
            System.out.println("2. Generate Sales Report");
            System.out.println("3. Generate Supply Report");
            System.out.println("4. View products by revenue");
            System.out.println("5. View all staff members");
            System.out.println("6. Return to initial menu");
            System.out.println("7. Exit Program");
            System.out.println("0. Admin Options");
            System.out.print("\n\nEnter a number to select option: ");

            int input = reader.nextInt(); // Scans the next token of the input as an int.

            switch (input)
            {
                case 1:
                    displayProductMenu();
                    break;
                case 2:
                    displaySalesReportMenu();
                    break;
                case 3:
                    try {
                        ProductReport.generateSupplyReport();
                    } catch (Exception e) {
                        System.out.println("Failed!");
                    }
                    break;
                case 4:
//                    ProductReport.getBestSellers();
                    break;
                case 5:
                    displayAllStaffMenu();
                    break;
                case 6:
                    exitProgram = true;
                    break;
                case 7:
                    return true;
                case 0:
                    Admin.displayAdminOptions();
                    break;
                default:
                    System.out.println("Error: invalid option");
                    break;
            }
        }

        return false;
    }

    private static void displayProductMenu()
    {
        boolean exitLoop = false;
        Scanner reader = new Scanner(System.in);

        while (!exitLoop)
        {
            System.out.println("1. Change Product Price");
            System.out.println("2. Change Product Discounts");
            System.out.println("3. Change Product Supplier");
            System.out.println("4. Return to previous menu");
            System.out.print("\n\nEnter a number to select option: ");

            int input = reader.nextInt(); // Scans the next token of the input as an int.

            switch (input)
            {
                case 1:
                    displayChangePriceMenu();
                    break;
                case 2:
                    displayChangeDiscountsMenu();
                    break;
                case 3:
                    displayChangeSupplierMenu();
                    break;
                case 4:
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Error: invalid option");
            }
        }
    }

    private static void displaySalesReportMenu()
    {
        Scanner reader = new Scanner(System.in);
        String startDate, endDate;
        boolean exitloop = false;

        System.out.println(" This will generate a report of all sales within (and including) the dates specified." +
                "\n Please enter all dates in the format DD/MM/YYYY\n\n");
        while(!exitloop)
        {
            System.out.print("Please enter starting date, or enter empty line to cancel: ");
            startDate = reader.nextLine();
            if(startDate.isEmpty())
                break;
            if(!startDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}"))
            {
                System.out.println("Error: invalid date format, please try again.");
                continue;
            }

            System.out.print("Please enter ending date, or enter empty line to cancel: ");
            endDate = reader.nextLine();
            if (endDate.isEmpty())
                break;
            if(!endDate.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}"))
            {
                System.out.println("Error: invalid date format, please try again.");
                continue;
            }

//            ProductReport.generateSalesReport(startDate, endDate);
            exitloop = true;
        }
    }

    private static void displayChangePriceMenu()
    {
        boolean exitLoop = false;
        Scanner reader = new Scanner(System.in);

        while (!exitLoop)
        {
            System.out.print("Please enter id of product you wish to change the price of, or enter empty line to cancel: ");
            String tempID = reader.nextLine();
            if(tempID.isEmpty())
                break;
            ProductType temp = (ProductType) Database.getByID(tempID, ProductType.class);
            if(temp == null)
            {
                System.out.println("Error: id does not match known product");
                continue;
            }

            if(temp.getBasePrice(PricingMethod.QUANTITY, 1) == null)
                System.out.println("Price for that product is currently not set.");
            else
                System.out.println("Current price for that item is " + temp.getBasePrice(PricingMethod.QUANTITY, 1));
            System.out.print("Please enter the new price for the product, or enter empty line to cancel: ");
            String tempPrice = reader.nextLine();
            if(tempPrice.isEmpty())
                break;

            double newPrice = Double.parseDouble(tempPrice);
            temp.setBasePrice(PricingMethod.QUANTITY, newPrice);
            System.out.println("New price for item " + temp.getProductID() + " is " + temp.getBasePrice(PricingMethod.QUANTITY, 1));
            if(Database.addObject(temp.getProductID(), temp))
                exitLoop = true;
            else
                System.out.println("Error: failed to update object");
        }
    }

    private static void displayChangeDiscountsMenu()
    {

    }

    private static void displayChangeSupplierMenu()
    {
        boolean exitLoop = false;
        Scanner reader = new Scanner(System.in);

        while (!exitLoop)
        {
            System.out.print("Please enter id of product you wish to change the supplier of, or enter empty line to cancel: ");
            String tempID = reader.nextLine();
            if(tempID.isEmpty())
                break;
            ProductType temp = (ProductType) Database.getByID(tempID, ProductType.class);
            if(temp == null)
            {
                System.out.println("Error: id does not match known product");
                continue;
            }

            System.out.println("Current supplier for that item is " + temp.getSupplier());
            System.out.print("Please enter the new supplier name for the product, or enter empty line to cancel: ");
            String tempSupplier = reader.nextLine();
            if(tempSupplier.isEmpty())
                break;

            temp.setSupplier(tempSupplier);
            System.out.println("New supplier for item " + temp.getProductID() + " is " + temp.getSupplier());
            if(Database.addObject(temp.getProductID(), temp))
                exitLoop = true;
            else
                System.out.println("Error: failed to update object");
        }
    }

    private static void displayAllStaffMenu()
    {
        ArrayList<StaffAccount> staffAccounts = Database.listAllAccounts();
        Scanner reader = new Scanner(System.in);

        System.out.println("Staff ID\t|\tStaff Position");
        System.out.println("--------------------------------");
        for(StaffAccount staffAccount : staffAccounts)
        {
            System.out.print(staffAccount.getStaffID() + "\t\t|\t");
            switch (staffAccount.getPermissions())
            {
                case MANAGER:
                    System.out.print("Manager\n");
                    break;
                case WAREHOUSE:
                    System.out.print("Warehouse Member\n");
                    break;
                case SALES:
                    System.out.print("Sales Member\n");
                    break;
                case EMPTY:
                    System.out.println("Null\n");
                    break;
            }
        }

        reader.nextLine();
    }
}
