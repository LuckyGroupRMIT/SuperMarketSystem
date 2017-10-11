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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController  implements Initializable
{
    @FXML private TableView<ProductType> prodTable;
    @FXML private TableColumn<ProductType, String> prodID;
    @FXML private TableColumn<ProductType, String> prodName;
    @FXML private TableColumn<ProductType, String> prodSupp;
    @FXML private TableColumn<ProductType, Double> prodPrice;
    @FXML private TableColumn<ProductType, Integer> prodStock;
    @FXML private TableColumn<ProductType, Integer> prodRestock;

    @FXML private TableView<StaffAccount> staffTable;
    @FXML private TableColumn<StaffAccount, String> staffID;
    @FXML private TableColumn<StaffAccount, String> staffPass;
    @FXML private TableColumn<StaffAccount, String> staffPerm;

    @FXML Button exit;
    @FXML Button previous;
    @FXML Button add;
    @FXML Button remove;
    @FXML Tab staffTab;
    @FXML Tab prodTab;

    private ObservableList<ProductType> prodData;
    private ObservableList<StaffAccount> staffData;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        getTableData();

        prodID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductID()));
        prodName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        prodSupp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupplier()));
        prodPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBasePrice(PricingMethod.QUANTITY, 1)));
        prodStock.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCurrentStock()));
        prodRestock.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRestock()));

        prodName.setOnEditCommit(event -> {
            ProductType prod = event.getRowValue();
            prod.setName(event.getNewValue() != null ? event.getNewValue() : event.getOldValue());
            Database.addObject(prod.getProductID(), prod);
        });
        prodSupp.setOnEditCommit(event -> {
            ProductType productType = event.getRowValue();
            productType.setSupplier(event.getNewValue() != null ? event.getNewValue() : event.getOldValue());
            Database.addObject(productType.getProductID(), productType);
        });
        prodPrice.setOnEditCommit(event -> {
            ProductType productType = event.getRowValue();
            productType.setBasePrice(PricingMethod.QUANTITY, event.getNewValue() != null ? event.getNewValue() : event.getOldValue());
            Database.addObject(productType.getProductID(), productType);
        });
        prodStock.setOnEditCommit(event -> {
            ProductType productType = event.getRowValue();
            productType.setCurrentStock(event.getNewValue() != null ? event.getNewValue() : event.getOldValue());
            Database.addObject(productType.getProductID(), productType);
        });
        prodRestock.setOnEditCommit(event -> {
            ProductType productType = event.getRowValue();
            productType.setRestock(event.getNewValue() != null ? event.getNewValue() : event.getOldValue());
            Database.addObject(productType.getProductID(), productType);
        });

        staffID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStaffID()));
        staffPass.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        staffPerm.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPermissions().toString()));

        prodTable.setItems(prodData);
        staffTable.setItems(staffData);
    }

    private void getTableData()
    {
        prodData = FXCollections.observableArrayList(Database.listAllProducts());
        staffData = FXCollections.observableArrayList(Database.listAllAccounts());
    }

    @FXML private void setExit()
    {
        Platform.exit();
    }

    @FXML private void setPrevious() throws Exception
    {
        Stage stage = (Stage)previous.getScene().getWindow();
        Parent purchaseRoot = FXMLLoader.load(getClass().getResource("managermenu.fxml"));
        stage.setTitle("Self-Serve Shopping");
        stage.getScene().setRoot(purchaseRoot);
    }

    @FXML private void setAdd() throws IOException
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNIFIED);
        stage.initOwner(prodTable.getScene().getWindow());
        stage.setTitle("Add Menu");

        if(staffTab.isSelected())
        {
            Parent staffAddRoot = FXMLLoader.load(getClass().getResource("staffAddPopup.fxml"));
            Scene staffAdd = new Scene(staffAddRoot);
            stage.setScene(staffAdd);
        }
        else
        {
            Parent prodAddRoot = FXMLLoader.load(getClass().getResource("productAddPopup.fxml"));
            Scene prodAdd = new Scene(prodAddRoot);
            stage.setScene(prodAdd);
        }

        stage.show();
    }

    @FXML private void setRemove()
    {
        if(staffTab.isSelected())
        {
            StaffAccount staff = staffTable.getSelectionModel().getSelectedItem();
            Database.removeObject(staff.getStaffID(), staff.getClass());
        }
        else
        {
            ProductType prod = prodTable.getSelectionModel().getSelectedItem();
            Database.removeObject(prod.getProductID(), prod.getClass());
        }
    }
}
