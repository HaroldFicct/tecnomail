package Negocio;
import Datos.DActividad;
import java.util.LinkedList;

public class NActividad {
    public DActividad dactividad;
    public NActividad(){}
    
    public LinkedList<String> listar() {
        dactividad = new DActividad();
        return dactividad.listar();
    }
    public LinkedList<String> listar_por_periodo_id(int periodo_id) {
        dactividad = new DActividad();
        return dactividad.listar_por_periodo_id(periodo_id);
    }
    public LinkedList<String> listar_por_periodo_nombre(String nombre_periodo) {
        dactividad = new DActividad();
        return dactividad.listar_por_periodo_nombre(nombre_periodo);
    }
    public LinkedList<String> listar_por_actividad_por_nombres_creador(String nombres_creador) {
        dactividad = new DActividad();
        return dactividad.listar_por_actividad_por_nombres_creador(nombres_creador);
    }
    public LinkedList<String> listar_por_actividad_por_apellidos_creador(String apellidos_creador) {
        dactividad = new DActividad();
        return dactividad.listar_por_actividad_por_apellidos_creador(apellidos_creador);
    }
    public LinkedList<String> listar_por_id_del_creador(int id_creador) {
        dactividad = new DActividad();
        return dactividad.listar_por_id_del_creador(id_creador);
    }

    public void eliminar(int id) {
        dactividad = new DActividad();
        dactividad.setId(id);
        dactividad.eliminar();
    }

    public LinkedList<String> mostrar(int id) {
        dactividad = new DActividad();
        dactividad.setId(id);
        return dactividad.mostrarID();
    }
    
    public void insertar(LinkedList<String> arg) {
        dactividad = new DActividad();
        dactividad.setNombre(arg.get(0));
        dactividad.setDescripcion(arg.get(1));
        dactividad.setActor(arg.get(2));
        dactividad.setUsuarioId(Integer.parseInt(arg.get(3)));
        dactividad.setPeriodoId(Integer.parseInt(arg.get(4)));
        dactividad.insertar();
    }

    public void modificar(LinkedList<String> arg) {
        dactividad = new DActividad();
        dactividad.setId(Integer.parseInt(arg.get(0)));
        dactividad.setNombre(arg.get(0));
        dactividad.setDescripcion(arg.get(1));
        dactividad.setActor(arg.get(2));
        dactividad.setUsuarioId(Integer.parseInt(arg.get(3)));
        dactividad.setPeriodoId(Integer.parseInt(arg.get(4)));
        dactividad.modificar();
    }
    public boolean existe(String id){
        dactividad = new DActividad();
        int idE=Integer.parseInt(id);
        dactividad.setId(idE);
        return dactividad.existeID();
    }
    
    public int GetLastID(){
        dactividad = new DActividad();
        return dactividad.GetLastID();
    }
    
}
