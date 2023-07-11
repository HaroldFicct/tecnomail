package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DCarrera {

    private int id;

    private String nombre;
    private String sigla;
    private int codigo;

    
       public DCarrera(){
        this.nombre="";
        this.sigla="";
        this.codigo=0;

    }
    
    public DCarrera(String nombre, String sigla,int codigo){
        this.nombre=nombre;
        this.sigla=sigla;
        this.codigo=codigo;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    //--------------------------------------------------Listar todos los objetos------------------------------------------------------
    public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "carrera.id, " +
                        "carrera.nombre, " +
                        "carrera.sigla, " +
                        "carrera.codigo " +
                        "from carrera";
                ResultSet result = connection.select(sql);
                //System.out.println(result);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("sigla"));
                    datos.add(Integer.toString(result.getInt("codigo")));
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
                String sql = "update carrera set "
                    + "nombre= '"+getNombre()
                    + "', sigla= '"+getSigla()
                    + "', codigo= '"+getCodigo()
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
                String sql = "insert into carrera ("
                    + "nombre, "
                    + "sigla, "
                    + "codigo "
                    + ") values "
                    +"('"
                        +getNombre()+"', '"
                        +getSigla()+"', '"
                        +getCodigo()
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
                String sql = "delete from carrera where id = "+getId()+";";
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
                String sql = "select * from carrera where id = "+getId()+";";
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
                "carrera.id, " +
                "carrera.nombre, " +
                "carrera.sigla, " +
                "lector.codigo " +
                "from carrera" +
                "where carrera.id="+getId()+";";
                ResultSet result = connection.select(sql); 
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("sigla"));
                    datos.add(Integer.toString(result.getInt("codigo")));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
    
}
