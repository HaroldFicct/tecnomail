package Datos;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Map;

public class DPostulante extends Datos {
	
	private String nombres;
	private String apellidos;
	private String nro_registro;
	private String carnet_identidad;
	private int carrera_id;
	private int periodo_id;

	public DPostulante() {
		super();
	}

	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getNroRegistro() {
		return nro_registro;
	}


	public void setNroRegistro(String nro_registro) {
		this.nro_registro = nro_registro;
	}


	public String getCarnetIdentidad() {
		return carnet_identidad;
	}


	public void setCarnetIdentidad(String carnet_identidad) {
		this.carnet_identidad = carnet_identidad;
	}


	public int getCarreraId() {
		return carrera_id;
	}


	public void setCarreraId(int carrera_id) {
		this.carrera_id = carrera_id;
	}


	public int getPeriodoId() {
		return periodo_id;
	}


	public void setPeriodoId(int periodo_id) {
		this.periodo_id = periodo_id;
	}

	@Override
	public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
			this.conexion.connect();
			String filtroNombres = "";
            String filtroPeriodo = "";
            String filtroCarrera = "";
            if(filtros != null){              
                if(filtros.containsKey("nombre")){
                	filtroNombres = (String) filtros.get("nombre");
                }
                if(filtros.containsKey("periodo")){
                	filtroPeriodo = (String) filtros.get("periodo");
                }
                if(filtros.containsKey("carrera")){
                	filtroCarrera = (String) filtros.get("carrera");
                }
            }
			LinkedList<String> lista = new LinkedList<>();
			String sql = "SELECT  p.id, CONCAT(p.nombres, ' ', p.apellidos) as nombres, p.nro_registro as registro, per.nombre as periodo, car.nombre as carrera, COALESCE((SELECT string_agg(m.sigla, ', ')  FROM postulante_materia pm JOIN materia m ON m.id = pm.materia_id AND m.estado = 1 WHERE pm.estado = 1 AND pm.postulante_id = p.id GROUP BY postulante_id), 'SIN MATERIAS') as materias FROM postulante p JOIN periodo per ON per.id = p.periodo_id AND per.estado = 1 JOIN carrera car ON car.id = p.carrera_id AND car.estado = 1 WHERE p.estado = 1 AND LOWER(p.nombres) LIKE LOWER('%"+filtroNombres+"%') AND per.nombre LIKE '%"+filtroPeriodo+"%' AND LOWER(car.nombre) LIKE LOWER('%"+filtroCarrera+"%')";
            ResultSet result = this.conexion.select(sql);
			while (result.next()) {
				lista.add(result.getString("id"));
				lista.add(result.getString("nombres"));
				lista.add(result.getString("registro"));
				lista.add(result.getString("periodo"));
				lista.add(result.getString("carrera"));
				lista.add(result.getString("materias"));
			}
			this.conexion.close();
			return lista;
			
	}

	@Override
	public int insertar() throws Exception {
			this.conexion.connect();
			String query = "INSERT INTO postulante (nombres, apellidos, nro_registro, carnet_identidad, carrera_id, periodo_id) VALUES ('" + getNombres() + "', '" + getApellidos() + "', '" + getNroRegistro() + "', '" + getCarnetIdentidad() + "', " + getCarreraId() + ", " + getPeriodoId() + ");";
			int id = this.conexion.insert(query);
			this.conexion.close();
			return id;
	}

	@Override
	public void modificar() throws Exception {
			this.conexion.connect();
			String query = "UPDATE postulante SET nombres = '" + getNombres() + "', apellidos = '" + getApellidos() + "', nro_registro = '" + getNroRegistro() + "', carnet_identidad = '" + getCarnetIdentidad() + "', carrera_id = " + getCarreraId() + ", periodo_id = " + getPeriodoId() + " WHERE id = " + getId() + ";";
			this.conexion.update(query);
			this.conexion.close();
	}

	@Override
	public void eliminar() throws Exception {
			this.conexion.connect();
            String sql = "UPDATE postulante SET estado = 0 WHERE id = " + getId() + ";";
            this.conexion.delete(sql);
            this.conexion.close();
	}

	@Override
	public LinkedList<String> mostrar() throws Exception {
			this.conexion.connect();
			LinkedList<String> lista = new LinkedList<>();
            String sql = "SELECT * FROM postulante WHERE id = " + getId() + " AND estado = 1;";
            ResultSet result = this.conexion.select(sql);
            if(result.next()) {
            	lista.add(result.getString("id"));
				lista.add(result.getString("nombres"));
				lista.add(result.getString("periodo"));
				lista.add(result.getString("carrera"));
				lista.add(result.getString("materias"));
            }else{
            	lista = null;
			}
            this.conexion.close();
			return lista;
	}
	
	public LinkedList<String> mostrarPorRegistro() throws Exception {
		this.conexion.connect();
		LinkedList<String> lista = new LinkedList<>();
        String sql = "SELECT  p.id,  CONCAT(p.nombres, ' ', p.apellidos) as nombres, p.nro_registro as registro, per.nombre as periodo, car.nombre as carrera, COALESCE((SELECT string_agg(m.sigla, ', ')  FROM postulante_materia pm JOIN materia m ON m.id = pm.materia_id AND m.estado = 1 WHERE pm.estado = 1 AND pm.postulante_id = p.id GROUP BY postulante_id), 'SIN MATERIAS') as materias FROM postulante p JOIN periodo per ON per.id = p.periodo_id AND per.estado = 1 JOIN carrera car ON car.id = p.carrera_id AND car.estado = 1 WHERE p.estado = 1 AND p.nro_registro = '"+getNroRegistro()+"' LIMIT 1";
        ResultSet result = this.conexion.select(sql);
        if(result.next()) {
        	lista.add(result.getString("id"));
			lista.add(result.getString("nombres"));
			lista.add(result.getString("registro"));
			lista.add(result.getString("periodo"));
			lista.add(result.getString("carrera"));
			lista.add(result.getString("materias"));
        }else{
        	lista = null;
		}
        this.conexion.close();
		return lista;
	}

}
