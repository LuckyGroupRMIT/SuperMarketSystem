package main;

import java.io.IOException;

public class SuperMarketTerminal {
	// This is to be the main class that will be run to start the program

    public static void main(String[] args) {
        try {
            Database.initAllMaps();
            PurchaseMode.displayPurchaseMenu();
            Database.saveAllMaps();
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
