package ui;


import dao.DAOFactory;
import dao.noticias.DAONoticias;
import entities.Fuente;
import entities.Noticia;

import java.util.List;
import java.util.Scanner;

public class Menu {


    public static void principal(){
        System.out.print("1) Fuentes\n2) Lectura\nOpción:");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "1" -> {
                fuentes();
            }
            case "2" -> {
                lectura();
            }
        }
    }

    public static void fuentes(){
        System.out.println("1)Anadir\n2)Mostrar\n");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "1" -> {
                anadir();
                principal();
            }
            case "2" -> {
                mostrar();
                principal();
            }
        }
    }

    public static void anadir(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("URL");
        String URL = scanner.nextLine();
        Fuente fuente = new Fuente(nombre, URL);
        DAOFactory.getInstance().getDAOFuentes().inserta(fuente);
    }

    public static void mostrar(){
        List<Fuente> fuentes = DAOFactory.getInstance().getDAOFuentes().getFuentes();
        System.out.println(fuentes);

    }
    public static void lectura(){
        Scanner scanner = new Scanner(System.in);
        List<Fuente> fuentes = DAOFactory.getInstance().getDAOFuentes().getFuentes();
        for(int i = 0; i < fuentes.size(); i++){
            Fuente fuente = fuentes.get(i);
            System.out.println(i+".- "+fuente.getNombre());
        }
        System.out.print("Fuente elegida: ");
        Integer posicionElegida = Integer.parseInt(scanner.nextLine());
        System.out.println("Muestro las noticias");
        List<Noticia> noticias = DAOFactory.getInstance().getDAONoticias().getNoticias(fuentes.get(posicionElegida));
        for(int i = 0; i < noticias.size(); i++){
            Noticia noticia = noticias.get(i);
            System.out.println(noticia.getTitulo());
            System.out.println(noticia.getTexto());
            System.out.println("Ver más info en: "+noticia.getUrl());
            System.out.println("---\n\n");
        }

    }

}
