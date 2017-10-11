package main.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.CustomerAccount;

import main.*;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class purchaseSummaryController implements Initializable
{
    @FXML private TableView<Purchase> cartTable;
    @FXML private TableColumn<Purchase, Integer> cartQuant;
    @FXML private TableColumn<Purchase, String> cartName;
    @FXML private TableColumn<Purchase, Double> cartPrice;
    @FXML private TableColumn<Purchase, Double> cartTotal;

    @FXML private Label gCost;
    @FXML private Label dCost;
    @FXML private Label nCost;
    @FXML private Label oPoints;
    @FXML private Label cPoints;

    private Sale cart;
    private CustomerAccount account;
    private static boolean confirmed = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cart = PurchaseModeController.getCart();
        account = LoginController.getAccount();

        ObservableList<Purchase> purchases = FXCollections.observableArrayList(cart.getPurchases());

        cartQuant.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));
        cartName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getName()));
        cartPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProduct().getBasePrice(1)));
        cartTotal.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUndiscountedPrice()));

        cartTable.setItems(purchases);

        gCost.setText(String.valueOf(cart.getUndiscountedTotal()));
        dCost.setText(String.valueOf(cart.getTotal() - cart.getUndiscountedTotal()));
        nCost.setText(String.valueOf(cart.getTotal()));
        oPoints.setText(String.valueOf(account.getLoyaltyPoints()));
//        cPoints.setText();
    }

    @FXML
    private void setConfirm() throws ParseException
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Database.addObject(dateFormat.format(cart.getDate()), cart);
        Stage stage = (Stage)cartTable.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thank you for shopping with us!");
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.showAndWait();
        stage.close();
        confirmed = true;
    }

    @FXML
    private void setCancel()
    {
        Stage stage = (Stage)cartTable.getScene().getWindow();
        stage.close();
    }

    public static boolean checkConfirm()
    {
        if(confirmed)
        {
            confirmed = false;
            return true;
        }

        return false;
    }
}
