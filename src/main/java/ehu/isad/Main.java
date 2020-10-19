package ehu.isad;

import ehu.isad.controllers.Controller;
import ehu.isad.controllers.InformazioKud;
import ehu.isad.utils.Sarea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/*
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{

    Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(root, 300, 275));
    primaryStage.show();

  }


  public static void main(String[] args) {
    launch(args);
  }

}
*/
public class Main extends Application{
  private Text label;
  private Parent mainUI;
  private Parent informazioaUI;
  private Controller mainkud;
  private InformazioKud informaziokud;
  private Stage stage;
  private Scene liburuScene;
  private Scene detailsScene;

  @Override
  public void start(Stage primaryStage) throws IOException {

    //primaryStage.setTitle("OpenLibrary APIa aztertzen");

/*
    comboBox.setOnAction( e -> {
      Book book = (Book)comboBox.getValue();
      try {
        Book liburua = readFromUrl(book.isbn);
        this.label.setText( liburua.details.title + "\n" +
                liburua.details.number_of_pages + "\n" +
                liburua.details.publishers[0] );

      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    });
*/

    stage=primaryStage;
    this.pantailaKargatu();
    stage.setTitle("Liburuen Informazioa API");
    stage.setScene(liburuScene);
    stage.show();
  }

  private void pantailaKargatu() throws IOException {
    FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/sample.fxml"));
    mainUI = (Parent) loaderMain.load();
    mainkud = loaderMain.getController();
    mainkud.setMainApp(this);
    liburuScene=new Scene(mainUI);

    FXMLLoader loaderinfo=new FXMLLoader(getClass().getResource("/informazio.fxml"));
    informazioaUI = (Parent) loaderinfo.load();
    informaziokud = loaderinfo.getController();
    informaziokud.setMainApp(this);
    detailsScene=new Scene(informazioaUI);


  }

  public static void main(String[] args) {
    launch(args);
  }
  public void mainErakutsi(){
      stage.setScene(liburuScene);
      //stage.setScene(new Scene(mainUI));
      stage.show();
  }
  public void infoErakutsi(Details xehetasunak,Image irudia) throws IOException{
      stage.setScene(detailsScene);
      //stage.setScene(new Scene(informazioaUI));
      stage.show();
      informaziokud.informazioa_jarri(xehetasunak,irudia);
  }



}
//https://openlibrary.org/api/books?bibkeys=ISBN:1491910399&jscmd=details&format=json"

