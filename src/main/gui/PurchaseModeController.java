package main.gui;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Modality;
import javafx.stage.Stage;
import main.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseModeController implements Initializable{
    @FXML private TableView<ProductType> prodTable;
    @FXML private TableColumn<ProductType, String> prodID;
    @FXML private TableColumn<ProductType, String> prodName;
    @FXML private TableColumn<ProductType, String> prodSupp;
    @FXML private TableColumn<ProductType, Double> prodPrice;

    @FXML private TableView<Purchase> cartTable;
    @FXML private TableColumn<Purchase, Integer> cartQuant;
    @FXML private TableColumn<Purchase, String> cartName;
    @FXML private TableColumn<Purchase, Double> cartPrice;
    @FXML private TableColumn<Purchase, Double> cartTotal;

    @FXML private TextField prodSearch;
    @FXML private ChoiceBox<PricingMethod> pricingMethod;
    @FXML private Spinner addCounter;
    @FXML private Label gCost;
    @FXML private Label tCost;

    private ObservableList<ProductType> data;
    private ObservableList<Purchase> cart;
    private Sale sale;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sale = new Sale();
        getTableData();

        prodID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductID()));
        prodName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        prodSupp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSupplier()));
        prodPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBasePrice()));

        cartQuant.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAmount()));
        cartName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduct().getName()));
        cartPrice.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProduct().getBasePrice(cellData.getValue().getPricingMethod(), 1)));
        cartTotal.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUndiscountedPrice()));

//        Filter code altered from: http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
        FilteredList<ProductType> filteredList = new FilteredList<>(data, cell-> true);

        prodSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(productType -> {
                if(newValue == null || newValue.isEmpty())
                    return true;
                String lowercase = newValue.toLowerCase();
                if(productType.getProductID().toLowerCase().contains(lowercase))
                    return true;
                else if(productType.getName().toLowerCase().contains(lowercase))
                    return true;
                return false;
            });
        });

        SortedList<ProductType> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(prodTable.comparatorProperty());

        prodTable.setItems(sortedList);
        cartTable.setItems(cart);

//        pricingMethod.setItems(ObservableList<PricingMethod>());
    }

    private void getTableData()
    {
        data = FXCollections.observableArrayList(Database.listAllProducts());
        cart = FXCollections.observableArrayList(sale.getPurchases());
    }

    @FXML private void handleStaffBtn(Event event) throws Exception
    {
        Parent popupRoot = FXMLLoader.load(getClass().getResource("loginPopup.fxml"));
        Scene popup = new Scene(popupRoot);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(prodTable.getScene().getWindow());
        stage.setTitle("Staff Login");
        stage.setScene(popup);
        stage.show();
    }

    @FXML private void handleAddItemBtn() throws Exception
    {
        int amount = (int)addCounter.getValue();
        if(prodTable.getSelectionModel().getSelectedItem() == null)
            return;
        ProductType productType = prodTable.getSelectionModel().getSelectedItem();
        Purchase purchase = new Purchase(productType, PricingMethod.QUANTITY, amount);

        sale.addPurchase(purchase);
        cart.add(purchase);

        gCost.setText("$"+String.valueOf(sale.getTotal()));
        tCost.setText("$"+String.valueOf(sale.getTotal()));
    }

    @FXML private void setExit()
    {
        Platform.exit();
    }

    @FXML private void setPurchase() throws IOException
    {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("customerLoginPopup.fxml"));
        Scene login = new Scene(loginRoot);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(prodTable.getScene().getWindow());
        stage.setTitle("Customer Login");
        stage.setScene(login);
        stage.show();
    }
}
