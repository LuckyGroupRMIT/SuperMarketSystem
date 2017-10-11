package main.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.*;

public class LoginController
{
    @FXML Button login;
    @FXML Button cancel;
    @FXML TextField userID;
    @FXML PasswordField password;
    @FXML Label invalid;

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
        else
        {
            if(Login.checkDetails(userID.getText(), password.getText()))
            {
                switch (Login.checkPermissions(userID.getText()))
                {
                    case SALES:
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
}
