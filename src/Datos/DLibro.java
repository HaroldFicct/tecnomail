package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DLibro {
    private int id;

    private String titulo;
    private String autor;
    private int cantidad;
    private int categoriacodigo;

    public DLibro() {

        this.titulo = "";
        this.autor = "";
        this.cantidad = 0;
        this.categoriacodigo = 0;
    }

    public DLibro(String titulo, String autor, int cantidad, int categoriacodigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.cantidad = cantidad;
        this.categoriacodigo = categoriacodigo;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCatcodigo() {
        return categoriacodigo;
    }

    public void setCatcodigo(int catcodigo) {
        this.categoriacodigo = catcodigo;
    }
    
    //---------------------------------Listar----------------------------------------------------------------------------------------------
        public LinkedList<String> listar() {
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
                        "from libro";
                ResultSet result = connection.select(sql);
                //System.out.println(result);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("titulo"));
                    datos.add(result.getString("autor"));
                    datos.add(Integer.toString(result.getInt("cantidad")));
                    datos.add(Integer.toString(result.getInt("categoriacodigo")));
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
