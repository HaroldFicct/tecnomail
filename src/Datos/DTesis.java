package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DTesis {
    
    private int id;

    private String titulo;
    private String autor;
    private String fecha;
    private String asesor;
    private String palabrasclave;
    private int carreracodigo;
   
    public DTesis() {

        this.titulo = "";
        this.autor = "";
        this.fecha = "";
        this.asesor = "";
        this.palabrasclave = "";
        this.carreracodigo = 0;

    }

    public DTesis(String titulo, String autor, String fecha, String asesor,String palabraclave,int  carreracodigo) {

        this.titulo = titulo;
        this.autor = autor;
        this.fecha = fecha;
        this.asesor = asesor;
        this.palabrasclave = palabraclave;
        this.carreracodigo = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public String getPalabrasclave() {
        return palabrasclave;
    }

    public void setPalabrasclave(String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }

    public int getCarreracodigo() {
        return carreracodigo;
    }

    public void setCarreracodigo(int carreracodigo) {
        this.carreracodigo = carreracodigo;
    }
    
    
    //---------------------------------Listar----------------------------------------------------------------------------------------------
        public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "tesis.id, " +
                        "tesis.titulo, " +
                        "tesis.autor, " +
                        "tesis.fecha, " +
                        "tesis.asesor " +
                        "tesis.palabrasclave " +
                        "tesis.carreracodigo " +
                        "from tesis";
                ResultSet result = connection.select(sql);
                //System.out.println(result);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("titulo"));
                    datos.add(result.getString("autor"));
                    datos.add(Integer.toString(result.getInt("fecha")));
                    datos.add(result.getString("asesor"));
                    datos.add(result.getString("palabrasclave"));
                    datos.add(Integer.toString(result.getInt("carrerascodigo")));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    } 
    //----------------------------------------- Modificar------------------------------------------------------------------------------
        public void modificar(){
        DBConnection connection = new DBConnection(); 
        try {  
            if (connection.connect()) {
                String sql = "update libro set "
                    + "titulo= '"+getTitulo()
                    + "', autor= '"+getAutor()
                    + "', cantidad= '"+getCantidad()
                    + "', categoriacodigo= '"+getCatcodigo()
                    +"' where id="+getId()+";";
                connection.update(sql);
                connection.close();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
        
    
    //------------------------insertar un nuevo usuario--------------------------------------------------------------------------------
    public void insertar() {
        DBConnection connection = new DBConnection();
        try {        
            if (connection.connect()) {
                String sql = "insert into libro ("
                    + "titulo, "
                    + "autor, "
                    + "cantidad, "
                    + "categoriacodigo "
                    + ") values "
                    +"('"
                        +getTitulo()+"', '"
                        +getAutor()+"', '"
                        +getCantidad()+"', '"
                        +getCatcodigo()
                    +"');";
                connection.insert(sql);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }    
        //---------------------------------------------Eliminar un Administrador -----------------------------------------------------------------
    
        public void eliminar() { 
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "delete from libro where id = "+getId()+";";
                connection.delete(sql);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }     
        
         //------------------ Verificador de existencia de una tupla por su id -------------------------------------------------------------
    public boolean existeID() {
        boolean b = false;
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select * from libro where id = "+getId()+";";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                  b = true;
                }
                connection.close();  
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return b;
    }   
    
     
    //----------------------------------Buscar por id solo un objeto y mostrarlo-------------------------------------------------------
    public LinkedList<String> mostrarID() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                "libro.id, " +
                "libro.titulo, " +
                "libro.autor, " +
                "libro.cantidad, " +
                "libro.categoriacodigo " +
                "from libro" +
                "where libro.id="+getId()+";";
                ResultSet result = connection.select(sql); 
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("titulo"));
                    datos.add(result.getString("autor"));
                    datos.add(Integer.toString(result.getInt("cantidad")));
                    datos.add(Integer.toString(result.getInt("catcodigo")));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
    
    
}
