package dao.noticias;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import dao.DAOFactory;
import entities.Fuente;
import entities.Noticia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DAONoticiasRSS implements DAONoticias{
    @Override
    public List<Noticia> getNoticias(Fuente fuente) {
        List<Noticia> noticias = new ArrayList<>();
        try {
            RssReader reader = new RssReader();
            Stream<Item> rssFeed = null;
            try {
                rssFeed = reader.read(fuente.getURL());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            List<Item> articles = rssFeed.collect(Collectors.toList());
            for (Item item : articles) {
                Noticia noticia = new Noticia(item.getTitle().get(),item.getDescription().get(),item.getLink().get());
                noticias.add(noticia);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return noticias;
    }



}
