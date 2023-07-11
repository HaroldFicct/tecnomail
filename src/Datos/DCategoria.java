package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DCategoria {

    private int id;

    private String nombre;
    private int codigoDewey;

    public DCategoria() {
        this.nombre = "";
        this.codigoDewey = 0;
    }

    public DCategoria(String sigla, int codigoDewey) {
        this.nombre = nombre;
        this.codigoDewey = codigoDewey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoDewey() {
        return codigoDewey;
    }

    public void setCodigoDewey(int codigoDewey) {
        this.codigoDewey = codigoDewey;
    }

    
    //--------------------------------------------------Listar todos los objetos------------------------------------------------------
    public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "categoria.id, " +
                        "categoria.nombre, " +
                        "categoria.codigoDewey " +
                        "from categoria";
                ResultSet result = connection.select(sql);
                //System.out.println(result);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(Integer.toString(result.getInt("codigoDewey")));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    } 

    //----------------------------------------- Modificar una carrera-----------------------------------------------------------------------
        public void modificar(){
        DBConnection connection = new DBConnection(); 
        try {  
            if (connection.connect()) {
                String sql = "update categoria set "
                    + "nombre= '"+getNombre()
                    + "', codigoDewey= '"+getCodigoDewey()
                    +"' where id="+getId()+";";
                connection.update(sql);
                connection.close();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    //------------------------insertar un nueva carrera--------------------------------------------------------------------------------
    public void insertar() {
        DBConnection connection = new DBConnection();
        try {        
            if (connection.connect()) {
                String sql = "insert into categoria ("
                    + "nombre, "
                    + "codigoDewey "
                    + ") values "
                    +"('"
                        +getNombre()+"', '"
                        +getCodigoDewey()
                    +"');";
                connection.insert(sql);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
     //---------------------------------------------Eliminar una carrera -----------------------------------------------------------------
    
        public void eliminar() { 
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "delete from categoria where id = "+getId()+";";
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
                String sql = "select * from categoria where id = "+getId()+";";
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
    //-----------------------------------------existe dewey------------------------------------------------------------------
      public boolean existeDewey() {
        boolean b = false;
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select * from categoria where codigodewey = "+getCodigoDewey()+";";
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
                "categoria.id, " +
                "categoria.nombre, " +
                "categoria.codigoDewey " +
                "from categoria" +
                "where categoria.id="+getId()+";";
                ResultSet result = connection.select(sql); 
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(Integer.toString(result.getInt("codigoDewey")));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
    
    
    
    
        public int getidbydewey() {
        LinkedList<String> datos = new LinkedList<>();
        int ide=0;
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                
                String sql = "select * from categoria where codigodewey = "+getCodigoDewey()+";";
                
                
                ResultSet result = connection.select(sql); 
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(Integer.toString(result.getInt("codigoDewey")));
                    ide=Integer.parseInt(datos.get(0));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ide;
    }
}
