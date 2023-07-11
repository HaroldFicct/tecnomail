package Negocio;
import Datos.DUsuario;
import java.util.LinkedList;

public class NUsuario {
    public DUsuario dusuario;
    public NUsuario(){}
    
    public void insertar(LinkedList<String> arg) {
        dusuario = new DUsuario();
        dusuario.setNombre(arg.get(0));
        dusuario.setApellido(arg.get(1));
        dusuario.setGenero(arg.get(2).charAt(0));
        dusuario.setFechaDeNacimiento(arg.get(3));
        dusuario.setFoto(arg.get(4));
        dusuario.setCarnetDeIdentidad(arg.get(5));
        dusuario.setDireccionDeVivienda(arg.get(6));
        dusuario.setRolId(Integer.parseInt(arg.get(7)));
        dusuario.setEmail(arg.get(8));
        dusuario.setPassword(arg.get(9));
        dusuario.insertar();
    }

    public void modificar(LinkedList<String> arg) {
        dusuario = new DUsuario();
        dusuario.setId(Integer.parseInt(arg.get(0)));
        dusuario.setNombre(arg.get(1));
        dusuario.setApellido(arg.get(2));
        dusuario.setGenero(arg.get(3).charAt(0));
        dusuario.setFechaDeNacimiento(arg.get(4));
        dusuario.setFoto(arg.get(5));
        dusuario.setCarnetDeIdentidad(arg.get(6));
        dusuario.setDireccionDeVivienda(arg.get(7));
        dusuario.setRolId(Integer.parseInt(arg.get(8)));
        dusuario.setEmail(arg.get(9));
        dusuario.setPassword(arg.get(10));
        dusuario.modificar();
    }

    public LinkedList<String> listar() {
        dusuario = new DUsuario();
        return dusuario.listar();
    }
    
    public LinkedList<String> listar_por_rol_id(int rol_id) {
        dusuario = new DUsuario();
        return dusuario.listar_por_rol_id(rol_id);
    }
    
    public LinkedList<String> listar_por_rol_nombre(String rol_nombre) {
        dusuario = new DUsuario();
        return dusuario.listar_por_rol_nombre(rol_nombre);
    }

    public void eliminar(int id) {
        dusuario = new DUsuario();
        dusuario.setId(id);
        dusuario.eliminar();
    }

    public LinkedList<String> mostrar(int id) {
        dusuario = new DUsuario();
        dusuario.setId(id);
        return dusuario.mostrarID();
    }
    
    public LinkedList<String> buscar_usuario_nombre(String nombre) {
        dusuario = new DUsuario();
        return dusuario.buscar_usuario_nombre(nombre);
    }
    public LinkedList<String> buscar_usuario_apellido(String apellido) {
        dusuario = new DUsuario();
        return dusuario.buscar_usuario_apellido(apellido);
    }
    
    public boolean existe(String id){
        dusuario = new DUsuario();
        int idE=Integer.parseInt(id);
        dusuario.setId(idE);
        return dusuario.existeID();
    }
}
