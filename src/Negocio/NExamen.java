package Negocio;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Datos.DActividad;
import Datos.DCronograma;
import Datos.DExamen;
import Datos.DMateria;

public class NExamen extends Negocio {
	
	private DExamen dExamen;

	public NExamen() {
		this.dExamen = new DExamen();
	}

	@Override
	public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
		return this.dExamen.listar(filtros);
	}
	
	public LinkedList<String> listarPorRegistro(String registro) throws Exception {
		return this.dExamen.examenesPorRegistro(registro);
	}

	@Override
	public void insertar(Map<String, Object> datos) throws Exception {
		DMateria dMateria = new DMateria();
		Map<String, Object> materia = dMateria.obtenerPorSigla((String) datos.get("materia"));
		if(materia == null) {
			throw new Exception("La materia con la sigla no existe");
		}
		DActividad dActividad = new DActividad();
		dActividad.setNombre("Examen de " + materia.get("sigla"));
		dActividad.setActor("Docente");
		dActividad.setDescripcion("Examen de Auxiliatura");
		dActividad.setPeriodoId(Integer.valueOf((String) datos.get("periodo")));
		dActividad.setUsuarioId(Integer.valueOf((String) datos.get("docente")));
		int actividadId = dActividad.insertar();
		if(actividadId == 0) {
			throw new Exception("Hubo un error al crear la actividad");
		}
		DCronograma dCronograma = new DCronograma();
		String[] fechaHora = ((String)datos.get("fecha")).split("-");
		String fecha = fechaHora[0].replace('.', '-');
		String hora = fechaHora[1].replace('.', ':');
		dCronograma.setActividadId(actividadId);
		dCronograma.setFechaInicio(fecha);
		dCronograma.setFechaFin(fecha);
		dCronograma.setHoraInicio(hora);
		dCronograma.insertar();
		
		this.dExamen.setActividadId(actividadId);
		this.dExamen.setMateriaId((int) materia.get("id"));
		this.dExamen.insertar();
	}

	@Override
	public void modificar(int id, Map<String, Object> datos) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(int id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> mostrar(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
