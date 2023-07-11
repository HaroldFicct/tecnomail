package Negocio;

import java.util.LinkedList;
import java.util.Map;

import Datos.DMateria;

public class NMateria extends Negocio {

    protected DMateria dMateria;

    public NMateria() {
        super();
        this.dMateria = new DMateria();
    }

    // LISMAT:*:
    //       :nombre=Estructuras Disc:sigla=INF:
    @Override
    public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
        return this.dMateria.listar(filtros);
    }

    // REGMAT:nombre=Estructuras Discretas:sigla=INF119:
    @Override
    public void insertar(Map<String, Object> datos) throws Exception {
        this.dMateria.setNombre((String) datos.get("nombre"));
        this.dMateria.setSigla((String) datos.get("sigla"));
        this.dMateria.insertar();
    }

    @Override
    public void modificar(int id, Map<String, Object> datos) throws Exception {
        // TODO Auto-generated method stub
        
    }

    // DELMAT:id:
    @Override
    public void eliminar(int id) throws Exception {
        this.dMateria.setId(id);
        this.dMateria.eliminar();        
    }

    // VERMAT:id:
    @Override
    public LinkedList<String> mostrar(int id) throws Exception {
        this.dMateria.setId(id);
        return this.dMateria.mostrar();
    }
    
}
