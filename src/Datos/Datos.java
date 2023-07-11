package Datos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Datos {
	
	protected int id;
	protected int estado;
	protected String created_at;
	protected String updated_at;
	protected ConexionDb conexion;

	public Datos() {
		this.id = 0;
		this.estado = 1;
		this.created_at = "";
		this.updated_at = "";
		this.conexion = new ConexionDb();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdatedAt() {
		return updated_at;
	}

	public void setUpdatedAt(String updated_at) {
		this.updated_at = updated_at;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public ConexionDb getConexion() {
		return conexion;
	}

	public void setConexion(ConexionDb conexion) {
		this.conexion = conexion;
	}

	public abstract List<String> listar(Map<String, Object> filtros) throws Exception;
    public abstract int insertar() throws Exception; 
    public abstract void modificar() throws Exception; 
    public abstract void eliminar() throws Exception; 
    public abstract List<String> mostrar() throws Exception; 
    
    public static boolean parametrosValidos(Map<String, Object> parametros, String[] validos, boolean exacto) {
    	List<String> listaParams = new LinkedList<String>(parametros.keySet());
    	List<String> listaValidos = Arrays.asList(validos);
    	if(exacto) {
    		return listaValidos.containsAll(listaParams);
    	}
    	for(String param: listaParams) {
    		if(!listaValidos.contains(param)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public static Map<String, Object> parametrosMapeados(List<String> parametros) throws Exception {
    	Map<String, Object> filtros = new HashMap<String, Object>();
    	for(int i=1; i<parametros.size(); i++) {
			String[] splitted = parametros.get(i).split("=");
			filtros.put(splitted[0].trim(), splitted[1].trim());
		}
    	return filtros;
    }

}
