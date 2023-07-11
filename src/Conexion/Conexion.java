package Conexion;

import Negocio.NPeriodo;
import Negocio.NUsuario;
import Negocio.NRol;
import Negocio.NCronograma;
import Negocio.NActividad;
import Negocio.NCargaHorariaGrupo;
import Conexion.Validador;

import java.util.LinkedList;

public class Conexion {
    public static void main(String[] args) {
        /*
        NPeriodo periodos = new NPeriodo();
        
        NUsuario usuarios = new NUsuario();
        NRol rol = new NRol();
        
        NCronograma cronograma = new NCronograma();
        NActividad actividad = new NActividad();
        NCargaHorariaGrupo carga_horaria_grupo = new NCargaHorariaGrupo();
        
        
        
        LinkedList<String> datos = new LinkedList<>();
        
        System.out.println("======================================");
        System.out.println(actividad.listar());
        System.out.println("======================================");*/
        
        Validador val = new Validador();
        System.err.println(val.isHoraMinSec("00:00:00"));
        System.err.println(val.isHoraMinSec("50:01:01"));
        System.err.println("=====");
        System.err.println(val.isHoraMinSec("00:00"));
        System.err.println("=====");
        System.err.println(val.isHoraMinSec("sas:as:as"));
        System.err.println("=====");
        System.err.println(val.isHoraMinSec("asasas"));
        
            
        //System.out.println(actividad.listar());
        /*listar*/
        //System.out.println(rol.listar());
        //System.out.println(periodos.listar());
        //System.out.println(usuarios.listar());
        /*show*/
        //System.out.println(periodos.mostrar(1));
        /*insertar*/
        /*LinkedList<String> datos = new LinkedList<>();
        datos.add("2023-1");
        datos.add("1");
        datos.add("2023-11-01");
        periodos.insertar(datos);*/
        /*LinkedList<String> datos = new LinkedList<>();
        datos.add("Erick");
        datos.add("Mercado");
        datos.add("M");
        datos.add("1998-01-16");
        datos.add("-");
        datos.add("-");
        datos.add("2");
        datos.add("1@gmail.com");
        datos.add("123123");
        usuarios.insertar(datos);*/

        /*actualizar*/
        /*LinkedList<String> datos = new LinkedList<>();
        datos.add("3");
        datos.add("2023-2");
        datos.add("2");
        datos.add("2023-11-01");
        periodos.modificar(datos);*/
        /*eliminar*/
        //periodos.eliminar(4);
        /* Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, "val");
        map.put(2, 4);
        System.out.println(map.get(4)); */
    }
}
