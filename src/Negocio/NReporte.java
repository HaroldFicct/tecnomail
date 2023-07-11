/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;
import Datos.DReporte;
import java.util.LinkedList;
/**
 *
 * @author DELL
 */
public class NReporte {
    
    public DReporte dreporte;
    public NReporte(){}
     
    public LinkedList<String> ReporteSemestrePos(String fechaIni, String fechaFin) {
        dreporte = new DReporte();
        return dreporte.ReporteSemestrePos(fechaIni,fechaFin);
    }
    public LinkedList<String> ReporteAuxiliarCarga(String todos) {
        dreporte = new DReporte();
        return dreporte.ReporteAuxiliarCarga(todos);
    }
    
    public LinkedList<String> ReporteMateriaGrupo(String todos) {
        dreporte = new DReporte();
        return dreporte.ReporteMateriaGrupo(todos);
    }
    
    public LinkedList<String> ReporteMateriaAprobados(String todos) {
        dreporte = new DReporte();
        return dreporte.ReporteMateriaAprobados(todos);
    }
}
