package main;

import java.util.Scanner;

public class Admin
{
    public static void displayAdminOptions()
    {
        boolean exitLoop = false;
        Scanner reader = new Scanner(System.in);

        while (!exitLoop)
        {
            System.out.println("1. Add Product to system");
            System.out.println("2. Add Staff to system");
            System.out.println("3. Remove Product from system");
            System.out.println("4. Remove Staff from system");
            System.out.println("5. Return to Manager menu");
            System.out.print("\n\nEnter a number to select option: ");

            int input = reader.nextInt(); // Scans the next token of the input as an int.

            switch (input)
            {
                case 1:
                    displayAddProductMenu();
                    break;
                case 2:
                    displayAddStaffMenu();
                    break;
                case 3:
                    displayRemoveProductMenu();
                    break;
                case 4:
                    displayRemoveStaffMenu();
                    break;
                case 5:
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Error: invalid option");
            }
        }
    }

    private static void displayAddProductMenu()
    {
        boolean exitLoop = false;
        ProductType newProduct;
        Scanner reader = new Scanner(System.in);

        while (!exitLoop)
        {
            System.out.print("Please enter new product id, or enter empty line to cancel: ");
            String tempId = reader.nextLine();
            if(tempId.isEmpty())
                break;
            if(Database.getByID(tempId, ProductType.class) != null)
            {
                System.out.println("Error: Product ID already exists. try again");
                continue;
            }

            System.out.print("Please enter the name of the new product, or enter empty line to cancel: ");
            String tempName = reader.nextLine();
            if(tempName.isEmpty())
                break;

            System.out.print("Please enter the name of new product supplier, or enter empty line to cancel: ");
            String tempSupplier = reader.nextLine();
            if(tempSupplier.isEmpty())
                break;

            newProduct = new ProductType(tempId, tempName, tempSupplier);
            System.out.println("New product is " + newProduct.getProductID() + " - " + newProduct.getName() + " - "
             + newProduct.getSupplier());
            System.out.print("Is this correct? (Y/N): ");
            String inp = reader.nextLine();
            if(inp.equalsIgnoreCase("Y"))
            {
                if(Database.addObject(newProduct.getProductID(), newProduct)) {
                    System.out.println("New product added successfully!\n");
                    exitLoop = true;
                }
                else
                    System.out.println("Error: failed to add object");
            }
        }

    }

    private static void displayAddStaffMenu()
    {
        boolean exitLoop = false;
        StaffAccount newStaff;
        Scanner reader = new Scanner(System.in);

        while (!exitLoop)
        {
            System.out.print("Please enter new staff id, or enter empty line to cancel: ");
            String tempId = reader.nextLine();
            if(tempId.isEmpty())
                break;
            if(Database.getByID(tempId, StaffAccount.class) != null)
            {
                System.out.println("Error: Staff ID already exists. try again");
                continue;
            }

            System.out.print("Please enter password for new account, or enter empty line to cancel: ");
            String tempPass = reader.nextLine();
            if(tempPass.isEmpty())
                break;

            boolean perLoop = false;
            Permission tempPermissions = null;
            System.out.println("Permission levels for accounts are as follows: Manager, Warehouse, or Sales. " +
                    "\nType as presented to set the permission level");
            while(!perLoop)
            {
                System.out.print("Please enter the permission level for new staff member, or enter empty line to cancel: ");
                String tempInp = reader.nextLine();
                if (tempInp.isEmpty())
                    break;
                if (tempInp.equalsIgnoreCase("Manager"))
                    tempPermissions = Permission.MANAGER;
                else if (tempInp.equalsIgnoreCase("Warehouse"))
                    tempPermissions = Permission.WAREHOUSE;
                else if (tempInp.equalsIgnoreCase("Sales"))
                    tempPermissions = Permission.SALES;
                else
                {
                    System.out.println("Error: not a valid permission level");
                    continue;
                }
                perLoop = true;
            }

            newStaff = new StaffAccount(tempId, tempPass, tempPermissions);
            System.out.print("Are all the details entered correct? (Y/N): ");
            String inp = reader.nextLine();
            if(inp.equalsIgnoreCase("Y"))
            {
                if(Database.addObject(newStaff.getStaffID(), newStaff)) {
                    System.out.println("New account created successfully!\n");
                    exitLoop = true;
                }
                else
                    System.out.println("Error: failed to create account");
            }
        }
    }

    private static void displayRemoveProductMenu()
    {

    }

    private static void displayRemoveStaffMenu()
    {

    }
}