package Negocio;

import Datos.DAdministrador;
import java.util.LinkedList;

public class NAdministrador {

    public DAdministrador dAdministrador;

    public NAdministrador() {
    }

    public void insertar(LinkedList<String> arg) {
        dAdministrador = new DAdministrador();

        dAdministrador.setNombre(arg.get(0));
        dAdministrador.setTelefono(arg.get(1));
        dAdministrador.setCodigo(Integer.parseInt(arg.get(2)));
        dAdministrador.setContrasena(arg.get(3));
        dAdministrador.insertar();
    }

    public void modificar(LinkedList<String> arg) {
        dAdministrador = new DAdministrador();

        dAdministrador.setId(Integer.parseInt(arg.get(0)));
        dAdministrador.setNombre(arg.get(1));
        dAdministrador.setTelefono(arg.get(2));
        dAdministrador.setCodigo(Integer.parseInt(arg.get(3)));
        dAdministrador.setContrasena(arg.get(4));
        dAdministrador.modificar();
    }

    public LinkedList<String> listar() {
        dAdministrador = new DAdministrador();
        return dAdministrador.listar();
    }

    public void eliminar(int id) {
        dAdministrador = new DAdministrador();
        dAdministrador.setId(id);
        dAdministrador.eliminar();
    }

    public LinkedList<String> mostrarId(int id) {
        dAdministrador = new DAdministrador();
        dAdministrador.setId(id);
        return dAdministrador.mostrarID();
    }

    public boolean existe(String id) {
        dAdministrador = new DAdministrador();
        int idE = Integer.parseInt(id);
        dAdministrador.setId(idE);
        return dAdministrador.existeID();
    }
}
