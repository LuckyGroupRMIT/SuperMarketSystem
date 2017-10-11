package main;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SuperMarketTerminal extends Application{
	// This is to be the main class that will be run to start the program

    public static void main(String[] args) {
        try {
            Database.initAllMaps();
//            PurchaseMode.displayPurchaseMenu();
            launch(args);
            Database.saveAllMaps();
        }catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui/purchasemode.fxml"));

        Scene scene = new Scene(root, 1280, 720);

        stage.setTitle("Self-Serve Shopping");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }
}
