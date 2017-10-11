package main.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.*;

import java.io.IOException;

public class LoginController
{
    @FXML Button login;
    @FXML Button cancel;
    @FXML TextField userID;
    @FXML PasswordField password;
    @FXML Label invalid;

    private static CustomerAccount account;

    @FXML TextField accNo;

    @FXML private void setCancel()
    {
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

    @FXML private void setLogin() throws Exception
    {
        if(userID.getText() == null || userID.getText().isEmpty())
            return;
        else if (password.getText() == null || password.getText().isEmpty())
            return;
        else if (password.getText().equals("admin") && userID.getText().equals("admin"))
        {
            Stage stage = (Stage)login.getScene().getWindow();
            Stage owner = (Stage)stage.getOwner();

            Parent managerRoot = FXMLLoader.load(getClass().getResource("managermenu.fxml"));
            owner.setTitle("Manager Menu");
            stage.close();
            owner.getScene().setRoot(managerRoot);
        }

        else
        {
            if(Login.checkStaffDetails(userID.getText(), password.getText()))
            {
                switch (Login.checkPermissions(userID.getText()))
                {
                    case SALES:
                        PurchaseModeController.setSalesmode(true);
                        break;
                    case MANAGER:
                        Stage stage = (Stage)login.getScene().getWindow();
                        Stage owner = (Stage)stage.getOwner();

                        Parent managerRoot = FXMLLoader.load(getClass().getResource("managermenu.fxml"));
                        owner.setTitle("Manager Menu");
                        stage.close();
                        owner.getScene().setRoot(managerRoot);
                        break;
                    case EMPTY:
                        invalid.setVisible(true);
                        break;
                }
            }
            else
                invalid.setVisible(true);
        }
    }

    @FXML private void setSubmit() throws IOException
    {
        if(Login.checkCustomerDetails(accNo.getText()))
        {
            account = (CustomerAccount) Database.getByID(accNo.getText(), account.getClass());
    }
        else
        {
            account = new CustomerAccount(accNo.getText());
        }

        Stage stage = new Stage();
        Stage parent = (Stage) cancel.getScene().getWindow();
        Parent summaryRoot = FXMLLoader.load(getClass().getResource("purchaseSummary.fxml"));
        Scene summary = new Scene(summaryRoot);
        stage.setTitle("Cart Summary");
        stage.setScene(summary);
        stage.show();
        parent.close();
    }

    public static CustomerAccount getAccount() {
        return account;
    }
}
