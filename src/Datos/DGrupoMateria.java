package Datos;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DGrupoMateria extends Datos {

    protected String sigla;
    protected String horario;
    protected int docenteId;
    protected int auxiliarId;
    protected int carreraId;
    protected int materiaId;

    public static String[] LLAVES = {"id", "sigla", "horario", "docente", "auxiliar", "carrera", "materia"};

    public DGrupoMateria(){
        super();	
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getAuxiliarId() {
        return auxiliarId;
    }

    public void setAuxiliarId(int auxiliarId) {
        this.auxiliarId = auxiliarId;
    }

    public int getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(int carreraId) {
        this.carreraId = carreraId;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    @Override
    public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
            this.conexion.connect();
            String filtroMateria = "";
            String filtroGrupo = "";
            String filtroSigla = "";
            String filtroCarrera = "";
            if(filtros != null){              
                if(filtros.containsKey("materia")){
                    filtroMateria = (String) filtros.get("materia");
                }
                if(filtros.containsKey("grupo")){
                    filtroGrupo = (String) filtros.get("grupo");
                }
                if(filtros.containsKey("sigla")){
                	filtroSigla = (String) filtros.get("sigla");
                }
                if(filtros.containsKey("carrera")){
                	filtroCarrera = (String) filtros.get("carrera");
                }
            }
            String query = "SELECT g.id as idgrupo, m.nombre as materia, m.sigla, g.sigla as grupo, g.horario, c.nombre as carrera, CONCAT(doc.nombres, ' ', doc.apellidos) as docente, COALESCE(aux.nombres, 'SIN AUXILIAR', CONCAT(aux.nombres, ' ', aux.apellidos)) as auxiliar FROM grupo g JOIN materia m ON m.id = g.materia_id AND m.estado = 1 JOIN users doc ON doc.id = g.docente_id AND doc.estado = 1 JOIN carrera c ON c.id = g.carrera_id AND c.estado = 1 LEFT JOIN users aux ON aux.id = g.auxiliar_id AND aux.estado = 1 WHERE (g.estado = 1) AND LOWER(m.nombre) LIKE LOWER('%"+filtroMateria+"%') AND LOWER(m.sigla) LIKE LOWER('%"+filtroSigla+"%') AND LOWER(g.sigla) LIKE LOWER('%"+filtroGrupo+"%') AND LOWER(c.nombre) LIKE LOWER('%"+filtroCarrera+"%') ORDER BY m.nombre;";
            ResultSet res = this.conexion.select(query);
            LinkedList<String> materias = new LinkedList<>();
            while(res.next()){
                materias.add(res.getString("idgrupo"));
                materias.add(res.getString("materia"));
                materias.add(res.getString("sigla"));
                materias.add(res.getString("grupo"));
                materias.add(res.getString("horario"));
                materias.add(res.getString("carrera"));
                materias.add(res.getString("docente"));
                materias.add(res.getString("auxiliar"));
            }
            this.conexion.close();
            return materias;
    }

    @Override
    public int insertar() throws Exception {
    	this.conexion.connect();
    	String query = "INSERT INTO grupo (sigla, horario, docente_id, carrera_id, materia_id) VALUES ('" + getSigla() + "', '" + getHorario() + "', " + getDocenteId() + ", "+ getCarreraId() + ", " + getMateriaId() + ");";
    	int id = this.conexion.insert(query);
    	this.conexion.close();
    	return id;
    }

    @Override
    public void modificar() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void eliminar() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public LinkedList<String> mostrar() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public Map<Boolean, String> validarLlaves(List<String> llaves) {
        Map<Boolean, String> res = new HashMap<Boolean, String>();
        List<String> validas = Arrays.asList(DGrupoMateria.LLAVES);
        for(String valida: validas) {
            if(!llaves.contains(valida)){
                res.put(true, "Falta incluir la llave " + valida);
            }
        }
        res.put(Boolean.TRUE, "Llaves Validas");
        return res;
    }
    
}
