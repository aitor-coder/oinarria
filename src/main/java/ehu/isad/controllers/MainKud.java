package ehu.isad.controllers;

import ehu.isad.Main;
import ehu.isad.URLModel;
import ehu.isad.controllers.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainKud implements Initializable {

    private Main mainKud;
    public void setMainApp(Main main) {
        this.mainKud=main;
    }

    @FXML
    private AnchorPane leiho_nagusia;

    @FXML
    private Button bilatu;

    @FXML
    private TableView<URLModel> taula;

    @FXML
    private TableColumn<URLModel, String> url_zutabea;

    @FXML
    private TableColumn<URLModel, String> md5_zutabea;

    @FXML
    private TableColumn<URLModel, String> bertsioa_zutabea;

    @FXML
    private TextField txt_url;

    @FXML
    void bilaketa_egin(ActionEvent event) {

    }
    private ObservableList<URLModel> url_zerrenda= FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ZerbitzuKud zk=ZerbitzuKud.getInstance();
        List<URLModel> urlak=zk.lortuPhpBertsioak();
        taula.setEditable(true);
        url_zerrenda.addAll(urlak);

        url_zutabea.setCellValueFactory(new PropertyValueFactory<>("url"));
        md5_zutabea.setCellValueFactory(new PropertyValueFactory<>("md5"));
        bertsioa_zutabea.setCellValueFactory(new PropertyValueFactory<>("bertsioa"));


        Callback<TableColumn<URLModel, String>, TableCell<URLModel, String>> defaultTextFieldCellFactory
                = TextFieldTableCell.forTableColumn(new DefaultStringConverter());
        bertsioa_zutabea.setCellFactory(col -> {
            TableCell<URLModel, String> cell = defaultTextFieldCellFactory.call(col);
            cell.setOnMouseClicked(event -> {
                    cell.setEditable(true);
            });

            return cell ;
        });


        bertsioa_zutabea.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(t.getTablePosition().getRow())
                            .setBertsioa(t.getNewValue());


                }
        );

        taula.setItems(url_zerrenda);



    }
}


