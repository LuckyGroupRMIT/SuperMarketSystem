package main.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import main.*;

public class AddPopupController
{
    @FXML TextField userID;
    @FXML TextField password;
    @FXML ChoiceBox<Permission> permissions;

    @FXML TextField prodID;
    @FXML TextField prodName;
    @FXML TextField prodSupp;
    @FXML TextField prodPrice;

    @FXML Button cancel;

    @FXML private void setCancel()
    {
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

    @FXML private void setStaffAdd()
    {
        StaffAccount staff = new StaffAccount(userID.getText(), password.getText(), permissions.getValue());
        Database.addObject(staff.getStaffID(), staff.getClass());
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

    @FXML private void setProdAdd()
    {
        ProductType product = new ProductType(prodID.getText(), prodName.getText(), prodSupp.getText());
        product.setBasePrice(PricingMethod.QUANTITY, Double.parseDouble(prodPrice.getText()));
        if(!Database.addObject(product.getProductID(), product))
            System.out.println("FAILED");
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }
}
