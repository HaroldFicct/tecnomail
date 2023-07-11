package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DUsuario {
    
    private int id;
    private String nombres;
    private String apellidos;
    private char genero;
    private String fecha_de_nacimiento;
    private String foto;
    private String carnet_de_identidad;
    private String direccion_de_vivienda;
    
    private int rol_id;
    private String email;
    private String password;
    
    
    public DUsuario() {
        this.id = 0;
        this.nombres = "";
        this.apellidos = "";
        this.genero = ' ';
        this.fecha_de_nacimiento = "";
        this.foto = "";
        this.carnet_de_identidad = "";
        this.direccion_de_vivienda = "";
        this.rol_id = 0;
        this.email = "";
        this.password = "";
        
    }
    
    public DUsuario(
            int id, 
            String nombres, 
            String apellidos, 
            char genero, 
            String fecha_de_nacimiento,
            String foto,
            String carnet_de_identida,
            String direccion_de_vivienda,
            int rol_id,
            String email,
            String password
    ) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.foto = foto;
        this.carnet_de_identidad = carnet_de_identidad;
        this.direccion_de_vivienda = direccion_de_vivienda;
        this.rol_id = rol_id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombres;
    }

    public void setNombre(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }
    
    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }
    
    public String getFechaDeNacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFechaDeNacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }
    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public String getCarnetDeIdentidad() {
        return carnet_de_identidad;
    }

    public void setCarnetDeIdentidad(String carnet_de_identidad) {
        this.carnet_de_identidad = carnet_de_identidad;
    }
    
    public String getDireccionDeVivienda() {
        return direccion_de_vivienda;
    }

    public void setDireccionDeVivienda(String direccion_de_vivienda) {
        this.direccion_de_vivienda = direccion_de_vivienda;
    }
    
    public int getRolId() {
        return rol_id;
    }

    public void setRolId(int rol_id) {
        this.rol_id = rol_id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos
    public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "users.id, " +
                        "users.nombres, " +
                        "users.apellidos, " +
                        "users.genero, " +
                        "users.fecha_de_nacimiento, " +
                        "users.direccion_de_vivienda, " +
                        "users.carnet_de_identidad, " +
                        "users.email, " +
                        "(rol.nombre) as nombre_del_rol " +
                        "from users LEFT JOIN rol on users.rol_id = rol.id and rol.estado=1 " +
                        "where users.estado=1;";
                ResultSet result = connection.select(sql);
                //System.out.println(result);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombres"));
                    datos.add(result.getString("apellidos"));
                    datos.add(result.getString("genero"));
                    datos.add(result.getString("fecha_de_nacimiento"));
                    datos.add(result.getString("direccion_de_vivienda"));
                    datos.add(result.getString("carnet_de_identidad"));
                    datos.add(result.getString("email"));
                    datos.add(result.getString("nombre_del_rol"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos
    public LinkedList<String> listar_por_rol_nombre(String rol_nombre) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql ="select " +
                        "users.id, " +
                        "users.nombres, " +
                        "users.apellidos, " +
                        "users.genero, " +
                        "users.fecha_de_nacimiento, " +
                        "users.direccion_de_vivienda, " +
                        "users.carnet_de_identidad, " +
                        "users.email, " +
                        "(rol.nombre) as nombre_del_rol " +
                        "from users LEFT JOIN rol on users.rol_id=rol.id and rol.estado=1 " +
                        "where users.estado=1 and LOWER(rol.nombre) LIKE LOWER('%"+ rol_nombre +"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombres"));
                    datos.add(result.getString("apellidos"));
                    datos.add(result.getString("genero"));
                    datos.add(result.getString("fecha_de_nacimiento"));
                    datos.add(result.getString("direccion_de_vivienda"));
                    datos.add(result.getString("carnet_de_identidad"));
                    datos.add(result.getString("email"));
                    datos.add(result.getString("nombre_del_rol"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos
    public LinkedList<String> listar_por_rol_id(int rol_id) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "users.id, " +
                        "users.nombres, " +
                        "users.apellidos, " +
                        "users.genero, " +
                        "users.fecha_de_nacimiento, " +
                        "users.direccion_de_vivienda, " +
                        "users.carnet_de_identidad, " +
                        "users.email, " +
                        "(rol.nombre) as nombre_del_rol " +
                        "from users LEFT JOIN rol on users.rol_id=rol.id and rol.estado=1 " +
                        "where users.estado=1 and users.rol_id=" +rol_id+ ";";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombres"));
                    datos.add(result.getString("apellidos"));
                    datos.add(result.getString("genero"));
                    datos.add(result.getString("fecha_de_nacimiento"));
                    datos.add(result.getString("direccion_de_vivienda"));
                    datos.add(result.getString("carnet_de_identidad"));
                    datos.add(result.getString("email"));
                    datos.add(result.getString("nombre_del_rol"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos
    public LinkedList<String> buscar_usuario_nombre(String nombre) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql ="select " +
                " users.id, " +
                "users.nombres, " +
                "users.apellidos, " +
                "users.genero, " +
                "users.fecha_de_nacimiento, " +
                "users.direccion_de_vivienda, " +
                "users.carnet_de_identidad, " +
                "users.email, " +
                "(rol.nombre) as nombre_del_rol " +
                "from users LEFT JOIN rol on users.rol_id = rol.id and rol.estado = 1 " +
                "where users.estado = 1 and LOWER(users.nombres) LIKE LOWER('%"+nombre+"%')";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombres"));
                    datos.add(result.getString("apellidos"));
                    datos.add(result.getString("genero"));
                    datos.add(result.getString("fecha_de_nacimiento"));
                    datos.add(result.getString("direccion_de_vivienda"));
                    datos.add(result.getString("carnet_de_identidad"));
                    datos.add(result.getString("email"));
                    datos.add(result.getString("nombre_del_rol"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos
    public LinkedList<String> buscar_usuario_apellido(String apellido) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql ="select " +
                " users.id, " +
                "users.nombres, " +
                "users.apellidos, " +
                "users.genero, " +
                "users.fecha_de_nacimiento, " +
                "users.direccion_de_vivienda, " +
                "users.carnet_de_identidad, " +
                "users.email, " +
                "(rol.nombre) as nombre_del_rol " +
                "from users LEFT JOIN rol on users.rol_id = rol.id and rol.estado = 1 " +
                "where users.estado = 1 and LOWER(users.apellidos) LIKE LOWER('%"+apellido+"%')";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombres"));
                    datos.add(result.getString("apellidos"));
                    datos.add(result.getString("genero"));
                    datos.add(result.getString("fecha_de_nacimiento"));
                    datos.add(result.getString("direccion_de_vivienda"));
                    datos.add(result.getString("carnet_de_identidad"));
                    datos.add(result.getString("email"));
                    datos.add(result.getString("nombre_del_rol"));
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
                //String sql = "select users.*, (rol.nombre) as rol_nombre from users, rol where users.rol_id = rol.id and users.estado=1 and rol.estado=1 and users.id="+getId()+";";
                String sql ="select " +
                " users.id, " +
                "users.nombres, " +
                "users.apellidos, " +
                "users.genero, " +
                "users.fecha_de_nacimiento, " +
                "users.direccion_de_vivienda, " +
                "users.carnet_de_identidad, " +
                "users.email, " +
                "(rol.nombre) as nombre_del_rol " +
                "from users LEFT JOIN rol on users.rol_id = rol.id and rol.estado = 1 " +
                "where users.estado = 1 and users.id="+getId()+";";
                
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("id")));
                    datos.add(result.getString("nombres"));
                    datos.add(result.getString("apellidos"));
                    datos.add(result.getString("genero"));
                    datos.add(result.getString("fecha_de_nacimiento"));
                    datos.add(result.getString("direccion_de_vivienda"));
                    datos.add(result.getString("carnet_de_identidad"));
                    datos.add(result.getString("email"));
                    datos.add(result.getString("nombre_del_rol"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //insertar un nuevo usuario con el rol que yo elija
    public void insertar() {
        DBConnection connection = new DBConnection();
        try {        
            if (connection.connect()) {
                String sql = "insert into users ("
                    + "nombres, "
                    + "apellidos, "
                    + "genero, "
                    + "fecha_de_nacimiento, "
                    + "foto, "
                    + "carnet_de_identidad, "
                    + "direccion_de_vivienda, "
                    + "rol_id, "
                    + "email, "
                    + "password"
                    + ") values "
                    +"('"
                        +getNombre()+"', '"
                        +getApellido()+"', '"
                        +getGenero()+"', '"
                        +getFechaDeNacimiento()+"', '"
                        +getFoto()+"', '"
                        +getCarnetDeIdentidad()+"', '"
                        +getDireccionDeVivienda()+"', '"
                        +getRolId()+"', '"
                        +getEmail()+"', '"
                        +getPassword()
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
                String sql = "update users set "
                    + "nombres= '"+getNombre()
                    + "', apellidos= '"+getApellido()
                    + "', genero= '"+getGenero()
                    + "', fecha_de_nacimiento= '"+getFechaDeNacimiento()
                    + "', foto= '"+getFoto()
                    + "', carnet_de_identidad= '"+getCarnetDeIdentidad()
                    + "', direccion_de_vivienda= '"+getDireccionDeVivienda()
                    + "', rol_id= '"+getRolId()
                    + "', email= '"+getEmail()
                    + "', password= '"+getPassword()
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
                String sql = "update users set estado=0 where id = "+getId()+";";
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
                String sql = "select * from users where estado=1 and id = "+getId()+";";
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