package Negocio;

import Datos.DLibro;
import Datos.DCategoria;    
import java.util.LinkedList;

public class NLibro {

    public DLibro dLibro;
    public DCategoria dcategoria;
    
    public NLibro() {}
    
        //  matematicas discretas:Braulio:20:500
        public void insertar(LinkedList<String> arg) {
        dLibro = new DLibro();
        dcategoria = new DCategoria();

        dcategoria.setCodigoDewey(Integer.parseInt(arg.get(3)));
        boolean existecategoria= dcategoria.existeDewey();
        int id =dcategoria.getidbydewey();
        
        System.out.println(existecategoria);
        if (existecategoria){
            dLibro.setTitulo(arg.get(0));
            dLibro.setAutor(arg.get(1));
            dLibro.setCantidad(Integer.parseInt(arg.get(2)));
            dLibro.setCatcodigo(id);
            dLibro.insertar();
        }

    }
}
