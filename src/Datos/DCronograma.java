package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DCronograma {
    
    private int id;
    private String fecha_inicio;
    private String fecha_fin;
    private String hora_inicio;
    private String hora_fin;
    private int actividad_id;
    
    public DCronograma() {
        this.id = 0;
        this.fecha_inicio = "";
        this.fecha_fin = "";
        this.hora_inicio = "";
        this.hora_fin = "00:00";
        this.actividad_id = 0;
    }
    
    public DCronograma(int id, String fecha_inicio, String fecha_fin, String hora_inicio, String hora_fin, int actividad_id) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.actividad_id = actividad_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFechaInicio() {
        return fecha_inicio;
    }

    public void setFechaInicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public String getFechaFin() {
        return fecha_fin;
    }

    public void setFechaFin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public String getHoraInicio() {
        return hora_inicio;
    }

    public void setHoraInicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    public String getHoraFin() {
        return hora_fin;
    }

    public void setHoraFin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
    public int getActividadId() {
        return actividad_id;
    }

    public void setActividadId(int actividad_id) {
        this.actividad_id = actividad_id;
    }

    
    
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select * from cronograma where estado=1;";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("fecha_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_inicio"));
                    datos.add(result.getString("hora_fin"));
                    datos.add(Integer.toString(result.getInt("actividad_id")));
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
                String sql = "select * from rol where estado=1 and id="+getId()+";";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("fecha_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_inicio"));
                    datos.add(result.getString("hora_fin"));
                    datos.add(Integer.toString(result.getInt("actividad_id")));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //insertar un nuevo rol
    public void insertar() {
        DBConnection connection = new DBConnection();
        try {        
            if (connection.connect()) {
                String sql = "insert into cronograma ("
                    + "fecha_inicio, "
                    + "fecha_fin, "
                    + "hora_inicio, "
                    + "hora_fin, "
                    + "actividad_id"
                    + ") values "
                    +"('"
                        +getFechaInicio()+"', '"
                        +getFechaFin()+"', '"
                        +getHoraInicio()+"', '"
                        +getHoraFin()+"', '"
                        +getActividadId()
                    +"');";
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
                String sql = "update cronograma set "
                    + "fecha_inicio= '"+getFechaInicio()
                    + "', fecha_fin= '"+getFechaFin()
                    + "', hora_inicio= '"+getHoraInicio()
                    + "', hora_fin= '"+getHoraFin()
                    + "', actividad_id = '"+getActividadId()
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
                String sql = "update cronograma set estado = 0 where id = "+getId()+";";
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
                String sql = "select * from cronograma where estado=1 and id = "+getId()+";";
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