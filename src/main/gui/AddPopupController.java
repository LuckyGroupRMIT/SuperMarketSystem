package main.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import main.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPopupController implements Initializable
{
    @FXML TextField userID;
    @FXML TextField password;
    @FXML ChoiceBox<Permission> permissions;

    @FXML TextField prodID;
    @FXML TextField prodName;
    @FXML TextField prodSupp;
    @FXML TextField prodPrice;
    @FXML TextField prodRestock;
    @FXML ChoiceBox<PricingMethod> prodMethod;

    @FXML TextField discID;
    @FXML TextField discAmount;
    @FXML TextField discPercent;

    @FXML Button cancel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(AdminController.getStaff())
            permissions.setItems(FXCollections.observableArrayList(Permission.MANAGER, Permission.SALES, Permission.WAREHOUSE));
        else if(AdminController.getProduct())
            prodMethod.setItems(FXCollections.observableArrayList(PricingMethod.QUANTITY, PricingMethod.WEIGHT));
    }

    @FXML private void setCancel()
    {
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

    @FXML private void setStaffAdd()
    {
        StaffAccount staff = new StaffAccount(userID.getText(), password.getText(), permissions.getValue());
        if(!Database.addObject(staff.getID(), staff))
            System.out.println("ERROR");
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

    @FXML private void setProdAdd()
    {
        ProductType product = new ProductType(prodID.getText(), prodName.getText(), prodSupp.getText(),
                Integer.parseInt(prodRestock.getText()), prodMethod.getValue(), Double.parseDouble(prodPrice.getText()));
        if(!Database.addObject(product.getProdID(), product))
            System.out.println("FAILED");
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

    @FXML private void setDiscAdd()
    {
        String discProd = ((ProductType)Database.getByID(discID.getText(), ProductType.class)).getProdName();
        Discount discount = new BulkDiscount(discProd, Integer.parseInt(discAmount.getText()), Double.parseDouble(discPercent.getText())/100);
        Database.addObject(((BulkDiscount)discount).getName(), discount);

        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }
}
