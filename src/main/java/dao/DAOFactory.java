package dao;

import dao.fuentes.DAOFuentes;
import dao.fuentes.DAOFuentesXML;
import dao.noticias.DAONoticias;
import dao.noticias.DAONoticiasRSS;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFuentes daoFuentes;
    private DAONoticias daoNoticias;

    private DAOFactory(){}

    public static DAOFactory getInstance() {
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public DAOFuentes getDAOFuentes(){
        if(daoFuentes == null){
            daoFuentes = new DAOFuentesXML();
        }
        return daoFuentes;
    }

    public DAONoticias getDAONoticias(){
        if(daoNoticias == null){
            daoNoticias = new DAONoticiasRSS();
        }
        return daoNoticias;
    }

}
