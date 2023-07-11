package Negocio;
import Datos.DGrupo;
import java.util.LinkedList;

public class NGrupo {
    public DGrupo dgrupo;
    public NGrupo(){}
    
    /*public void insertar(LinkedList<String> arg) {
        drol = new DRol();
        drol.setNombre(arg.get(0));
        drol.setDescripcion(arg.get(1));
        drol.insertar();
    }

    public void modificar(LinkedList<String> arg) {
        drol = new DRol();
        drol.setId(Integer.parseInt(arg.get(0)));
        drol.setNombre(arg.get(1));
        drol.setDescripcion(arg.get(2));
        drol.modificar();
    }

    public LinkedList<String> listar() {
        drol = new DRol();
        return drol.listar();
    }

    public void eliminar(int id) {
        drol = new DRol();
        drol.setId(id);
        drol.eliminar();
    }

    public LinkedList<String> mostrar(int id) {
        drol = new DRol();
        drol.setId(id);
        return drol.mostrarID();
    }
    
    public boolean existe(String id){
        drol = new DRol();
        int idE=Integer.parseInt(id);
        drol.setId(idE);
        return drol.existeID();
    }*/
    
    public boolean existe(String id){
        dgrupo = new DGrupo();
        int idE=Integer.parseInt(id);
        dgrupo.setId(idE);
        return dgrupo.existeID();
    }
}
