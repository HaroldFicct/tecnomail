package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DActividad {
    
    private int id;
    private String nombre;
    private String descripcion;
    private String actor;
    private int usuario_id;
    private int periodo_id;
    
    public DActividad() {
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.actor = "";
        this.usuario_id = 0;
        this.periodo_id = 0;
    }
    
    public DActividad(int id, String nombre, String descripcion, String actor, int usuario_id, int periodo_id) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.actor = actor;
        this.usuario_id = usuario_id;
        this.periodo_id = periodo_id;
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
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getUsuarioId() {
        return usuario_id;
    }

    public void setUsuarioId(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    public int getPeriodoId() {
        return periodo_id;
    }

    public void setPeriodoId(int periodo_id) {
        this.periodo_id = periodo_id;
    }
    
    

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos de la actividad
    public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(actividad.id) as nro, " +
                        "(actividad.nombre) as nombre, " +
                        "(actividad.descripcion) as descripcion, " +
                        "(actividad.actor) as actor, " +
                        "(users.nombres) as actividad_creada_por_nombres, " +
                        "(users.apellidos) as actividad_creada_por_apellidos, " +
                        "(periodo.nombre) as actividad_del_periodo, " +
                        "(cronograma.fecha_inicio) as fecha_de_inicio, " +
                        "(cronograma.fecha_fin) as fecha_fin, " +
                        "(cronograma.hora_inicio) as hora_de_inicio, " +
                        "(cronograma.hora_fin) as hora_fin " +
                        "from " +
                        "users, " +
                        "actividad " +
                        "LEFT JOIN cronograma on actividad.id = cronograma.actividad_id and cronograma.estado =1 " +
                        "LEFT JOIN periodo on actividad.periodo_id = periodo.id and periodo.estado =1 " +
                        "where " +
                        "actividad.estado = 1 and " +
                        "users.id = actividad.usuario_id;";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("nro")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("descripcion"));
                    datos.add(result.getString("actor"));
                    datos.add(result.getString("actividad_creada_por_nombres"));
                    datos.add(result.getString("actividad_creada_por_apellidos"));
                    datos.add(result.getString("actividad_del_periodo"));
                    datos.add(result.getString("fecha_de_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_de_inicio"));
                    datos.add(result.getString("hora_fin"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public LinkedList<String> listar_por_periodo_id(int rol_id) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(actividad.id) as nro, " +
                        "(actividad.nombre) as nombre, " +
                        "(actividad.descripcion) as descripcion, " +
                        "(actividad.actor) as actor, " +
                        "(users.nombres) as actividad_creada_por_nombres, " +
                        "(users.apellidos) as actividad_creada_por_apellidos, " +
                        "(periodo.nombre) as actividad_del_periodo, " +
                        "(cronograma.fecha_inicio) as fecha_de_inicio, " +
                        "(cronograma.fecha_fin) as fecha_fin, " +
                        "(cronograma.hora_inicio) as hora_de_inicio, " +
                        "(cronograma.hora_fin) as hora_fin " +
                        "from " +
                        "users, " +
                        "actividad " +
                        "LEFT JOIN cronograma on actividad.id = cronograma.actividad_id and cronograma.estado =1 " +
                        "LEFT JOIN periodo on actividad.periodo_id = periodo.id and periodo.estado =1 " +
                        "where " +
                        "actividad.estado = 1 and " +
                        "users.id = actividad.usuario_id and "+
                        "periodo.id ="+rol_id+
                        ";";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("nro")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("descripcion"));
                    datos.add(result.getString("actor"));
                    datos.add(result.getString("actividad_creada_por_nombres"));
                    datos.add(result.getString("actividad_creada_por_apellidos"));
                    datos.add(result.getString("actividad_del_periodo"));
                    datos.add(result.getString("fecha_de_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_de_inicio"));
                    datos.add(result.getString("hora_fin"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public LinkedList<String> listar_por_periodo_nombre(String nombre_periodo) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(actividad.id) as nro, " +
                        "(actividad.nombre) as nombre, " +
                        "(actividad.descripcion) as descripcion, " +
                        "(actividad.actor) as actor, " +
                        "(users.nombres) as actividad_creada_por_nombres, " +
                        "(users.apellidos) as actividad_creada_por_apellidos, " +
                        "(periodo.nombre) as actividad_del_periodo, " +
                        "(cronograma.fecha_inicio) as fecha_de_inicio, " +
                        "(cronograma.fecha_fin) as fecha_fin, " +
                        "(cronograma.hora_inicio) as hora_de_inicio, " +
                        "(cronograma.hora_fin) as hora_fin " +
                        "from " +
                        "users, " +
                        "actividad " +
                        "LEFT JOIN cronograma on actividad.id = cronograma.actividad_id and cronograma.estado =1 " +
                        "LEFT JOIN periodo on actividad.periodo_id = periodo.id and periodo.estado =1 " +
                        "where " +
                        "actividad.estado = 1 and " +
                        "users.id = actividad.usuario_id and "+
                        "LOWER(periodo.nombre) LIKE LOWER('%"+nombre_periodo+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("nro")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("descripcion"));
                    datos.add(result.getString("actor"));
                    datos.add(result.getString("actividad_creada_por_nombres"));
                    datos.add(result.getString("actividad_creada_por_apellidos"));
                    datos.add(result.getString("actividad_del_periodo"));
                    datos.add(result.getString("fecha_de_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_de_inicio"));
                    datos.add(result.getString("hora_fin"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public LinkedList<String> listar_por_actividad_por_nombres_creador(String nombres_creador) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(actividad.id) as nro, " +
                        "(actividad.nombre) as nombre, " +
                        "(actividad.descripcion) as descripcion, " +
                        "(actividad.actor) as actor, " +
                        "(users.nombres) as actividad_creada_por_nombres, " +
                        "(users.apellidos) as actividad_creada_por_apellidos, " +
                        "(periodo.nombre) as actividad_del_periodo, " +
                        "(cronograma.fecha_inicio) as fecha_de_inicio, " +
                        "(cronograma.fecha_fin) as fecha_fin, " +
                        "(cronograma.hora_inicio) as hora_de_inicio, " +
                        "(cronograma.hora_fin) as hora_fin " +
                        "from " +
                        "users, " +
                        "actividad " +
                        "LEFT JOIN cronograma on actividad.id = cronograma.actividad_id and cronograma.estado =1 " +
                        "LEFT JOIN periodo on actividad.periodo_id = periodo.id and periodo.estado =1 " +
                        "where " +
                        "actividad.estado = 1 and " +
                        "users.id = actividad.usuario_id and "+
                        "LOWER(users.nombres) LIKE LOWER('%"+nombres_creador+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("nro")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("descripcion"));
                    datos.add(result.getString("actor"));
                    datos.add(result.getString("actividad_creada_por_nombres"));
                    datos.add(result.getString("actividad_creada_por_apellidos"));
                    datos.add(result.getString("actividad_del_periodo"));
                    datos.add(result.getString("fecha_de_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_de_inicio"));
                    datos.add(result.getString("hora_fin"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public LinkedList<String> listar_por_actividad_por_apellidos_creador(String apellidos_creador) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(actividad.id) as nro, " +
                        "(actividad.nombre) as nombre, " +
                        "(actividad.descripcion) as descripcion, " +
                        "(actividad.actor) as actor, " +
                        "(users.nombres) as actividad_creada_por_nombres, " +
                        "(users.apellidos) as actividad_creada_por_apellidos, " +
                        "(periodo.nombre) as actividad_del_periodo, " +
                        "(cronograma.fecha_inicio) as fecha_de_inicio, " +
                        "(cronograma.fecha_fin) as fecha_fin, " +
                        "(cronograma.hora_inicio) as hora_de_inicio, " +
                        "(cronograma.hora_fin) as hora_fin " +
                        "from " +
                        "users, " +
                        "actividad " +
                        "LEFT JOIN cronograma on actividad.id = cronograma.actividad_id and cronograma.estado =1 " +
                        "LEFT JOIN periodo on actividad.periodo_id = periodo.id and periodo.estado =1 " +
                        "where " +
                        "actividad.estado = 1 and " +
                        "users.id = actividad.usuario_id and "+
                        "LOWER(users.apellidos) LIKE LOWER('%"+apellidos_creador+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("nro")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("descripcion"));
                    datos.add(result.getString("actor"));
                    datos.add(result.getString("actividad_creada_por_nombres"));
                    datos.add(result.getString("actividad_creada_por_apellidos"));
                    datos.add(result.getString("actividad_del_periodo"));
                    datos.add(result.getString("fecha_de_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_de_inicio"));
                    datos.add(result.getString("hora_fin"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public LinkedList<String> listar_por_id_del_creador(int id_creador) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(actividad.id) as nro, " +
                        "(actividad.nombre) as nombre, " +
                        "(actividad.descripcion) as descripcion, " +
                        "(actividad.actor) as actor, " +
                        "(users.nombres) as actividad_creada_por_nombres, " +
                        "(users.apellidos) as actividad_creada_por_apellidos, " +
                        "(periodo.nombre) as actividad_del_periodo, " +
                        "(cronograma.fecha_inicio) as fecha_de_inicio, " +
                        "(cronograma.fecha_fin) as fecha_fin, " +
                        "(cronograma.hora_inicio) as hora_de_inicio, " +
                        "(cronograma.hora_fin) as hora_fin " +
                        "from " +
                        "users, " +
                        "actividad " +
                        "LEFT JOIN cronograma on actividad.id = cronograma.actividad_id and cronograma.estado =1 " +
                        "LEFT JOIN periodo on actividad.periodo_id = periodo.id and periodo.estado =1 " +
                        "where " +
                        "actividad.estado = 1 and " +
                        "users.id = actividad.usuario_id and "+
                        "actividad.usuario_id ="+id_creador+
                        ";";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("nro")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("descripcion"));
                    datos.add(result.getString("actor"));
                    datos.add(result.getString("actividad_creada_por_nombres"));
                    datos.add(result.getString("actividad_creada_por_apellidos"));
                    datos.add(result.getString("actividad_del_periodo"));
                    datos.add(result.getString("fecha_de_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_de_inicio"));
                    datos.add(result.getString("hora_fin"));
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
                String sql = "select " +
                        "(actividad.id) as nro, " +
                        "(actividad.nombre) as nombre, " +
                        "(actividad.descripcion) as descripcion, " +
                        "(actividad.actor) as actor, " +
                        "(users.nombres) as actividad_creada_por_nombres, " +
                        "(users.apellidos) as actividad_creada_por_apellidos, " +
                        "(periodo.nombre) as actividad_del_periodo, " +
                        "(cronograma.fecha_inicio) as fecha_de_inicio, " +
                        "(cronograma.fecha_fin) as fecha_fin, " +
                        "(cronograma.hora_inicio) as hora_de_inicio, " +
                        "(cronograma.hora_fin) as hora_fin " +
                        "from " +
                        "users, " +
                        "actividad " +
                        "LEFT JOIN cronograma on actividad.id = cronograma.actividad_id and cronograma.estado=1 " +
                        "LEFT JOIN periodo on actividad.periodo_id = periodo.id and periodo.estado=1 " +
                        "where actividad.estado=1 and users.id = actividad.usuario_id and actividad.id="+getId()+";";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("nro")));
                    datos.add(result.getString("nombre"));
                    datos.add(result.getString("descripcion"));
                    datos.add(result.getString("actor"));
                    datos.add(result.getString("actividad_creada_por_nombres"));
                    datos.add(result.getString("actividad_creada_por_apellidos"));
                    datos.add(result.getString("actividad_del_periodo"));
                    datos.add(result.getString("fecha_de_inicio"));
                    datos.add(result.getString("fecha_fin"));
                    datos.add(result.getString("hora_de_inicio"));
                    datos.add(result.getString("hora_fin"));
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
    public int insertar() {
        DBConnection connection = new DBConnection();
        try {        
        	int id = 0;
            if (connection.connect()) {
                String sql = "insert into actividad ("
                    + "nombre, "
                    + "descripcion, "
                    + "actor, "
                    + "usuario_id, "
                    + "periodo_id"
                    + ") values "
                    +"('"
                        +getNombre()+"', '"
                        +getDescripcion()+"', '"
                        +getActor()+"', '"
                        +getUsuarioId()+"', '"
                        +getPeriodoId()
                    +"');";
                id = connection.insert(sql);
                connection.close();                
            }
            return id;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void modificar(){
        DBConnection connection = new DBConnection(); 
        try {  
            if (connection.connect()) {
                String sql = "update actividad set "
                    + "nombre= '"+getNombre()
                    + "', descripcion= '"+getDescripcion()
                    + "', actor= '"+getActor()
                    + "', usuario_id= '"+getUsuarioId()
                    + "', periodo_id = '"+getPeriodoId()
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
                String sql = "update actividad set estado = 0 where id = "+getId()+";";
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
                String sql = "select * from actividad where estado=1 and id = "+getId()+";";
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
    public int GetLastID(){
        int id=0;
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select id from actividad ORDER BY id DESC LIMIT 1;";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    id = result.getInt("id");
                }
                connection.close();  
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
}