package main.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.*;

import java.awt.*;
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
        prodPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(ProductReport.getProductRevenue(cellData.getValue())));

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
}
