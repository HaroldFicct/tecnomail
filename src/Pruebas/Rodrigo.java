package Pruebas;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import Datos.DGrupoMateria;
import Datos.DMateria;
import Datos.DPostulante;
import Negocio.NGrupoMateria;
import Negocio.NMateria;
import Negocio.NPeriodo;
import Negocio.NPostulante;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

public class Rodrigo {

	public static void main(String[] args) {
		Map<String, Object> datos = new HashMap<String, Object>();
        //NPeriodo periodos = new NPeriodo();
        /*listar*/
        //System.out.println(periodos.listar());
		
		// postulantes
        NPostulante np = new NPostulante();
        //listar
        
//        try {
//        	datos.put("nombre", "Magdalena Barreto Segundo");
//			System.out.println(np.listar(datos).get(4));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        datos.put("nombres", "Raul Boris");
//        datos.put("apellidos", "Panozo Sejas");
//        datos.put("registro", "217015436");
//        datos.put("ci", "217015436");
//        datos.put("carrera", "2");
//        datos.put("periodo", "2");
//        datos.put("materias", "INF552, FIS100");
//        try {
//			np.insertar(datos);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Error! " + e.getMessage());
//		} 
        //eliminar
//        np.eliminar(0); 
        //mostrar por registro
        try {
			System.out.println(np.mostrarPorRegistro("218017243"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

        //materias
        //NMateria nm = new NMateria();
        //listar F
        /*datos.put("sigla", "inf");
        datos.put("nombre", "datos");
        try {
        	System.out.println(nm.listar(datos)); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
        
        //insertar F      
        /*try {
            datos.put("nombre", "Calculo 1");
            datos.put("sigla", "MAT101");
            nm.insertar(datos);
        } catch (Exception e) {
            System.out.println("Error SQL " + e.getMessage());
        }*/
        //eliminar F
        //nm.eliminar(1);
        //mostrar F
        //System.out.println(nm.mostrar(1));

        //grupos
        //NGrupoMateria gm = new NGrupoMateria();
        //listar
//        try {
//        	System.out.println(gm.listar(datos)); 
//        } catch (Exception e) {
//        	System.out.println(e.getMessage());
//        }
        //insertar F
		
//		  datos.put("materia", "MAT101"); 
//		  datos.put("grupo", "Z2");
//		  datos.put("horario", "19.45-21.30_lun-mie-vi"); 
//		  datos.put("docente", "3");
//		  datos.put("carrera", "1"); 
//		  try { 
//			  gm.insertar(datos); 
//		  } catch (Exception e) {
//			  System.out.println(e.getMessage()); 
//		  }
		 
        //System.out.println(Arrays.asList(DGrupoMateria.LLAVES));
	}

}
