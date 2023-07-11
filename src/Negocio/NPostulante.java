package Negocio;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Datos.DMateria;
import Datos.DPostulante;
import Datos.DPostulanteMateria;

public class NPostulante extends Negocio {
    protected DPostulante dPostulante;

    public NPostulante() {
        super();
        this.dPostulante = new DPostulante();
    }

    @Override
    public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
        return this.dPostulante.listar(filtros);
    }

    // REGPOS:nombres=Rodrigo:apellidos=Duarte_Barrientos:registro=218017243:ci=8616461:carrera=1:periodo=1:materias=INF110,INF119,INF220
    @Override
    public void insertar(Map<String, Object> datos) throws Exception {
        this.dPostulante.setNombres((String) datos.get("nombres"));
        this.dPostulante.setApellidos((String) datos.get("apellidos"));
        this.dPostulante.setNroRegistro((String) datos.get("registro"));
        this.dPostulante.setCarnetIdentidad((String) datos.get("ci"));
        this.dPostulante.setCarreraId(Integer.valueOf((String) datos.get("carrera")));
        this.dPostulante.setPeriodoId(Integer.valueOf((String) datos.get("periodo")));
        int nuevoId = this.dPostulante.insertar();
        this.dPostulante.setId(nuevoId);

        //materias a postular
        DMateria dMateria = new DMateria();
        DPostulanteMateria dPostulanteMateria = new DPostulanteMateria();
        String materias = (String) datos.get("materias");
        List<String> siglas = Arrays.asList(materias.split(","));
        siglas.forEach((sigla) -> {
            Map<String, Object> materia;
			try {
				materia = dMateria.obtenerPorSigla(sigla.trim());
			} catch (Exception e) {
				materia = null;
			}
            if(materia != null){
                dPostulanteMateria.setMateriaId((int) materia.get("id"));
                dPostulanteMateria.setPostulanteId(nuevoId);
                try {
					dPostulanteMateria.insertar();
				} catch (Exception e) {
					
				}
            }
        });
    }

    @Override
    public void modificar(int id, Map<String, Object> datos) throws Exception {
        this.dPostulante.setId(id);
        this.dPostulante.setNombres((String) datos.get("nombres"));
        this.dPostulante.setApellidos((String) datos.get("apellidos"));
        this.dPostulante.setNroRegistro((String) datos.get("nro_registro"));
        this.dPostulante.setCarnetIdentidad((String) datos.get("carnet_identidad"));
        this.dPostulante.setCarreraId((int) datos.get("carrera_id"));
        this.dPostulante.setPeriodoId((int) datos.get("periodo_id"));
        this.dPostulante.modificar();
    }

    @Override
    public void eliminar(int id) throws Exception {
        this.dPostulante.setId(id);
        this.dPostulante.eliminar();
    }

    @Override
    public LinkedList<String> mostrar(int id) throws Exception {
        this.dPostulante.setId(id);
        return this.dPostulante.mostrar();
    }
    
    public LinkedList<String> mostrarPorRegistro(String registro) throws Exception {
    	this.dPostulante.setNroRegistro(registro);
    	return this.dPostulante.mostrarPorRegistro();
    }
    
}
