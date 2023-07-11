package Negocio;

import java.util.List;
import java.util.Map;

public abstract class Negocio {
    
    public abstract List<String> listar(Map<String, Object> filtros) throws Exception;
    public abstract void insertar(Map<String, Object> datos) throws Exception; 
    public abstract void modificar(int id, Map<String, Object> datos) throws Exception; 
    public abstract void eliminar(int id) throws Exception; 
    public abstract List<String> mostrar(int id) throws Exception; 
    
}
