package Negocio;

import Datos.DCategoria;
import java.util.LinkedList;

public class NCategoria {
        public DCategoria dCategoria;

    public NCategoria() {}
    
    public void insertar(LinkedList<String> arg) {
        dCategoria = new DCategoria();

        dCategoria.setNombre(arg.get(0));
        dCategoria.setCodigoDewey(Integer.parseInt(arg.get(1)));
        dCategoria.insertar();
    }
    
        public void modificar(LinkedList<String> arg) {
        dCategoria = new DCategoria();

        dCategoria.setId(Integer.parseInt(arg.get(0)));
        dCategoria.setNombre(arg.get(1));
        dCategoria.setCodigoDewey(Integer.parseInt(arg.get(2)));
        dCategoria.modificar();
    }
        
    public LinkedList<String> listar() {
        dCategoria = new DCategoria();
        return dCategoria.listar();
    }

    public void eliminar(int id) {
        dCategoria = new DCategoria();
        dCategoria.setId(id);
        dCategoria.eliminar();
    }

    public LinkedList<String> mostrarId(int id) {
        dCategoria = new DCategoria();
        dCategoria.setId(id);
        return dCategoria.mostrarID();
    }

    public boolean existe(String id) {
        dCategoria = new DCategoria();
        int idE = Integer.parseInt(id);
        dCategoria.setId(idE);
        return dCategoria.existeID();
    }
    
}
