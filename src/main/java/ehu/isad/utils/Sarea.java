package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.Book;
import ehu.isad.Details;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Sarea {
    Properties properties = Utils.lortuEzarpenak();
    public Book readFromUrl(String isbn) throws IOException {
        URL openlibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+isbn+"&jscmd=details&format=json");
        URLConnection yc = openlibrary.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine();
        in.close();

        String[] zatiak = inputLine.split("ISBN:"+isbn+"\":");
        inputLine = zatiak[1].substring(0, zatiak[1].length()-1);
        Gson gson = new Gson();
        return gson.fromJson(inputLine, Book.class);
    }

    public Details sartu_datuak(String GJSon){
        Gson g=new Gson();
        return g.fromJson(GJSon, Details.class);
    }
    public Image eman_irudia(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }

    public Image irudia_ireki(String Izena) throws IOException {
        String irudi_karpeta=properties.getProperty("imagePath");
        String irudi_bidea=irudi_karpeta+"/"+Izena;
        File irudia=new File(irudi_bidea);
        BufferedImage img=null;
        img= ImageIO.read(irudia);
        return SwingFXUtils.toFXImage(img,null);
    }

    public void irudia_gorde(Image irudia,String izena) throws IOException {
        String bidea=properties.getProperty("imagePath");
        String bide_osoa=bidea+"/"+izena;
        System.out.println(bide_osoa);
        File fitxategia=new File(bide_osoa);
        ImageIO.write(SwingFXUtils.fromFXImage(irudia,null),"jpg",fitxategia);
        System.out.println("IRUDIA SORTU DA.");

    }
    
}
