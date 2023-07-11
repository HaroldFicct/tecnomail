package Datos;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DMateria extends Datos {

    private String nombre;
    private String sigla;

    public DMateria() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public LinkedList<String> listar(Map<String, Object> filtros) throws Exception {
       
            this.conexion.connect();
            String filtroId = "";
            String filtroNombre = "";
            String filtroSigla = "";
            if(filtros != null){
                if(filtros.containsKey("id")){
                	int id = Integer.valueOf((String) filtros.get("id"));
                    filtroId = " AND CAST(m.id as VARCHAR) LIKE '%"+ id +"%' ";
                }
                if(filtros.containsKey("nombre")){
                    filtroNombre = (String) filtros.get("nombre");
                }
                if(filtros.containsKey("sigla")){
                    filtroSigla = (String) filtros.get("sigla");
                }
            }
            String query = "SELECT m.id, m.nombre, m.sigla FROM materia m WHERE (m.estado = 1) " + filtroId + " AND LOWER(m.nombre) LIKE LOWER('%" + filtroNombre + "%') AND LOWER(m.sigla) LIKE LOWER('%" + filtroSigla + "%');";
            ResultSet res = this.conexion.select(query);
            LinkedList<String> materias = new LinkedList<>();
            while(res.next()){
                materias.add(res.getString("id"));
                materias.add(res.getString("nombre"));
                materias.add(res.getString("sigla"));
            }
            this.conexion.close();
            return materias;

    }

    @Override
    public int insertar() throws Exception {
            this.conexion.connect();
            String query = "INSERT INTO materia (nombre, sigla) VALUES ('" + getNombre() + "', '" + getSigla() + "');";
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
            this.conexion.connect();
            String query = "UPDATE materia SET estado = 0 WHERE id = " + getId() + " AND estado = 1;";
            this.conexion.delete(query);
            this.conexion.close();  
    }

    @Override
    public LinkedList<String> mostrar() throws Exception {
			this.conexion.connect();
			LinkedList<String> materia = new LinkedList<>();
            String sql = "SELECT * FROM materia WHERE estado = 1 AND id = " + getId() + ";";
            ResultSet result = this.conexion.select(sql);
            if(result.next()) {
                materia.add(result.getString("id"));
				materia.add(result.getString("nombre"));
				materia.add(result.getString("sigla"));
            }else{
				materia = null;
			}
            this.conexion.close();
			return materia;
    }

    public Map<String, Object> obtenerPorSigla(String sigla) throws Exception {
			this.conexion.connect();
			Map<String, Object> materia = new HashMap<String, Object>();
            String sql = "SELECT * FROM materia WHERE estado = 1 AND sigla = '" + sigla + "';";
            ResultSet result = this.conexion.select(sql);
            if(result.next()) {
                materia.put("id", result.getInt("id"));
				materia.put("nombre", result.getString("nombre"));
				materia.put("sigla", result.getString("sigla"));
            }else{
				materia = null;
			}
            this.conexion.close();
			return materia;
    }
    
}
