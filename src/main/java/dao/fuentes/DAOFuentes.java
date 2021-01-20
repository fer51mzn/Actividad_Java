package dao.fuentes;

import entities.Fuente;

import java.util.List;

public interface DAOFuentes {

    public List<Fuente> getFuentes();
    public void inserta(Fuente fuente);

}
