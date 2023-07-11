package Negocio;

import Datos.DLector;
import java.util.LinkedList;

public class NLector {
    public DLector dlector;
    public NLector(){}
    
    public void insertar(LinkedList<String> arg){
        dlector= new DLector();
    
        dlector.setNombre(arg.get(0));
        dlector.setCarnet(arg.get(1));
        dlector.setCorreo(arg.get(2));
        dlector.setTelefono(arg.get(3));
        dlector.insertar();
    }
    
    public void modificar(LinkedList<String> arg){
        dlector = new DLector();
        
        dlector.setId(Integer.parseInt(arg.get(0)));
        dlector.setNombre(arg.get(1));
        dlector.setCorreo(arg.get(2));
        dlector.setCarnet(arg.get(3));
        dlector.setCorreo(arg.get(4));
        dlector.setTelefono(arg.get(5));
        dlector.modificar();
    }
    
    public LinkedList<String> listar(){
        dlector = new DLector();
        return dlector.listar();
    }
    
    public void eliminar(int id) {
        dlector = new DLector();
        dlector.setId(id);
        dlector.eliminar();
    }
    
    public LinkedList<String> mostrarId(int id) {
        dlector = new DLector();
        dlector.setId(id);
        return dlector.mostrarID();
    }
        
    public boolean existe(String id){
        dlector = new DLector();
        int idE=Integer.parseInt(id);
        dlector.setId(idE);
        return dlector.existeID();
    }
    
}
