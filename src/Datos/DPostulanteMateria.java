package Datos;

import java.util.LinkedList;
import java.util.Map;

public class DPostulanteMateria extends Datos {

    private int materiaId;
    private int postulanteId;

    public DPostulanteMateria() {
        super();
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public int getPostulanteId() {
        return postulanteId;
    }

    public void setPostulanteId(int postulanteId) {
        this.postulanteId = postulanteId;
    }

    @Override
    public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertar() throws Exception {
    	this.conexion.connect();
        String query = "INSERT INTO postulante_materia (materia_id, postulante_id) VALUES ('" + getMateriaId() + "', '" + getPostulanteId() + "');";
        int id = this.conexion.insert(query);
        this.conexion.close();
        return id;
    }

    @Override
    public void modificar() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void eliminar() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public LinkedList<String> mostrar() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
