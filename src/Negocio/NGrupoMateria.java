package Negocio;

import java.util.LinkedList;
import java.util.Map;
import Datos.DGrupoMateria;
import Datos.DMateria;

public class NGrupoMateria extends Negocio{

    protected DGrupoMateria dGrupoMateria;

    public NGrupoMateria() {
        super();
        this.dGrupoMateria = new DGrupoMateria();
    }

    // LISMATGRUP:...:
    @Override
    public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
        return this.dGrupoMateria.listar(filtros);
    }

    // REGMATGRUP:materia=INF119:grupo=SA:horario=19.45-21.30 lun-mie-vi:docente=3:carrera=1:
    @Override
    public void insertar(Map<String, Object> datos) throws Exception {
        this.dGrupoMateria.setSigla((String) datos.get("grupo"));
        this.dGrupoMateria.setHorario((String) datos.get("horario"));
        this.dGrupoMateria.setDocenteId(Integer.valueOf((String) datos.get("docente")));
        this.dGrupoMateria.setCarreraId(Integer.valueOf((String) datos.get("carrera")));
        String materia = (String) datos.get("materia");
        DMateria dMateria = new DMateria();
        Map<String, Object> materiaPorSigla = dMateria.obtenerPorSigla(materia);
        if(materiaPorSigla == null) {
        	throw new Exception("La materia con la sigla no existe");
        }
        int materiaId = (int) materiaPorSigla.get("id");
        this.dGrupoMateria.setMateriaId(materiaId);
        this.dGrupoMateria.insertar();
    }

    @Override
    public void modificar(int id, Map<String, Object> datos) throws Exception {
        // TODO Auto-generated method stub
        
    }

    // DELMATGRUP:id:
    @Override
    public void eliminar(int id) throws Exception {
        this.dGrupoMateria.setId(id);
        this.dGrupoMateria.eliminar();
    }

    // VERMATGRUP:id:
    @Override
    public LinkedList<String> mostrar(int id) throws Exception {
        this.dGrupoMateria.setId(id);
        return this.dGrupoMateria.mostrar();
    }
    
}
