package Negocio;

import Datos.DCarrera;
import java.util.LinkedList;

public class NCarrera {
    
    public DCarrera dCarrera;

    public NCarrera() {
    }
 public void insertar(LinkedList<String> arg) {
        dCarrera = new DCarrera();

        dCarrera.setNombre(arg.get(0));
        dCarrera.setSigla(arg.get(1));
        dCarrera.setCodigo(Integer.parseInt(arg.get(2)));
        dCarrera.insertar();
    }

    public void modificar(LinkedList<String> arg) {
        dCarrera = new DCarrera();

        dCarrera.setId(Integer.parseInt(arg.get(0)));
        dCarrera.setNombre(arg.get(1));
        dCarrera.setSigla(arg.get(2));
        dCarrera.setCodigo(Integer.parseInt(arg.get(3)));
        dCarrera.modificar();
    }

    public LinkedList<String> listar() {
        dCarrera = new DCarrera();
        return dCarrera.listar();
    }

    public void eliminar(int id) {
        dCarrera = new DCarrera();
        dCarrera.setId(id);
        dCarrera.eliminar();
    }

    public LinkedList<String> mostrarId(int id) {
        dCarrera = new DCarrera();
        dCarrera.setId(id);
        return dCarrera.mostrarID();
    }

    public boolean existe(String id) {
        dCarrera = new DCarrera();
        int idE = Integer.parseInt(id);
        dCarrera.setId(idE);
        return dCarrera.existeID();
    }
}


