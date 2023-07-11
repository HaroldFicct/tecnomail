package Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DCargaHorariaGrupo {
    
    private int id;
    private int grupo_id;
    private int carga_horaria_id;
    
    public DCargaHorariaGrupo() {
        this.id = 0;
        this.grupo_id = 0;
        this.carga_horaria_id = 0;
    }
    
    public DCargaHorariaGrupo(int id, int grupo_id, int carga_horaria_id) {
        this.id = id;
        this.grupo_id = grupo_id;
        this.carga_horaria_id = carga_horaria_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getGrupoId() {
        return grupo_id;
    }

    public void setGrupoId(int grupo_id) {
        this.grupo_id = grupo_id;
    }
    public int getCargaHorariaId() {
        return carga_horaria_id;
    }

    public void setCargaHorariaId(int carga_horaria_id) {
        this.carga_horaria_id = carga_horaria_id;
    }
    
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar() {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1;";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar_por_nombres_de_auxiliar(String nombres_de_aux) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1 and "
                        + "LOWER(u.nombres) LIKE LOWER('%"+nombres_de_aux+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar_por_apellidos_de_auxiliar(String apellidos_de_aux) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1 and "
                        + "LOWER(u.apellidos) LIKE LOWER('%"+apellidos_de_aux+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar_por_nombres_de_docente(String nombres_de_docente) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1 and "
                        + "LOWER(d.nombres) LIKE LOWER('%"+nombres_de_docente+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar_por_apellidos_de_docente(String apellidos_de_docente) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1 and "
                        + "LOWER(d.apellidos) LIKE LOWER('%"+apellidos_de_docente+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar_por_sigla_materia(String sigla_materia) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1 and "
                        + "LOWER(m.sigla) LIKE LOWER('%"+sigla_materia+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar_por_sigla_grupo(String sigla_grupo) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1 and "
                        + "LOWER(g.sigla) LIKE LOWER('%"+sigla_grupo+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
                }
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return datos;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //es para listar todos los objetos del rol
    public LinkedList<String> listar_por_horas_asignadas(String horas_asignadas) {
        LinkedList<String> datos = new LinkedList<>();
        DBConnection connection = new DBConnection();
        try {
            if (connection.connect()) {
                String sql = "select " +
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1 and "
                        + "LOWER(ch.horario) LIKE LOWER('%"+horas_asignadas+"%');";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
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
                        "(a.id) as codigo_auxiliar, " +
                        "(u.nombres) as nombres_auxiliar, " +
                        "(u.apellidos) as apellidos_auxiliar, " +
                        "(ch.horario) as horas_asignadas, " +
                        "(ch.horario_adicional) as horas_adicionales, " +
                        "(m.sigla) as sigla_materia, " +
                        "(g.sigla) as sigla_grupo, " +
                        "(d.nombres) as nombres_docente, " +
                        "(d.apellidos) as apellidos_docente " +
                        "from " +
                        "carga_horaria_grupo chg " +
                        "LEFT JOIN carga_horaria ch on ch.id = chg.carga_horaria_id and ch.estado=1 " +
                        "LEFT JOIN grupo g on g.id = chg.grupo_id and g.estado=1 " +
                        "LEFT JOIN materia m on m.id = g.materia_id  and m.estado=1 " +
                        "LEFT JOIN auxiliar a on a.id = g.auxiliar_id and a.estado=1 " +
                        "LEFT JOIN users u on u.id = a.usuario_id and u.estado=1 " +
                        "LEFT JOIN users d on d.id = g.docente_id and u.estado=1"
                        + "where "
                        + "chg.estado=1 and "
                        + "chg.id="+getId()+";";
                ResultSet result = connection.select(sql);
                while (result.next()) {
                    datos.add(Integer.toString(result.getInt("codigo_auxiliar")));
                    datos.add(result.getString("nombres_auxiliar"));
                    datos.add(result.getString("apellidos_auxiliar"));
                    datos.add(result.getString("horas_asignadas"));
                    datos.add(result.getString("horas_adicionales"));
                    datos.add(result.getString("sigla_materia"));
                    datos.add(result.getString("sigla_grupo"));
                    datos.add(result.getString("nombres_docente"));
                    datos.add(result.getString("apellidos_docente"));
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
                String sql = "insert into carga_horaria_grupo (grupo_id, carga_horaria_id) values "
                    +"('"+getGrupoId()+"', '"+getCargaHorariaId()+"');";
                System.out.println(sql);
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
                String sql = "update carga_horaria_grupo set "
                    +"grupo_id='"+getGrupoId()
                    +"', carga_horaria_id='"+getCargaHorariaId()
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
                String sql = "update carga_horaria_grupo set estado = 0 where id = "+getId()+";";
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
                String sql = "select * from carga_horaria_grupo where estado=1 and id = "+getId()+";";
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