package Negocio;
import Datos.DCronograma;
import java.util.LinkedList;

public class NCronograma {
    public DCronograma dcronograma;
    public NCronograma(){}
    
    public LinkedList<String> listar() {
        dcronograma = new DCronograma();
        return dcronograma.listar();
    }
    
    public void eliminar(int id) {
        dcronograma = new DCronograma();
        dcronograma.setId(id);
        dcronograma.eliminar();
    }

    public LinkedList<String> mostrar(int id) {
        dcronograma = new DCronograma();
        dcronograma.setId(id);
        return dcronograma.mostrarID();
    }
    
    public void insertar(LinkedList<String> arg) {
        dcronograma = new DCronograma();
        dcronograma.setFechaInicio(arg.get(0));
        dcronograma.setFechaFin(arg.get(1));
        dcronograma.setHoraInicio(arg.get(2));
        dcronograma.setHoraFin(arg.get(3));
        dcronograma.setActividadId(Integer.parseInt(arg.get(4)));
        dcronograma.insertar();
    }

    public void modificar(LinkedList<String> arg) {
        dcronograma = new DCronograma();
        dcronograma.setId(Integer.parseInt(arg.get(0)));
        dcronograma.setFechaInicio(arg.get(1));
        dcronograma.setFechaFin(arg.get(2));
        dcronograma.setHoraInicio(arg.get(3));
        dcronograma.setHoraFin(arg.get(4));
        dcronograma.setActividadId(Integer.parseInt(arg.get(5)));
        dcronograma.modificar();
    }
    
    public boolean existe(String id){
        dcronograma = new DCronograma();
        int idE=Integer.parseInt(id);
        dcronograma.setId(idE);
        return dcronograma.existeID();
    }
}
