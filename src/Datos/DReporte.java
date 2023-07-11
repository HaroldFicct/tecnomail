/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author DELL
 */
public class DReporte {
    private int id;
    private String nombre;
    private String semestre;
    private String Postulante;
    private String Materia;
    private int cantidad;
    
    public DReporte() {
        this.id = 0;
        this.nombre = "";
        this.semestre = "";
        this.Postulante="";
        this.Materia="";
        this.cantidad = 0;
    }
    
    public LinkedList<String> ReporteSemestrePos(String fechaIni, String fechaFin){
        LinkedList<String> consulta= new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                if(!(fechaIni.isEmpty()) && !(fechaIni.isEmpty()) ){
                    String sql = "select periodo.id as id, periodo.nombre as nombre, materia.nombre as Materia, periodo.semestre as semestre, COUNT(examen_postulante.id) as cantidad "
                            + " from periodo"
                            + " JOIN actividad ON actividad.periodo_id=periodo.id "
                            + " JOIN examen ON examen.actividad_id=actividad.id"
                            + " JOIN materia ON materia.id=examen.materia_id "
                            + " JOIN examen_postulante ON examen_postulante.examen_id=examen.id"
                            + " where estado=1 and año between"+fechaIni+" and "+fechaFin+" ORDER BY periodo.fecha;";
                    ResultSet result = connection.select(sql);
                    while (result.next()) {
                    consulta.add(Integer.toString(result.getInt("id")));
                    consulta.add(result.getString("nombre"));
                    consulta.add(result.getString("semestre"));
                    consulta.add(result.getString("Materia"));
                    consulta.add(Integer.toString(result.getInt("cantidad")));
                    }  
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return consulta;
    }
    
    public LinkedList<String> ReporteAuxiliarCarga(String todos){
        LinkedList<String> consulta= new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                if(todos.equals("*") ){
                    String sql = "select usuario.nombre as auxiliar, COUNT(carga_horaria_grupo.id) as cantidad, SUM(carga_horaria.nro_horas) as horas "
                            + " from auxiliar"
                            + " JOIN usuario ON usuario.id=auxiliar.usuario_id"
                            + " JOIN carga_horaria ON carga_horaria.auxiliar_id=auxiliar.id "
                            + " JOIN carga_horaria_grupo ON carga_horaria_grupo.carga_horaria_id=cargar_horaria.id"
                            + " GROUP BY auxiliar.id ORDER BY auxiliar.id;";
                    ResultSet result = connection.select(sql);
                    while (result.next()) {
                    consulta.add(result.getString("auxiliar"));
                    consulta.add(Integer.toString(result.getInt("cantidad")));
                    consulta.add(Integer.toString(result.getInt("horas")));
                    }  
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return consulta;
    }
    
    public LinkedList<String> ReporteMateriaGrupo(String todos){
        LinkedList<String> consulta= new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                if(todos.equals("*") ){
                    String sql = "select materia.nombre as materia, grupo.sigla as grupo, COUNT(postulante_materia.id) as cantidad "
                            + " from materia"
                            + " JOIN grupo ON grupo.materia_id=materia.id"
                            + " JOIN postulante_materia ON postulante_materia.materia_id=materia.id"
                            + " ORDER BY materia.id,grupo.id;";
                    ResultSet result = connection.select(sql);
                    while (result.next()) {
                    consulta.add(result.getString("materia"));
                    consulta.add(result.getString("grupo"));
                    consulta.add(Integer.toString(result.getInt("cantidad")));
                    
                    }  
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return consulta;
    }
    
    public LinkedList<String> ReporteMateriaAprobados(String todos){
        LinkedList<String> consulta= new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                if(todos.equals("*") ){
                    String sql = "select materia.nombre as materia, COUNT(examen_postulante.id), "
                            + " (ROUND(SUM(IF(examen_postulante.nota>60,1,0))/SUM(examen_postulante.id)),2) as aprobados "
                            + " from materia"
                            + " JOIN examen ON examen.materia.id=materia.id"
                            + " JOIN examen_postulante ON examen_postulante.examen_id=examen.id"
                            + " ORDER BY materia.id;";
                    ResultSet result = connection.select(sql);
                    while (result.next()) {
                    consulta.add(result.getString("materia"));
                    consulta.add(Integer.toString(result.getInt("cantidad")));
                    consulta.add(Float.toString(result.getFloat("aprobados")));
                    }  
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return consulta;
    }
}
