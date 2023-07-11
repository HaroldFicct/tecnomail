package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAdministrador {

    private int id;

    private String nombre;
    private String telefono;
    private int codigo;
    private String contrasena;

    public DAdministrador() {

        this.nombre = "";
        this.telefono = "";
        this.codigo = 0;
        this.contrasena = "";
    }

    public DAdministrador(String nombre, String telefono, int codigo, String contrasena) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.codigo = codigo;
        this.telefono = telefono;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    //---------------------------------Listar----------------------------------------------------------------------------------------------
        public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "administrador.id, " +
                        "administrador.nombre, " +
                        "administrador.telefono, " +
                        "administrador.codigo, " +
                        "administrador.contrasena " +
                        "from administrador";
                ResultSet result = connection.select(sql);
                //System.out.println(result);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("telefono"));
                    datos.add(Integer.toString(result.getInt("codigo")));
                    datos.add(result.getString("contrasena"));
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
                String sql = "update administrador set "
                    + "nombre= '"+getNombre()
                    + "', telefono= '"+getTelefono()
                    + "', codigo= '"+getCodigo()
                    + "', contrasena= '"+getContrasena()
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
                String sql = "insert into administrador ("
                    + "nombre, "
                    + "telefono, "
                    + "codigo, "
                    + "contrasena "
                    + ") values "
                    +"('"
                        +getNombre()+"', '"
                        +getTelefono()+"', '"
                        +getCodigo()+"', '"
                        +getContrasena()
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
                String sql = "delete from administrador where id = "+getId()+";";
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
                String sql = "select * from administrador where id = "+getId()+";";
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
                "administrador.id, " +
                "administrador.nombre, " +
                "administrador.telefono, " +
                "administrador.codigo, " +
                "administrador.contraseña " +
                "from administrador" +
                "where administrador.id="+getId()+";";
                ResultSet result = connection.select(sql); 
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("telefono"));
                    datos.add(Integer.toString(result.getInt("codigo")));
                    datos.add(result.getString("contrasena"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
}
