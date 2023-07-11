package Datos;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DExamen extends Datos {
	
	private int materiaId;
	private int actividadId;

	public DExamen() {
		super();
	}

	@Override
	public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
		this.conexion.connect();
		String filtroSigla = "";
        String filtroPeriodo = "";
        String filtroDocente = "";
        if(filtros != null){              
            if(filtros.containsKey("sigla")){
            	filtroSigla = (String) filtros.get("sigla");
            }
            if(filtros.containsKey("periodo")){
            	filtroPeriodo = (String) filtros.get("periodo");
            }
            if(filtros.containsKey("docente")){
            	filtroDocente = (String) filtros.get("docente");
            }
        }
		LinkedList<String> lista = new LinkedList<>();
		String sql = "SELECT  e.id, m.nombre as materia, m.sigla,  per.nombre as gestion,  CONCAT(doc.nombres, ' ', doc.apellidos) as docente, cr.fecha_inicio as fecha, (SELECT COUNT(*) FROM examen_postulante ep WHERE e.id = ep.examen_id) as cantidad_tomados, (SELECT COUNT(*) FROM examen_postulante ep WHERE e.id = ep.examen_id AND ep.nota >= 51) as aprobados FROM examen e JOIN actividad a ON a.id = e.actividad_id AND a.estado = 1 JOIN materia m ON m.id = e.materia_id AND m.estado = 1  JOIN periodo per ON per.id = a.periodo_id AND per.estado = 1 JOIN users doc ON doc.id = a.usuario_id AND doc.estado = 1 JOIN cronograma cr ON a.id = cr.actividad_id AND cr.estado = 1 WHERE e.estado = 1 AND LOWER(m.sigla) LIKE LOWER('%"+filtroSigla+"%') AND LOWER(per.nombre) LIKE LOWER('%"+filtroPeriodo+"%') AND LOWER(CONCAT(doc.nombres, ' ', doc.apellidos)) LIKE LOWER('%"+filtroDocente+"%') ORDER BY per.id";
        ResultSet result = this.conexion.select(sql);
		while (result.next()) {
			lista.add(result.getString("id"));
			lista.add(result.getString("materia"));
			lista.add(result.getString("sigla"));
			lista.add(result.getString("gestion"));
			lista.add(result.getString("docente"));
			lista.add(result.getString("fecha"));
			lista.add(result.getString("cantidad_tomados"));
			lista.add(result.getString("aprobados"));
		}
		this.conexion.close();
		return lista;
	}
	
	public LinkedList<String> examenesPorRegistro(String registro) throws Exception {
		this.conexion.connect();
		LinkedList<String> lista = new LinkedList<>();
		String sql = "SELECT  per.nombre as gestion, m.nombre as materia, ep.nota, CONCAT(doc.nombres, ' ', doc.apellidos) as tomado_por, cr.fecha_inicio as fecha FROM examen_postulante ep JOIN postulante_materia pm ON pm.id = ep.postulante_materia_id AND pm.estado = 1 JOIN postulante p ON p.id = pm.postulante_id AND p.estado = 1 JOIN examen e ON e.id = ep.examen_id AND e.estado = 1 JOIN actividad a ON a.id = e.actividad_id AND a.estado = 1 JOIN periodo per ON per.id = a.periodo_id AND per.estado = 1 JOIN materia m ON m.id = e.materia_id AND m.estado = 1 JOIN users doc ON doc.id = a.usuario_id AND doc.estado = 1 JOIN cronograma cr ON a.id = cr.actividad_id AND cr.estado = 1 WHERE ep.estado = 1 AND p.nro_registro = '"+registro+"'";
        ResultSet result = this.conexion.select(sql);
		while (result.next()) {
			lista.add(result.getString("gestion"));
			lista.add(result.getString("materia"));
			lista.add(result.getString("nota"));
			lista.add(result.getString("tomado_por"));
			lista.add(result.getString("fecha"));
		}
		this.conexion.close();
		return lista;
	}

	@Override
	public int insertar() throws Exception {
		this.conexion.connect();
		String query = "INSERT INTO examen (materia_id, actividad_id) VALUES (" + getMateriaId() + ", " + getActividadId() + ");";
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
	public List<String> mostrar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(int materiaId) {
		this.materiaId = materiaId;
	}

	public int getActividadId() {
		return actividadId;
	}

	public void setActividadId(int actividadId) {
		this.actividadId = actividadId;
	}

}
