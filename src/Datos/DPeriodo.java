package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DPeriodo {
    
    private int id;
    private String nombre;
    private String semestre;
    private String año;
    
    public DPeriodo() {
        this.id = 0;
        this.nombre = "";
        this.semestre = "";
        this.año = "";
    }
    
    public DPeriodo(int id, String nombre, String semestre, String año) {
        this.id = id;
        this.nombre = nombre;
        this.semestre = semestre;
        this.año = año;
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
    
    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos
    public LinkedList<String> listar(String fechaIni,String fechaFin) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                if(!(fechaIni.isEmpty()) && !(fechaIni.isEmpty()) ){
                    String sql = "select * from periodo where estado=1 and año between"+fechaIni+" and "+fechaFin+";";
                    ResultSet result = connection.select(sql);
                    while (result.next()) {
                        datos.add(Integer.toString(result.getInt("id")));
                        datos.add(result.getString("nombre"));
                        datos.add(result.getString("semestre"));
                        datos.add(result.getString("año"));
                    }
                }   
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //para buscar por id solo un objeto y mostrarlo
    public LinkedList<String> mostrarID() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select * from periodo where estado=1 and id="+getId()+";";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("semestre"));
                    datos.add(result.getString("año"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //insertar un nuevo objeto a la base de datos de periodo
    public void insertar() {
        DBConnection connection = new DBConnection();
        try {        
            if (connection.connect()) {
                String sql = "insert into periodo (nombre, semestre, año) values "
                    +"('"+getNombre()+"', '"+getSemestre()+"', '"+getAño()+"');";
                connection.insert(sql);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void modificar(){
        DBConnection connection = new DBConnection(); 
        try {  
            if (connection.connect()) {
                String sql = "update periodo set "
                    +"nombre='"+getNombre()
                    +"', semestre='"+getSemestre()
                    +"', año='"+getAño()
                    +"' where id="+getId()+";";
                connection.update(sql);
                connection.close();
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void eliminar() {  // lo elimina tanto de la tabla acceso como usuario
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "update periodo set estado=0 where id = "+getId()+";";
                connection.update(sql);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public boolean existeID() {
        boolean b = false;
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select * from periodo where id = "+getId()+";";
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
    
    public boolean existeFecha(){
        boolean b=false;
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select * from periodo where año = "+getAño()+" and semestre="+getSemestre()+";";
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
    
}