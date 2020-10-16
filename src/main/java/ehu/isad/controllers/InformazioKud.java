package ehu.isad.controllers;

import ehu.isad.Details;
import ehu.isad.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;

public class InformazioKud {
    private Main main_app;
    public void setMainApp(Main main){
        this.main_app=main;

    }

    public void informazioa_jarri(Details xehetasunak, Image irudia){
        if (xehetasunak==null){
            irudi_taula.setImage(irudia);
        }
        else {
            izena_label.setText(xehetasunak.title);
            argitaletxea_label.setText(Arrays.toString(xehetasunak.publishers));
            orrialde_label.setText(xehetasunak.number_of_pages.toString());
            irudi_taula.setImage(irudia);
        }


    }
    @FXML
    private AnchorPane bigarren_leihoa;
    @FXML
    private Label izena_label;

    @FXML
    private Button atzera_button;

    @FXML
    private Label argitaletxea_label;

    @FXML
    private Label orrialde_label;

    @FXML
    private ImageView irudi_taula;
    @FXML
    void bueltatu_hasierara(ActionEvent event) {
        atzera_button.getScene().setRoot(main_app.mainErakutsi());
        //main_app.mainErakutsi();

    }


}
