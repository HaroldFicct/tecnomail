package Negocio;
import Datos.DPeriodo;
import java.util.LinkedList;

public class NPeriodo {
    public DPeriodo dperiodo;
    public NPeriodo(){}
    
    public void insertar(LinkedList<String> arg) {
        dperiodo = new DPeriodo();
        dperiodo.setNombre(arg.get(0));
        dperiodo.setSemestre(arg.get(1));
        dperiodo.setAño(arg.get(2));
        dperiodo.insertar();
    }

    public void modificar(LinkedList<String> arg) {
        dperiodo = new DPeriodo();
        dperiodo.setId(Integer.parseInt(arg.get(0)));
        dperiodo.setNombre(arg.get(1));
        dperiodo.setSemestre(arg.get(2));
        dperiodo.setAño(arg.get(3));
        dperiodo.modificar();
    }

    public LinkedList<String> listar(String fechaIni, String fechaFin) {
        dperiodo = new DPeriodo();
        return dperiodo.listar(fechaIni,fechaFin);
    }

    public void eliminar(int id) {
        dperiodo = new DPeriodo();
        dperiodo.setId(id);
        dperiodo.eliminar();
    }

    public LinkedList<String> mostrar(int id) {
        dperiodo = new DPeriodo();
        dperiodo.setId(id);
        return dperiodo.mostrarID();
    }
    
    public boolean existe(String id){
        dperiodo = new DPeriodo();
        int idE=Integer.parseInt(id);
        dperiodo.setId(idE);
        return dperiodo.existeID();
    }
    
    public boolean existeFecha(String fecha,String semestre){
        dperiodo= new DPeriodo();
        dperiodo.setAño(fecha);
        dperiodo.setSemestre(semestre);
        return dperiodo.existeFecha();
    }
}
