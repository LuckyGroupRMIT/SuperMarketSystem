package main.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.*;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ManagerController implements Initializable
{
    @FXML TableView<ProductType> products;
    @FXML TableColumn<ProductType, String> prodID;
    @FXML TableColumn<ProductType, String> prodName;
    @FXML TableColumn<ProductType, String> prodSupp;
    @FXML TableColumn<ProductType, Double> prodPrice;
    @FXML TableColumn<ProductType, Double> prodRev;
//    @FXML TableColumn<ProductType, > prodDisc;

    @FXML TableView<Sale> sales;
    @FXML TableColumn<Sale, Date> salesDate;
    @FXML TableColumn<Sale, Double> salesSub;
//    @FXML TableColumn salesDisc;
    @FXML TableColumn<Sale, Double> salesTot;
    @FXML TableColumn salesProd;

    @FXML Button signout;
    @FXML Button supplyGen;
    @FXML Button exit;
    @FXML Button search;
    @FXML Button admin;
    @FXML TextField startDate;
    @FXML TextField endDate;

    private ObservableList<ProductType> prodData;
    private ObservableList<Sale> saleData;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        getTableData();

        prodID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductID()));
        prodName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        prodSupp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupplier()));
        prodPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBasePrice(PricingMethod.QUANTITY, 1)));
        prodRev.setCellValueFactory(cellData -> new SimpleObjectProperty<>(ProductReport.getProductRevenue(cellData.getValue())));

        salesDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCurrentDate()));
        salesSub.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotal()));
        salesTot.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotal()));

        sales.setItems(saleData);
        products.setItems(prodData);
    }

    private void getTableData()
    {
        prodData = FXCollections.observableArrayList(Database.listAllProducts());
        saleData = FXCollections.observableArrayList(Database.listAllSales());
    }

    @FXML private void setSignout() throws Exception
    {
        Stage stage = (Stage)signout.getScene().getWindow();
        Parent purchaseRoot = FXMLLoader.load(getClass().getResource("purchasemode.fxml"));
        stage.setTitle("Self-Serve Shopping");
        stage.getScene().setRoot(purchaseRoot);
    }

    @FXML private void setExit()
    {
        Platform.exit();
    }

    @FXML private void setAdmin() throws Exception
    {
        Stage stage = (Stage)signout.getScene().getWindow();
        Parent adminRoot = FXMLLoader.load(getClass().getResource("adminmenu.fxml"));
        stage.setTitle("Admin Menu");
        stage.getScene().setRoot(adminRoot);
    }
}
