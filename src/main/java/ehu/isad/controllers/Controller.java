package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Main;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Main main_app;
    public void setMainApp(Main main) {
        this.main_app=main;
    }


    @FXML
    private AnchorPane leiho_nagusia;
    @FXML
    private ComboBox liburu_zerrenda;
    @FXML
    private Button sartu;


    @FXML
    void begiratu_informazioa(ActionEvent event) throws IOException {
        Book liburua= (Book) liburu_zerrenda.getValue();
        Sarea s=new Sarea();
        Book liburu_info= s.readFromUrl(liburua.isbn);

        System.out.println(liburu_info.details.toString());
        System.out.println(liburu_info.thumbnail_url.replace("-S","-M"));

        Image irudia=s.eman_irudia(liburu_info.thumbnail_url.replace("-S","-M"));

        main_app.infoErakutsi(liburu_info.details,irudia);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(
                new Book("1491910399", "R for Data Science"),
                new Book("1491946008", "Fluent Python"),
                new Book("9781491906187", "Data Algorithms")
        );
        liburu_zerrenda.setItems(books);
        liburu_zerrenda.setEditable(false);
        liburu_zerrenda.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                if (book==null)
                    return "";
                return book.title;
            }

            @Override
            public Book fromString(String string) {
                return null;
            }
        });
    }
}

