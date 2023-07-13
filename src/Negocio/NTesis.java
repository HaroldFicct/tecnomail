package Negocio;

import Datos.DTesis;
import Datos.DCarrera;

import java.util.LinkedList;

public class NTesis {

    public DTesis dTesis;
    public DCarrera dCarrera;

    public NTesis() {}
    // docker para desarrolladores:Jorge luis condori:18/01/2022/:Braulio caceres:Docker desarrollador tesis:5

    public void insertar(LinkedList<String> arg) {
        dTesis = new DTesis();
        dCarrera = new DCarrera();
        dCarrera.setCodigo(Integer.parseInt(arg.get(5)));
        boolean existecarrera=dCarrera.existecarrera();
        int id = dCarrera.getidbycodigo();
        System.out.println(existecarrera);
        if (existecarrera){
            dTesis.setTitulo(arg.get(0));
            dTesis.setAutor(arg.get(1));
            dTesis.setFecha(arg.get(2));
            dTesis.setAsesor(arg.get(3));
            dTesis.setPalabrasclave(arg.get(4));
            dTesis.setCarreracodigo(id);
            dTesis.insertar();
        }
    }
}
