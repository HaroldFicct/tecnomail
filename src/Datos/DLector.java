package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DLector {
    
    private int id;
    
    private String nombre;
    private String carnet;    
    private String correo;
    private String telefono;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public DLector(){
        this.nombre="";
        this.carnet="";
        this.correo="";
        this.telefono="";
    }
    
    public DLector(String nombre, String carnet,String correo, String telefono){
        this.nombre=nombre;
        this.carnet=carnet;
        this.correo=correo;
        this.telefono=telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    //--------------------------------------------------Listar todos los objetos------------------------------------------------------
    public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "lector.id, " +
                        "lector.nombre, " +
                        "lector.carnet, " +
                        "lector.correo, " +
                        "lector.telefono " +
                        "from lector";
                ResultSet result = connection.select(sql);
                //System.out.println(result);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("carnet"));
                    datos.add(result.getString("correo"));
                    datos.add(result.getString("telefono"));
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
                String sql = "update lector set "
                    + "nombre= '"+getNombre()
                    + "', carnet= '"+getCarnet()
                    + "', correo= '"+getCorreo()
                    + "', telefono= '"+getTelefono()
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
                String sql = "insert into lector ("
                    + "nombre, "
                    + "carnet, "
                    + "correo, "
                    + "telefono "
                    + ") values "
                    +"('"
                        +getNombre()+"', '"
                        +getCarnet()+"', '"
                        +getCorreo()+"', '"
                        +getTelefono()
                    +"');";
                connection.insert(sql);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
     //---------------------------------------------Eliminar un lector -----------------------------------------------------------------
    
        public void eliminar() { 
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "delete from lector where id = "+getId()+";";
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
                String sql = "select * from lector where id = "+getId()+";";
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
                "lector.id, " +
                "lector.nombre, " +
                "lector.carnet, " +
                "lector.correo, " +
                "lector.telefono " +
                "from lector" +
                "where lector.id="+getId()+";";
                ResultSet result = connection.select(sql); 
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("carnet"));
                    datos.add(result.getString("correo"));
                    datos.add(result.getString("telefono"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
}
