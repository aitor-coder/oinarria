package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.Main;
import ehu.isad.controllers.db.ZerbitzuKud;
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
import java.sql.SQLException;
import java.util.List;
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
    void begiratu_informazioa(ActionEvent event) throws IOException, SQLException {
        Book liburua= (Book) liburu_zerrenda.getValue();
        Sarea s = new Sarea();
        ZerbitzuKud zk = ZerbitzuKud.getInstance();
        Book liburu_info;
        Book b=zk.osatuta_dago(liburua.isbn);
        Image irudia;
        if(b!=null){
            System.out.println(b.getThumbnail_url());
            irudia=s.eman_irudia(b.getThumbnail_url().replace("-S","-M"));
            liburu_info=b;
            //b.getDetails().setTitle(liburua.);
            System.out.println("CACHEA ERABILTZEN ARI DA.");

        }
        else {
            liburu_info = s.readFromUrl(liburua.isbn);

            System.out.println(liburua.toString());
            System.out.println(liburu_info.toString());
            System.out.println(liburu_info.getDetails().toString());

            String irudi_url = liburu_info.getThumbnail_url().replace("-S", "-M");

            System.out.println(irudi_url);


            irudia = s.eman_irudia(irudi_url);

            zk.gorde_liburua(liburua, liburu_info, irudi_url);
            System.out.println("EZ DA CACHEA ERABILTZEN");
        }
        main_app.infoErakutsi(liburu_info.getDetails(),irudia);

    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        ObservableList<Book> books = FXCollections.observableArrayList();
//        books.addAll(
//                new Book("1491910399", "R for Data Science"),
//                new Book("1491946008", "Fluent Python"),
//                new Book("9781491906187", "Data Algorithms")
//        );
//        liburu_zerrenda.setItems(books);
//        liburu_zerrenda.setEditable(false);
//        liburu_zerrenda.setConverter(new StringConverter<Book>() {
//            @Override
//            public String toString(Book book) {
//                if (book==null)
//                    return "";
//                return book.title;
//            }
//
//            @Override
//            public Book fromString(String string) {
//                return null;
//            }
//        });
//    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<Book> books = FXCollections.observableArrayList();
        ZerbitzuKud zk=ZerbitzuKud.getInstance();

        List<Book> liburuak=zk.lortuLiburuak();
        System.out.println(liburuak.size());
        books.addAll(liburuak);
        System.out.println(books.size());
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

