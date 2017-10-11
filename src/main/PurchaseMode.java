package main;

import java.util.ArrayList;
import java.util.Scanner;

public class PurchaseMode {

    public static void displayPurchaseMenu()
    {
        boolean exitProgram = false;
        Scanner reader= new Scanner(System.in);  // Reading from System.in
        Sale newSale = new Sale();

        while (!exitProgram)
        {
            System.out.println("1. View all products");
            System.out.println("2. Enter product by name");
            System.out.println("3. Enter product by product id");
            System.out.println("4. View shopping cart");
            System.out.println("0. Staff Login");
            System.out.print("\n\nEnter a number to select option: ");

            int input = reader.nextInt(); // Scans the next token of the input as an int.

            switch (input)
            {
                case 0:
                    switch (Login.displayLogin())
                    {
                        case MANAGER:
                            if(Manager.displayManagerMenu())
                                exitProgram = true;
                            break;
                        case SALES:
                            if(displayEmployeeMenu(newSale))
                                exitProgram = true;
                            break;
                        case WAREHOUSE:
                            displayWarehouseMenu();
                            break;
                    }
                    break;
                case 1:
                    displayAllProducts(newSale);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    displayShoppingCart(newSale);
                    break;
                default:
                    System.out.println("Error: invalid option");
                    break;
            }
        }
    }

    private static void displayAllProducts(Sale sale)
    {
        final int itemNo = 10;
        int indexPos = 0;
        boolean exitLoop = false;
        ArrayList<ProductType> products = Database.listAllProducts();
        Scanner reader = new Scanner(System.in);

        while(!exitLoop)
        {
            System.out.printf("\t%10s\t|\t%20s\t|\t%20s\t|\t%s\n",
                    "ID", "name", "brand", "price");
            System.out.println(new String(new char[90]).replace("\0", "-"));
            if(indexPos > 10)
                System.out.println("P. Go to previous page");
            do {
                ProductType product = products.get(indexPos);

                System.out.printf("%d. %10s\t|\t%20s\t|\t%20s\t|\t$%.2f\n",
                        indexPos,
                        product.getProductID(),
                        product.getName(),
                        product.getSupplier(),
                        product.getBasePrice(PricingMethod.QUANTITY, 1));
                indexPos++;
            } while (indexPos % itemNo != 0 && indexPos < products.size());
            if(indexPos < products.size()-10)
                System.out.println("N. Go to next page");

            System.out.print("Type product number to add to cart, or enter empty line to cancel: ");
            String inp = reader.nextLine();
            if(inp.isEmpty())
                exitLoop = true;
            else
            {
                if(inp.matches("[0:9]"))
                {
                    int choice = Integer.parseInt(inp);
                    if(choice > products.size()) {
                        System.out.println("Error: no such item exists");
                        continue;
                    }
                    ProductType product = products.get(choice);


                    System.out.printf("Enter the amount of %s to add to cart, or enter empty line to cancel: ", product.getName());
                    int amount = Integer.parseInt(reader.nextLine());
                    System.out.printf("\nAdding %d of %s, is this correct? (Y/N): ", amount, product.getName());
                    inp = reader.nextLine();
                    if(inp.equalsIgnoreCase("Y"))
                    {
                        sale.addPurchase(new Purchase(product, PricingMethod.QUANTITY, amount));
                        System.out.println("Item added to cart!");
                    }
                }
                else
                {
                    switch (inp)
                    {
                        case "P":
                            if(indexPos >= 20)
                                indexPos -= 20;
                            else
                                System.out.println("Error: invalid input");
                            break;
                        case "N":
                            if(!(indexPos < products.size()-10))
                                System.out.println("Error: invalid input");
                    }
                }
            }
        }
    }

    private static void displayShoppingCart(Sale sale)
    {
        ArrayList<Purchase> cart = sale.getPurchases();
        Scanner reader = new Scanner(System.in);

        if(cart.isEmpty())
            System.out.println("Your shopping cart is currently empty.");

        else
        {
            System.out.println("Current Shopping Cart\n");
            System.out.println("id\t\t|\tname\t|\tbrand\t\t|\tprice");
            System.out.println("-------------------------------------------------");
            for (Purchase purchases : cart) {
                ProductType product = purchases.getProduct();

                System.out.printf("%s\t|\t%s\t|\t%s\t|\t$%.2f\n",
                        product.getProductID(),
                        product.getName(),
                        product.getSupplier(),
                        product.getBasePrice(PricingMethod.QUANTITY, 1));
            }
            System.out.printf("\nTotal cost is: $%.2f\n\n", sale.getTotal());

            System.out.print("Do you want to purchase all items in cart? (Y/N): ");
            String inp = reader.nextLine();
            if(inp.equalsIgnoreCase("Y"))
                makeSale(sale);
        }
    }

    private static void displayWarehouseMenu()
    {
        boolean exitProgram = false;
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        while (!exitProgram)
        {
            System.out.println("1. Purchase Mode");
            System.out.println("2. Staff Login");
            System.out.println("3. Quit");
            System.out.print("\n\nEnter a number to select option: ");

            int input = reader.nextInt(); // Scans the next token of the input as an int.

            switch (input)
            {

            }
        }

        reader.close();
    }

    private static boolean displayEmployeeMenu(Sale sale)
    {
        boolean exitLoop = false;
        Scanner reader = new Scanner(System.in);  // Reading from System.in

        while (!exitLoop)
        {
            System.out.println("1. Remove item from cart");
            System.out.println("2. Cancel customer sale");
            System.out.println("3. Exit program");
            System.out.println("4. Return to previous menu");
            System.out.print("\n\nEnter a number to select option: ");

            int input = reader.nextInt(); // Scans the next token of the input as an int.

            switch (input)
            {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    return true;
                case 4:
                    exitLoop = true;
                    break;
                default:
                    System.out.println("Error: invalid option");
                    break;
            }
        }

        return false;
    }

    private static void makeSale(Sale sale)
    {
//        sale.getCurrentDate().toString();
    }
}
