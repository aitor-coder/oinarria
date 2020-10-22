package ehu.isad.controllers.db;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.utils.Utils;
import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    Properties properties = Utils.lortuEzarpenak();
    private ZerbitzuKud() {
    }

    public Book osatuta_dago(String isbn) throws SQLException {
        String sententzia="select izen_osoa,argitaletxeak,orriak,osatuta,thumbnail_url from liburua where isbn='"+isbn+"';";
        DBKudeatzaile dk=DBKudeatzaile.getInstantzia();
        ResultSet rs=dk.execSQL(sententzia);
        Details d = null;
        Book b = null;

        rs.next();
        Integer osatuta = rs.getInt("osatuta");
        if (osatuta.equals(0)) {
            String izena = rs.getString("izen_osoa");

            String argitaletxea = rs.getString("argitaletxeak");

            String[] argitaletxeak=argitaletxea.split(",");
            for(int i=0;i<argitaletxeak.length;i++){
                System.out.println(argitaletxeak[i]);
            }

            Integer orriak = rs.getInt("orriak");
            String irudia = rs.getString("thumbnail_url");
            System.out.println(irudia);
            b=new Book(isbn,izena);
            d = new Details();
            d.setTitle(izena);
            d.setNumber_of_pages(orriak);
            d.setPublishers(argitaletxeak);
            b = new Book(isbn, izena);
            b.setThumbnail_url(irudia);
            b.setDetails(d);
            System.out.println(b.toString());

        }
        else{
            b=null;
        }
        return b;
    }
    public List<Book> lortuLiburuak() {

        String query = "select isbn,izena from liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        List<Book> liburuak = new ArrayList<>();
        try {
            while (rs.next()) {
                String kodea = rs.getString("isbn");
                String izena = rs.getString("izena");
                System.out.println(kodea + ":" + izena);
                Book b1=new Book(kodea,izena);
                System.out.println(b1.toString());
                liburuak.add(b1);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return liburuak;
    }
//    public void ezabatu_dbs(String izena){
//        String sententzia="Delete from services where izena='"+izena+"';";
//        DBKudeatzaile dbKudeatzaile=DBKudeatzaile.getInstantzia();
//        ResultSet rs=dbKudeatzaile.execSQL(sententzia);
//
//    }
    public void gorde_liburua(Book liburu_oinarri, Book liburua_info, String irudia, Image irudia_fitx) throws IOException {
        String argitaletxea=liburua_info.getDetails().getPublishers()[0];
        for(int i=0;i>liburua_info.getDetails().getPublishers().length;i++){
            argitaletxea +=", "+liburua_info.getDetails().getPublishers()[i];
        }
        String irudi_path=irudia.split("/")[irudia.split("/").length-1];

        System.out.println(irudi_path);
        System.out.println(properties.getProperty("imagePath")+"/"+irudi_path);


        System.out.println(irudi_path);
        System.out.println(argitaletxea);
        argitaletxea=argitaletxea.replace("'","\\'");
        System.out.println(argitaletxea);
        String sententzia="update liburua set `izen_osoa`='"+liburua_info.getDetails().getTitle()+"', `info_url`='"+liburua_info.getInfo_url()+"', `bib_key`='"+liburua_info.getBib_key()+"', `preview_url`='"+liburua_info.getPreview_url()+"', `thumbnail_url`='"+liburua_info.getThumbnail_url()+"', `argitaletxeak`='"+argitaletxea+"', `orriak`='"+liburua_info.getDetails().getNumber_of_pages()+"', `osatuta`='0' WHERE `isbn`='"+ liburu_oinarri.isbn+"';";
//        String sententzia="update liburua set `izen_osoa`='"+liburua_info.getDetails().getTitle()+"', `info_url`='"+liburua_info.getInfo_url()+"', `bib_key`='"+liburua_info.getBib_key()+"', `preview_url`='"+liburua_info.getPreview_url()+"', `thumbnail_url`='"+liburua_info.getThumbnail_url()+"', `argitaletxeak`='"+argitaletxea+"', `orriak`='"+liburua_info.getDetails().getNumber_of_pages()+"', `osatuta`='0', irudia='"+irudi_path+"' WHERE `isbn`='"+ liburu_oinarri.isbn+"';";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(sententzia);

    }
}
