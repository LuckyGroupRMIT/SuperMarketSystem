package main.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.*;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @FXML TableColumn<ProductType, Double> prodDisc;

    @FXML TableView<Sale> sales;
    @FXML TableColumn<Sale, Date> salesDate;
    @FXML TableColumn<Sale, Double> salesSub;
    @FXML TableColumn<Sale, Double> salesDisc;
    @FXML TableColumn<Sale, Double> salesTot;
    @FXML TableColumn salesProd;

    @FXML TableView<BulkDiscount> discounts;
    @FXML TableColumn<BulkDiscount, String> discName;
    @FXML TableColumn<BulkDiscount, Integer> discAmount;
    @FXML TableColumn<BulkDiscount, Double> discPercent;

    @FXML Button signout;
    @FXML Button supplyGen;
    @FXML Button exit;
    @FXML Button search;
    @FXML Button admin;
    @FXML TextField startDate;
    @FXML TextField endDate;
    @FXML Label salesRev;

    private ObservableList<ProductType> prodData;
    private ObservableList<Sale> saleData;
    private ObservableList<BulkDiscount> discData;
    private FilteredList<Sale> filteredList;
    SortedList<Sale> sortedList;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        getTableData();

        prodID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdID()));
        prodName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdName()));
        prodSupp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdSupp()));
        prodPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBasePrice( 1)));
        prodRev.setCellValueFactory(cellData -> new SimpleObjectProperty<>(ProductReport.getProductRevenue(cellData.getValue())));

        salesDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCurrentDate()));
        salesSub.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotal()));
        salesTot.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotal()));

        discName.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        discAmount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));
        discPercent.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPercentage()));

        filteredList = new FilteredList<>(saleData, cell-> false);
        sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(sales.comparatorProperty());

        sales.setItems(sortedList);
        products.setItems(prodData);
        discounts.setItems(discData);
    }

    private void getTableData()
    {
        prodData = FXCollections.observableArrayList(Database.listAllProducts());
        saleData = FXCollections.observableArrayList(Database.listAllSales());
        discData = FXCollections.observableArrayList(Database.listAllDiscounts());
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

    @FXML private void setSearch() throws ParseException
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        filteredList.setPredicate(sale -> {
            try {
                if (startDate.getText() == null || startDate.getText().isEmpty())
                    return false;
                if (endDate.getText() == null || endDate.getText().isEmpty())
                    return false;

                Date firstDate = df.parse(startDate.getText());
                Date secondDate = df.parse(endDate.getText());

                return sale.getCurrentDate().after(firstDate) && sale.getCurrentDate().before(secondDate);

            } catch (ParseException parse)
            {
                System.out.println("Unable to parse dates.");
                return false;
            }
        });

        salesRev.setText(String.valueOf(ProductReport.getSaleForDates(startDate.getText(), endDate.getText())));
    }

    @FXML private void setDiscAdd() throws Exception
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNIFIED);
        stage.initOwner(admin.getScene().getWindow());
        stage.setTitle("Add Discount Menu");
        Parent discAddRoot = FXMLLoader.load(getClass().getResource("discountAddPopup.fxml"));
        Scene discAdd = new Scene(discAddRoot);
        stage.setScene(discAdd);
        stage.show();
    }

    @FXML private void setSupplyGen() throws Exception
    {

        Alert alert;
        if(ProductReport.generateSupplyReport())
            alert = new Alert(Alert.AlertType.INFORMATION, "Supply Report Generated successfully!");
        else
            alert = new Alert(Alert.AlertType.INFORMATION, "Supply Report Failed to Generate!");
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
