package Conexion;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import Datos.Datos;
import Negocio.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comando {
    POP3 entrada;
    Mailer salida;
    LinkedList<String> parametros;
    String from;
    String stat;
    
    public NPeriodo nperiodo;
    public NReporte nreporte;
    public NUsuario nusuario;
    public NRol nrol;
    public NActividad nactividad;
    public NCronograma ncronograma;
    public NCargaHorariaGrupo ncargahorariagrupo;
    public NGrupo ngrupo;
    public NCargaHoraria ncargahoraria;
    public NMateria nMateria;
    Map<String, Object> params;
    LinkedList<String> campos;
    LinkedList<String> datos;
    public NGrupoMateria nGrupoMateria;
    public NPostulante nPostulante;
    public NExamen nExamen;
    
    
    public NLector nLector;
    public NAdministrador nAdministrador;
    public NCarrera nCarrera;  
    public NCategoria nCategoria;
    public NLibro nLibro;
    
    public Comando() {
    }

    public void escoger() throws MessagingException {
     this.salida = new Mailer();
     Validador validar = new Validador();
     String comando = parametros.get(0);
      if (!validar.isComando(comando)) {
        System.out.println("COMANDO: " + comando + " INVALIDO");
        salida.sendHtmlEmail(from, "Comando " + comando + " no encontrado", ToHTML.getHTMLMessage("Comando " + comando + " no encontrado", "Para consultar los comandos escriba y envie HELP "));
        return;
      } else {
        System.out.println("COMANDO: " + comando);
      }

      switch (comando) {
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++        
//                                    TABLA DE COMANDOS
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
          
        case "HELP":
            System.out.println("");
            salida.sendHtmlEmail(from, "Tabla de comandos EMAIL PROYECTO1 ", ToHTML.getHTMLMessage("Comando: " + comando, ToHTML.getHelpTable()));
        break;
        
        
        
        
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                          Caso de uso 1 Gestionar lector
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  
        // registrar lector     REGLEC:Carlos Tarqui:333:carlos@gmail.com:6000000
        case "REGLEC":
            if(parametros.size()==5){
                String nombre = parametros.get(1);
                String carnet = parametros.get(2);
                String correo = parametros.get(3);
                String telefono = parametros.get(4);
                if (validar.isNumber(carnet)){
                    LinkedList<String> arg = new LinkedList<>();    
                    for (int i = 1; i < 5; i++) {
                        arg.add(parametros.get(i));
                        System.out.println(arg.toString()+"locooooo");
                    }
                    nLector= new NLector();
                    System.out.println(arg.get(0)+"pos 0");
                    nLector.insertar(arg);                     
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos enviados tengan el formato y orden correcto"));
                }
            }else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                          Caso de uso 2 Gestionar Administrador
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//              registrar administrador     REGADM:Carlos Angola:6000000:2432:123456789                   
        case "REGADM":
            if(parametros.size()==5){
                String nombre = parametros.get(1);
                String telefono = parametros.get(2);
                String codigo = parametros.get(3);
                String contraseña = parametros.get(4);
                if (validar.isNumber(codigo)){
                    LinkedList<String> arg = new LinkedList<>();    
                    for (int i = 1; i < 5; i++) {
                        arg.add(parametros.get(i));
                    }
                    nAdministrador= new NAdministrador();

                    nAdministrador.insertar(arg);                     
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos enviados tengan el formato y orden correcto"));
                }
            }else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                          Caso de uso 3 Gestionar CARRERAS
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//              registrar CARRERAS     REGCARR:Ing. en sistemas:187:4            
        case "REGCARR":
            if(parametros.size()==4){
                String nombre = parametros.get(1);
                String sigla = parametros.get(2);
                String codigo = parametros.get(3);
                if (validar.isNumber(codigo)){
                    LinkedList<String> arg = new LinkedList<>();    
                    for (int i = 1; i < 4; i++) {
                        arg.add(parametros.get(i));
                    }
                    nCarrera= new NCarrera();
                    nCarrera.insertar(arg);                     
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos enviados tengan el formato y orden correcto"));
                }
            }else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
        
        
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                          Caso de uso 4 Gestionar CATEGORIAS
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//              registrar CATEGORIAS     REGCAT:GENERALIDADES:000        
        case "REGCAT":
            if(parametros.size()==3){
                String nombre = parametros.get(1);
                String codigoDewey = parametros.get(2);
                if (validar.isNumber(codigoDewey)){
                    LinkedList<String> arg = new LinkedList<>();    
                    for (int i = 1; i < 3; i++) {
                        arg.add(parametros.get(i));
                    }
                    nCategoria= new NCategoria();
                    nCategoria.insertar(arg);                     
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos enviados tengan el formato y orden correcto"));
                }
            }else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
        
        
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                          Caso de uso 5 Gestionar LIBRO
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//              registrar CATEGORIAS     REGLIB:matematicas discretas:Braulio:20:500
        case "REGLIB":
            if(parametros.size()==5){
                String titulo = parametros.get(1);
                String autor = parametros.get(2);
                String cantidad = parametros.get(3);
                String catcodigo = parametros.get(4);
                if (validar.isNumber(catcodigo)){
                    LinkedList<String> arg = new LinkedList<>();    
                    for (int i = 1; i < 5; i++) {
                        arg.add(parametros.get(i));
                    }
                    nLibro= new NLibro();
                    nLibro.insertar(arg);                     
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos enviados tengan el formato y orden correcto"));
                }
            }else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++        
//                                    CASO DE USO 1: GESTION DE ENTRENADORES Y CLIENTES
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  REGISTRAR PERIODO
//  REGPER: Id:Nom:Semestre:Año
//  REGPER: 1:2022-2:2:2022-01-01
        case "REGPER": 
            if (parametros.size() == 4) {
                String nombre = parametros.get(1);
                String semestre = parametros.get(2);
                String año = parametros.get(3);      
                if (validar.isNumber(semestre)&&validar.isAño(año)){
                    LinkedList<String> arg = new LinkedList<>();    
                    for (int i = 1; i < 11; i++) {
                        arg.add(parametros.get(i));
                    }
                    nperiodo = new NPeriodo();
                    nperiodo.insertar(arg);                      
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos enviados tengan el formato y orden correcto"));
                }
            }else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  MODIFICAR DATOS DEL PERIODO           
//  EDITPER:Id:Nombre:Semestre:Año
//  EDITPER:2:2021-2:2:2021-07-01
        case "EDITPER":
            if (parametros.size() == 5) {
                String id = parametros.get(1);
                String nombre = parametros.get(2);
                String semestre = parametros.get(3);
                String año = parametros.get(4);
                if (validar.isNumber(id)&&validar.isNumber(semestre)&&validar.isAño(año) ) {
                    if (existePeriodo(id)){//si existe el codigo
                        LinkedList<String> arg = new LinkedList<>();
                        for (int i = 1; i < 5; i++) {
                            arg.add(parametros.get(i));
                        }
                        nperiodo = new NPeriodo();
                        nperiodo.modificar(arg);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
                    } else {
                        System.out.println("ERROR: EL PERIODO NO EXISTE!!!");
                        salida.sendHtmlEmail(from, "ERROR: EL PERIODO BUSCADO NO EXISTE!!!", ToHTML.getHTMLMessage("El periodo utilizado no existe", "Asegurese de que el periodo utilizado sea correcto"));
                    }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos enviados tengan el formato y orden correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  ELIMINAR PERIODO
//  DELPER:id
//  DELUSER:2
        case "DELPER":
            if (parametros.size() == 2) {
                String id = parametros.get(1);
                if (validar.isNumber(id)){ //es lo mismo que length()>0
                        if (!existePeriodo(id)){           
                            int idEli= Integer.parseInt(id);
                            nperiodo = new NPeriodo();
                            nperiodo.eliminar(idEli);//elimina el periodo de la tabla
                            salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
                        }else{
                            System.out.println("ERROR: EL ID NO EXISTE!!!");
                            salida.sendHtmlEmail(from, "ERROR: EL ID UTILIZADO NO EXISTE!!!", ToHTML.getHTMLMessage("El id utilizado no existe", "El id "+id+ "no existe!!!"));
                        }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los codigos contenga algun dato"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  VER PERIODO
//  VERUSER:id
//  VERUSER:3
        case "VERPER": //este comando funciona si todos los datos de un objetivo son diferentes de null
            if (parametros.size() == 2) {
                String id = parametros.get(1);
                if (validar.isNumber(id)){ //es lo mismo que length()>0
                    if (existePeriodo(id)){
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("ID");
                        campos.add("NOMBRE");
                        campos.add("SEMESTRE");
                        campos.add("AÑO");
                        LinkedList<String> datos = new LinkedList<>();
                        int idper = Integer.parseInt(id);
                        datos = nperiodo.mostrar(idper);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("VER DATOS ", ToHTML.getTable(datos, campos)));
                    }else{
                        System.out.println("ERROR: EL ID NO EXISTE!!!");
                        salida.sendHtmlEmail(from, "ERROR: EL ID UTILIZADO NO EXISTE!!!", ToHTML.getHTMLMessage("El id utilizado no existe", "El id "+id+ "no existe!!!"));
                    }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los codigos contenga algun dato"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR PERIODO
//  LISUSER:{codEntrenador|codAdmin}:cant[nro|*]:[administrador|entrenador|usuario|*]
//  LISUSER:2022-10-12:2022-12-20
//  LISUSER:217051057:*:*        
        case "LISPER": //este comando funciona si todos los datos de un objetivo son diferentes de null
            if (parametros.size() == 3) {
                String fechaIni = parametros.get(1);
                String fechaFin = parametros.get(2);
                if (validar.isFecha(fechaIni)||validar.isFecha(fechaFin)){        
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("ID");
                        campos.add("NOMBRE");
                        campos.add("SEMESTRE");
                        campos.add("AÑO");
                        LinkedList<String> datos = new LinkedList<>();
                        nperiodo = new NPeriodo();
                        datos = nperiodo.listar(fechaIni,fechaFin);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("MOSTRAR DATOS DE TUS CLIENTES", ToHTML.getTable(datos, campos)));
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos introducidos sean de formato correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break; 
//  REPORTE POSTULANTE X PERIODO
//  REPORPOSPER: fechaInicio: fechaFin 
//  REPORPOSPER: 2022-01-01:2022-12-01 
        case "REPORPOSPER":
            if (parametros.size() == 3) {
                String fechaIni = parametros.get(1);
                String fechaFin = parametros.get(2);
                if (validar.isFecha(fechaIni)||validar.isFecha(fechaFin)){        
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("ID");
                        campos.add("NOMBRE");
                        campos.add("SEMESTRE");
                        campos.add("MATERIA");
                        campos.add("CANTIDAD");
                        LinkedList<String> datos = new LinkedList<>();
                        nreporte = new NReporte();
                        datos = nreporte.ReporteSemestrePos(fechaIni,fechaFin);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("MOSTRAR DATOS DE TUS CLIENTES", ToHTML.getTable(datos, campos)));
                } else {
                     System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos introducidos sean de formato correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR USUARIO
//  LISTARUSUARIO               //:{cod....|.....}
//  LISTARUSUARIO               //:{cod....|.....}
//  LISTARUSUARIO               //:{cod....|.....}
        case "LISTARUSUARIO": 
            if (parametros.size() == 1) {       
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("ID");
                    campos.add("NOMBRES");
                    campos.add("APELLIDOS");
                    campos.add("GENERO");
                    campos.add("FECHA_DE_NACIMIENTO");
                    campos.add("DIRECCION_DE_VIVIENDA");
                    campos.add("CARNET_DE_IDENTIDAD");
                    campos.add("EMAIL");
                    campos.add("NOMBRE_DEL_ROL");
                    LinkedList<String> datos = new LinkedList<>();
                    nusuario = new NUsuario();
                    datos = nusuario.listar();
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE USUARIOS", ToHTML.getTable(datos, campos)));               
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR USUARIO POR ROL ID
//  LISTARUSERFILTRADOPORROLID:{rol_id}               //:{cod....|.....}
//  LISTARUSERFILTRADOPORROLID:{rol_id}               //:{cod....|.....}
//  LISTARUSERFILTRADOPORROLID:{rol_id}               //:{cod....|.....}
        case "LISTARUSERFILTRADOPORROLID": 
            if (parametros.size() == 2) {
                String rol_id = parametros.get(1);
                if(validar.isNumber(rol_id)){
                    nusuario = new NUsuario();
                    nrol = new NRol();
                    if (nrol.existe(rol_id)){
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("ID");
                        campos.add("NOMBRES");
                        campos.add("APELLIDOS");
                        campos.add("GENERO");
                        campos.add("FECHA_DE_NACIMIENTO");
                        campos.add("DIRECCION_DE_VIVIENDA");
                        campos.add("CARNET_DE_IDENTIDAD");
                        campos.add("EMAIL");
                        campos.add("NOMBRE_DEL_ROL");
                        LinkedList<String> datos = new LinkedList<>();
                        datos = nusuario.listar_por_rol_id(Integer.parseInt(rol_id));
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE USUARIOS FILTRADO POR ROL ID: "+rol_id, ToHTML.getTable(datos, campos)));
                    } else {
                        System.out.println("ERROR: EL ID DE ROL NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE ROL NO EXISTE", ToHTML.getHTMLMessage("EL ROL ID: "+rol_id+", utilizado para listar usuarios no se encuentra registrado", "Asegurese que este ingresando un id ROL Existente para listar a los usuarios"));
                    }
                }else{
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos introducidos sean de formato correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//  REPORTE AUXILIAR X CARGA HORARIA 
//  REPORAUXCAR: Todos 
//  REPORAUXCAR: *
        case "REPORAUXCAR":
            if (parametros.size() == 2) {
                String todos = parametros.get(1);
                if (validar.isAll(todos)){        
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("AUXILIAR");
                        campos.add("CANTIDAD");
                        campos.add("HORAS");
                        LinkedList<String> datos = new LinkedList<>();
                        nreporte = new NReporte();
                        datos = nreporte.ReporteMateriaGrupo(todos);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("MOSTRAR DATOS DE TUS CLIENTES", ToHTML.getTable(datos, campos)));
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos introducidos sean de formato correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR USUARIO FILTRADO POR NOMBRE DE ROL
//  LISTARUSERFILTRADOPORNOMBREDEROL:{rol_nombre}               //:{cod....|.....}
//  LISTARUSERFILTRADOPORNOMBREDEROL:{rol_nombre}               //:{cod....|.....}
//  LISTARUSERFILTRADOPORNOMBREDEROL:{rol_nombre}               //:{cod....|.....}
        case "LISTARUSERFILTRADOPORNOMBREDEROL": 
            if (parametros.size() == 2) {
                String rol_nombre = parametros.get(1).replace('_',' ');
                if(validar.isNameText(rol_nombre)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("ID");
                    campos.add("NOMBRES");
                    campos.add("APELLIDOS");
                    campos.add("GENERO");
                    campos.add("FECHA_DE_NACIMIENTO");
                    campos.add("DIRECCION_DE_VIVIENDA");
                    campos.add("CARNET_DE_IDENTIDAD");
                    campos.add("EMAIL");
                    campos.add("NOMBRE_DEL_ROL");
                    LinkedList<String> datos = new LinkedList<>();
                    nusuario = new NUsuario();
                    datos = nusuario.listar_por_rol_nombre(rol_nombre);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE USUARIOS FILTRADO POR NOMBRE DE ROL: "+rol_nombre, ToHTML.getTable(datos, campos)));
                }
                else {
                    System.out.println("ERROR: EL NOMBRE DE ROL: "+rol_nombre+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL NOMBRE DE ROL: "+rol_nombre+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El nombre de rol introducido no tiene el formato correcto para poder buscar en el listado de usuarios", "Verifique que el nombre de rol introducido sean de formato correcto para poder buscar dentro del listado de usuarios"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR USUARIO FILTRADO POR NOMBRE DE USUARIO
//  LISTARUSERFILTRADOPORNOMBREDEUSUARIO:{user_nombres}               //:{cod....|.....}
//  LISTARUSERFILTRADOPORNOMBREDEUSUARIO:{user_nombres}               //:{cod....|.....}
//  LISTARUSERFILTRADOPORNOMBREDEUSUARIO:{user_nombres}               //:{cod....|.....}
        case "LISTARUSERFILTRADOPORNOMBREDEUSUARIO": 
            if (parametros.size() == 2) {
                String user_nombres = parametros.get(1).replace('_',' ');
                if(validar.isNameText(user_nombres)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("ID");
                    campos.add("NOMBRES");
                    campos.add("APELLIDOS");
                    campos.add("GENERO");
                    campos.add("FECHA_DE_NACIMIENTO");
                    campos.add("DIRECCION_DE_VIVIENDA");
                    campos.add("CARNET_DE_IDENTIDAD");
                    campos.add("EMAIL");
                    campos.add("NOMBRE_DEL_ROL");
                    LinkedList<String> datos = new LinkedList<>();
                    nusuario = new NUsuario();
                    datos = nusuario.buscar_usuario_nombre(user_nombres);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE USUARIOS FILTRADO POR NOMBRE DE USUARIO: "+user_nombres, ToHTML.getTable(datos, campos)));
                }else {
                    System.out.println("ERROR: EL NOMBRE DE USUARIO: "+user_nombres+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL NOMBRE DE USUARIO: "+user_nombres+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El nombre de usuario introducido no tiene el formato correcto para poder buscar en el listado de usuarios", "Verifique que el nombre de usuario introducido sean de formato correcto para poder buscar dentro del listado de usuarios"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR USUARIO FILTRADO POR NOMBRE DE USUARIO
//  LISTARUSERFILTRADOPORAPELLIDODEUSUARIO:{user_apellido}               //:{cod....|.....}
//  LISTARUSERFILTRADOPORAPELLIDODEUSUARIO:{user_apellido}               //:{cod....|.....}
//  LISTARUSERFILTRADOPORAPELLIDODEUSUARIO:{user_apellido}               //:{cod....|.....}
        case "LISTARUSERFILTRADOPORAPELLIDODEUSUARIO": 
            if (parametros.size() == 2) {
                String user_apellido = parametros.get(1).replace('_',' ');
                if(validar.isNameText(user_apellido)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("ID");
                    campos.add("NOMBRES");
                    campos.add("APELLIDOS");
                    campos.add("GENERO");
                    campos.add("FECHA_DE_NACIMIENTO");
                    campos.add("DIRECCION_DE_VIVIENDA");
                    campos.add("CARNET_DE_IDENTIDAD");
                    campos.add("EMAIL");
                    campos.add("NOMBRE_DEL_ROL");
                    LinkedList<String> datos = new LinkedList<>();
                    nusuario = new NUsuario();
                    datos = nusuario.buscar_usuario_apellido(user_apellido);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE USUARIOS FILTRADO POR APELLIDO DE USUARIO: "+user_apellido, ToHTML.getTable(datos, campos)));
                }else {
                    System.out.println("ERROR: EL APELLIDO DE USUARIO: "+user_apellido+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL APELLIDO DE USUARIO: "+user_apellido+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El apellido de usuario introducido no tiene el formato correcto para poder buscar en el listado de usuarios", "Verifique que el apellido de usuario introducido sean de formato correcto para poder buscar dentro del listado de usuarios"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CONVOCATORIA
//  LISTARCONVOCATORIA               //:{cod....|.....}
//  LISTARCONVOCATORIA               //:{cod....|.....}
//  LISTARCONVOCATORIA               //:{cod....|.....}
        case "LISTARCONVOCATORIA": 
            if (parametros.size() == 1) {
                LinkedList<String> campos = new LinkedList<>();
                campos.add("NRO");
                campos.add("NOMBRE");
                campos.add("DESCRIPCION");
                campos.add("ACTOR");
                campos.add("ACTIVIDAD_CREADA_POR_NOMBRES");
                campos.add("ACTIVIDAD_CREADA_POR_APELLIDOS");
                campos.add("ACTIVIDAD_DEL_PERIODO");
                campos.add("FECHA_DE_INICIO");
                campos.add("FECHA_FIN");
                campos.add("HORA_DE_INICIO");
                campos.add("HORA_FIN");
                LinkedList<String> datos = new LinkedList<>();
                nactividad = new NActividad();
                datos = nactividad.listar();
                salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CONVOCATORIA", ToHTML.getTable(datos, campos)));
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CONVOCATORIA FILTRADO POR PERIODO ID
//  LISTARCONVOCATORIAFILTRADOPORPERIODOID:{periodo_id}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORPERIODOID:{periodo_id}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORPERIODOID:{periodo_id}               //:{cod....|.....}
        case "LISTARCONVOCATORIAFILTRADOPORPERIODOID": 
            if (parametros.size() == 2) {
                String periodo_id = parametros.get(1);
                if(validar.isNumber(periodo_id)){
                    nactividad = new NActividad();
                    if (nactividad.existe(periodo_id)){
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("NRO");
                        campos.add("NOMBRE");
                        campos.add("DESCRIPCION");
                        campos.add("ACTOR");
                        campos.add("ACTIVIDAD_CREADA_POR_NOMBRES");
                        campos.add("ACTIVIDAD_CREADA_POR_APELLIDOS");
                        campos.add("ACTIVIDAD_DEL_PERIODO");
                        campos.add("FECHA_DE_INICIO");
                        campos.add("FECHA_FIN");
                        campos.add("HORA_DE_INICIO");
                        campos.add("HORA_FIN");
                        LinkedList<String> datos = new LinkedList<>();
                        
                        datos = nactividad.listar_por_periodo_id(Integer.parseInt(periodo_id));
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CONVOCATORIA FILTRADO POR PERIODO ID: "+periodo_id, ToHTML.getTable(datos, campos)));
                    } else {
                        System.out.println("ERROR: EL ID DE PERIODO NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE PERIODO NO EXISTE", ToHTML.getHTMLMessage("EL PERIODO ID: "+periodo_id+", utilizado para listar convocatoria, no se encuentra registrado", "Asegurese que este ingresando un id PERIODO Existente para listar a las convocatoria"));
                    }
                }else{
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos introducidos sean de formato correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//  REPORTE MATERIA X APROBADOS 
//  REPORMATGRUP: Todos 
//  REPORMATGRUP: *
        case "REPORMATGRUP":
            if (parametros.size() == 2) {
                String todos = parametros.get(1);
                if (validar.isAll(todos)){        
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("MATERIA");
                        campos.add("GRUPO");
                        campos.add("CANTIDAD");
                        LinkedList<String> datos = new LinkedList<>();
                        nreporte = new NReporte();
                        datos = nreporte.ReporteMateriaAprobados(todos);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("MOSTRAR DATOS DE TUS CLIENTES", ToHTML.getTable(datos, campos)));
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos introducidos sean de formato correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CONVOCATORIA FILTRADO POR PERIODO NOMBRE
//  LISTARCONVOCATORIAFILTRADOPORPERIODONOMBRE:{periodo_nombre}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORPERIODONOMBRE:{periodo_nombre}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORPERIODONOMBRE:{periodo_nombre}               //:{cod....|.....}
        case "LISTARCONVOCATORIAFILTRADOPORPERIODONOMBRE": 
            if (parametros.size() == 2) {
                String periodo_nombre = parametros.get(1).replace('_',' ');
                LinkedList<String> campos = new LinkedList<>();
                campos.add("NRO");
                campos.add("NOMBRE");
                campos.add("DESCRIPCION");
                campos.add("ACTOR");
                campos.add("ACTIVIDAD_CREADA_POR_NOMBRES");
                campos.add("ACTIVIDAD_CREADA_POR_APELLIDOS");
                campos.add("ACTIVIDAD_DEL_PERIODO");
                campos.add("FECHA_DE_INICIO");
                campos.add("FECHA_FIN");
                campos.add("HORA_DE_INICIO");
                campos.add("HORA_FIN");
                LinkedList<String> datos = new LinkedList<>();
                nactividad = new NActividad();
                datos = nactividad.listar_por_periodo_nombre(periodo_nombre);
                salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CONVOCATORIAS FILTRADO POR NOMBRE DE PERIODO: "+periodo_nombre, ToHTML.getTable(datos, campos)));
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CONVOCATORIA FILTRADO POR NOMBRE CREADOR
//  LISTARCONVOCATORIAFILTRADOPORNOMBRECREADOR:{nombre_creador}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORNOMBRECREADOR:{nombre_creador}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORNOMBRECREADOR:{nombre_creador}               //:{cod....|.....}
        case "LISTARCONVOCATORIAFILTRADOPORNOMBRECREADOR": 
            //if (parametros.size() == 3) {
            if (parametros.size() == 2) {
                String nombre_creador = parametros.get(1).replace('_',' ');
                if(validar.isNameText(nombre_creador)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("NRO");
                    campos.add("NOMBRE");
                    campos.add("DESCRIPCION");
                    campos.add("ACTOR");
                    campos.add("ACTIVIDAD_CREADA_POR_NOMBRES");
                    campos.add("ACTIVIDAD_CREADA_POR_APELLIDOS");
                    campos.add("ACTIVIDAD_DEL_PERIODO");
                    campos.add("FECHA_DE_INICIO");
                    campos.add("FECHA_FIN");
                    campos.add("HORA_DE_INICIO");
                    campos.add("HORA_FIN");
                    LinkedList<String> datos = new LinkedList<>();
                    nactividad = new NActividad();
                    datos = nactividad.listar_por_actividad_por_nombres_creador(nombre_creador);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CONVOCATORIA FILTRADO POR NOMBRE CREADOR: "+nombre_creador, ToHTML.getTable(datos, campos)));
                }else {
                    System.out.println("ERROR: EL NOMBRE DE CREADOR: "+nombre_creador+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL NOMBRE DE CREADOR: "+nombre_creador+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El nombre de creador introducido no tiene el formato correcto para poder buscar las convocatorias", "Verifique que el nombre de creador introducido sean de formato correcto para poder buscar las convocatorias"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CONVOCATORIA FILTRADO POR APELLIDO CREADOR
//  LISTARCONVOCATORIAFILTRADOPORAPELLIDOCREADOR:{apellido_creador}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORAPELLIDOCREADOR:{apellido_creador}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORAPELLIDOCREADOR:{apellido_creador}               //:{cod....|.....}
        case "LISTARCONVOCATORIAFILTRADOPORAPELLIDOCREADOR": 
            if (parametros.size() == 2) {
                String apellido_creador = parametros.get(1).replace('_',' ');
                if(validar.isNameText(apellido_creador)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("NRO");
                    campos.add("NOMBRE");
                    campos.add("DESCRIPCION");
                    campos.add("ACTOR");
                    campos.add("ACTIVIDAD_CREADA_POR_NOMBRES");
                    campos.add("ACTIVIDAD_CREADA_POR_APELLIDOS");
                    campos.add("ACTIVIDAD_DEL_PERIODO");
                    campos.add("FECHA_DE_INICIO");
                    campos.add("FECHA_FIN");
                    campos.add("HORA_DE_INICIO");
                    campos.add("HORA_FIN");
                    LinkedList<String> datos = new LinkedList<>();
                    nactividad = new NActividad();
                    datos = nactividad.listar_por_actividad_por_apellidos_creador(apellido_creador);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CONVOCATORIA FILTRADO POR APELLIDO CREADOR: "+apellido_creador, ToHTML.getTable(datos, campos)));
                }else {
                    System.out.println("ERROR: EL APELLIDO DE CREADOR: "+apellido_creador+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL APELLIDO DE CREADOR: "+apellido_creador+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El apellido de creador introducido no tiene el formato correcto para poder buscar las convocatorias", "Verifique que el apellido de creador introducido sean de formato correcto para poder buscar las convocatorias"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CONVOCATORIA FILTRADO POR ID DEL CREADOR
//  LISTARCONVOCATORIAFILTRADOPORIDDELCREADOR:{id_creador}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORIDDELCREADOR:{id_creador}               //:{cod....|.....}
//  LISTARCONVOCATORIAFILTRADOPORIDDELCREADOR:{id_creador}               //:{cod....|.....}
        case "LISTARCONVOCATORIAFILTRADOPORIDDELCREADOR": 
            //if (parametros.size() == 2) {
            if (parametros.size() == 2) {
                String id_creador = parametros.get(1);
                if(validar.isNumber(id_creador)){
                    nactividad = new NActividad();
                    if (nactividad.existe(id_creador)){
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("NRO");
                        campos.add("NOMBRE");
                        campos.add("DESCRIPCION");
                        campos.add("ACTOR");
                        campos.add("ACTIVIDAD_CREADA_POR_NOMBRES");
                        campos.add("ACTIVIDAD_CREADA_POR_APELLIDOS");
                        campos.add("ACTIVIDAD_DEL_PERIODO");
                        campos.add("FECHA_DE_INICIO");
                        campos.add("FECHA_FIN");
                        campos.add("HORA_DE_INICIO");
                        campos.add("HORA_FIN");
                        LinkedList<String> datos = new LinkedList<>();
                        datos = nactividad.listar_por_id_del_creador(Integer.parseInt(id_creador));
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CONVOCATORIA FILTRADO POR ID DEL CREADOR: "+id_creador, ToHTML.getTable(datos, campos)));                   
                    } else {
                        System.out.println("ERROR: EL ID DE CREADOR NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE CREADOR NO EXISTE", ToHTML.getHTMLMessage("EL CREADOR ID: "+id_creador+", utilizado para listar convocatoria, no se encuentra registrado", "Asegurese que este ingresando un id CREADOR Existente para listar a las convocatoria"));
                    }
                }else{
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos introducidos sean de formato correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
      
        case "REPORMATAPRO":
           if (parametros.size() == 2) {
                String todos = parametros.get(1);
                if (validar.isAll(todos)){        
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("MATERIA");
                        campos.add("CANTIDAD");
                        campos.add("APROBADOS");
                        LinkedList<String> datos = new LinkedList<>();
                        nreporte = new NReporte();
                        datos = nreporte.ReporteAuxiliarCarga(todos);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("MOSTRAR DATOS DE TUS CLIENTES", ToHTML.getTable(datos, campos)));
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los datos introducidos sean de formato correcto"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CARGA HORARIA AUXILIARES
//  LISTARCARGAHORARIAAUXILIARES               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARES               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARES               //:{cod....|.....}
        case "LISTARCARGAHORARIAAUXILIARES": 
            if (parametros.size() == 1) {
                LinkedList<String> campos = new LinkedList<>();
                campos.add("CODIGO_AUXILIAR");
                campos.add("NOMBRES_AUXILIAR");
                campos.add("APELLIDOS_AUXILIAR");
                campos.add("HORAS_ASIGNADAS");
                campos.add("HORAS_ADICIONALES");
                campos.add("SIGLA_MATERIA");
                campos.add("SIGLA_GRUPO");
                campos.add("NOMBRES_DOCENTE");
                campos.add("APELLIDOS_DOCENTE");
                LinkedList<String> datos = new LinkedList<>();
                ncargahorariagrupo = new NCargaHorariaGrupo();
                datos = ncargahorariagrupo.listar();
                salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CARGA HORARIA AUXILIARES", ToHTML.getTable(datos, campos)));
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CARGA HORARIA AUXILIARES FILTRADO POR NOMBRE DE AUXILIAR
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEAUXILIAR:{nombre_de_auxiliar}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEAUXILIAR:{nombre_de_auxiliar}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEAUXILIAR:{nombre_de_auxiliar}               //:{cod....|.....}
        case "LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEAUXILIAR": 
            if (parametros.size() == 2) {
                String nombre_de_auxiliar = parametros.get(1).replace('_',' ');
                if(validar.isNameText(nombre_de_auxiliar)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("CODIGO_AUXILIAR");
                    campos.add("NOMBRES_AUXILIAR");
                    campos.add("APELLIDOS_AUXILIAR");
                    campos.add("HORAS_ASIGNADAS");
                    campos.add("HORAS_ADICIONALES");
                    campos.add("SIGLA_MATERIA");
                    campos.add("SIGLA_GRUPO");
                    campos.add("NOMBRES_DOCENTE");
                    campos.add("APELLIDOS_DOCENTE");
                    LinkedList<String> datos = new LinkedList<>();
                    ncargahorariagrupo = new NCargaHorariaGrupo();
                    datos = ncargahorariagrupo.listar_por_nombres_de_auxiliar(nombre_de_auxiliar);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CARGA HORARIA AUXILIARES FILTRADO POR NOMBRE DE AUXILIAR: "+nombre_de_auxiliar, ToHTML.getTable(datos, campos)));
                }else {
                    System.out.println("ERROR: EL NOMBRE DE AUXILIAR: "+nombre_de_auxiliar+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL NOMBRE DE AUXILIAR: "+nombre_de_auxiliar+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El nombre de auxiliar introducido no tiene el formato correcto para poder buscar las cargas horarias de auxiliares", "Verifique que el nombre de auxiliar introducido sean de formato correcto para poder buscar las cargas horarias de auxiliares"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CARGA HORARIA AUXILIARES FILTRADO POR APELLIDO DE AUXILIAR
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEAUXILIAR:{apellido_de_auxiliar}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEAUXILIAR:{apellido_de_auxiliar}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEAUXILIAR:{apellido_de_auxiliar}               //:{cod....|.....}
        case "LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEAUXILIAR": 
            //if (parametros.size() == 3) {
            if (parametros.size() == 2) {
                String apellido_de_auxiliar = parametros.get(1).replace('_',' ');
                if(validar.isNameText(apellido_de_auxiliar)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("CODIGO_AUXILIAR");
                    campos.add("NOMBRES_AUXILIAR");
                    campos.add("APELLIDOS_AUXILIAR");
                    campos.add("HORAS_ASIGNADAS");
                    campos.add("HORAS_ADICIONALES");
                    campos.add("SIGLA_MATERIA");
                    campos.add("SIGLA_GRUPO");
                    campos.add("NOMBRES_DOCENTE");
                    campos.add("APELLIDOS_DOCENTE");
                    LinkedList<String> datos = new LinkedList<>();
                    ncargahorariagrupo = new NCargaHorariaGrupo();
                    datos = ncargahorariagrupo.listar_por_apellidos_de_auxiliar(apellido_de_auxiliar);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CARGA HORARIA AUXILIARES FILTRADO POR APELLIDO DE AUXILIAR: "+apellido_de_auxiliar, ToHTML.getTable(datos, campos)));
                }else {
                    System.out.println("ERROR: EL APELLIDO DE AUXILIAR: "+apellido_de_auxiliar+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL APELLIDO DE AUXILIAR: "+apellido_de_auxiliar+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El apellido de auxiliar introducido no tiene el formato correcto para poder buscar las cargas horarias de auxiliares", "Verifique que el apellido de auxiliar introducido sean de formato correcto para poder buscar las cargas horarias de auxiliares"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;        
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CARGA HORARIA AUXILIARES FILTRADO POR NOMBRE DE DOCENTE
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEDOCENTE:{nombre_de_docente}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEDOCENTE:{nombre_de_docente}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEDOCENTE:{nombre_de_docente}               //:{cod....|.....}
        case "LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEDOCENTE": 
            if (parametros.size() == 2) {
                String nombre_de_docente = parametros.get(1).replace('_',' ');
                if(validar.isNameText(nombre_de_docente)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("CODIGO_AUXILIAR");
                    campos.add("NOMBRES_AUXILIAR");
                    campos.add("APELLIDOS_AUXILIAR");
                    campos.add("HORAS_ASIGNADAS");
                    campos.add("HORAS_ADICIONALES");
                    campos.add("SIGLA_MATERIA");
                    campos.add("SIGLA_GRUPO");
                    campos.add("NOMBRES_DOCENTE");
                    campos.add("APELLIDOS_DOCENTE");
                    LinkedList<String> datos = new LinkedList<>();
                    ncargahorariagrupo = new NCargaHorariaGrupo();
                    datos = ncargahorariagrupo.listar_por_nombres_de_docente(nombre_de_docente);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CARGA HORARIA AUXILIARES FILTRADO POR NOMBRE DE DOCENTE: "+nombre_de_docente, ToHTML.getTable(datos, campos)));
                }else {
                    System.out.println("ERROR: EL NOMBRE DE DOCENTE: "+nombre_de_docente+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL NOMBRE DE DOCENTE: "+nombre_de_docente+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El nombre de docente introducido no tiene el formato correcto para poder buscar las cargas horarias de auxiliares", "Verifique que el nombre de docente introducido sean de formato correcto para poder buscar las cargas horarias de auxiliares"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CARGA HORARIA AUXILIARES FILTRADO POR APELLIDO DE DOCENTE
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEDOCENTE:{apellido_de_docente}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEDOCENTE:{apellido_de_docente}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEDOCENTE:{apellido_de_docente}               //:{cod....|.....}
        case "LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEDOCENTE": 
            if (parametros.size() == 2) {
                String apellido_de_docente = parametros.get(1).replace('_',' ');
                if(validar.isNameText(apellido_de_docente)){
                    LinkedList<String> campos = new LinkedList<>();
                    campos.add("CODIGO_AUXILIAR");
                    campos.add("NOMBRES_AUXILIAR");
                    campos.add("APELLIDOS_AUXILIAR");
                    campos.add("HORAS_ASIGNADAS");
                    campos.add("HORAS_ADICIONALES");
                    campos.add("SIGLA_MATERIA");
                    campos.add("SIGLA_GRUPO");
                    campos.add("NOMBRES_DOCENTE");
                    campos.add("APELLIDOS_DOCENTE");
                    LinkedList<String> datos = new LinkedList<>();
                    ncargahorariagrupo = new NCargaHorariaGrupo();
                    datos = ncargahorariagrupo.listar_por_apellidos_de_docente(apellido_de_docente);
                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CARGA HORARIA AUXILIARES FILTRADO POR APELLIDO DE DOCENTE: "+apellido_de_docente, ToHTML.getTable(datos, campos)));
                }else {
                    System.out.println("ERROR: EL APELLIDO DE DOCENTE: "+apellido_de_docente+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "EL APELLIDO DE DOCENTE: "+apellido_de_docente+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El apellido de docente introducido no tiene el formato correcto para poder buscar las cargas horarias de auxiliares", "Verifique que el apellido de docente introducido sean de formato correcto para poder buscar las cargas horarias de auxiliares"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CARGA HORARIA AUXILIARES FILTRADO POR SIGLA MATERIA
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAMATERIA:{sigla_materia}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAMATERIA:{sigla_materia}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAMATERIA:{sigla_materia}               //:{cod....|.....}
        case "LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAMATERIA": 
            if (parametros.size() == 2) {
                String sigla_materia = parametros.get(1).replace('_',' ');
                LinkedList<String> campos = new LinkedList<>();
                campos.add("CODIGO_AUXILIAR");
                campos.add("NOMBRES_AUXILIAR");
                campos.add("APELLIDOS_AUXILIAR");
                campos.add("HORAS_ASIGNADAS");
                campos.add("HORAS_ADICIONALES");
                campos.add("SIGLA_MATERIA");
                campos.add("SIGLA_GRUPO");
                campos.add("NOMBRES_DOCENTE");
                campos.add("APELLIDOS_DOCENTE");
                LinkedList<String> datos = new LinkedList<>();
                ncargahorariagrupo = new NCargaHorariaGrupo();
                datos = ncargahorariagrupo.listar_por_sigla_materia(sigla_materia);
                salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CARGA HORARIA AUXILIARES FILTRADO POR SIGLA MATERIA: "+sigla_materia, ToHTML.getTable(datos, campos)));
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CARGA HORARIA AUXILIARES FILTRADO POR SIGLA GRUPO
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAGRUPO:{sigla_grupo}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAGRUPO:{sigla_grupo}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAGRUPO:{sigla_grupo}               //:{cod....|.....}
        case "LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAGRUPO": 
            if (parametros.size() == 2) {
                String sigla_grupo = parametros.get(1).replace('_',' ');
                LinkedList<String> campos = new LinkedList<>();
                campos.add("CODIGO_AUXILIAR");
                campos.add("NOMBRES_AUXILIAR");
                campos.add("APELLIDOS_AUXILIAR");
                campos.add("HORAS_ASIGNADAS");
                campos.add("HORAS_ADICIONALES");
                campos.add("SIGLA_MATERIA");
                campos.add("SIGLA_GRUPO");
                campos.add("NOMBRES_DOCENTE");
                campos.add("APELLIDOS_DOCENTE");
                LinkedList<String> datos = new LinkedList<>();
                ncargahorariagrupo = new NCargaHorariaGrupo();
                datos = ncargahorariagrupo.listar_por_sigla_grupo(sigla_grupo);
                salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CARGA HORARIA AUXILIARES FILTRADO POR SIGLA GRUPO: "+sigla_grupo, ToHTML.getTable(datos, campos)));
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;        
//----------------------------------------------------------------------------------------------------------------------
//  LISTAR CARGA HORARIA AUXILIARES FILTRADO POR HORAS ASIGNADAS
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORHORASASIGNADAS:{horas_asignadas}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORHORASASIGNADAS:{horas_asignadas}               //:{cod....|.....}
//  LISTARCARGAHORARIAAUXILIARESFILTRADOPORHORASASIGNADAS:{horas_asignadas}               //:{cod....|.....}
        case "LISTARCARGAHORARIAAUXILIARESFILTRADOPORHORASASIGNADAS": 
            if (parametros.size() == 2) {
                String horas_asignadas = parametros.get(1).replace('_',' ');
                LinkedList<String> campos = new LinkedList<>();
                campos.add("CODIGO_AUXILIAR");
                campos.add("NOMBRES_AUXILIAR");
                campos.add("APELLIDOS_AUXILIAR");
                campos.add("HORAS_ASIGNADAS");
                campos.add("HORAS_ADICIONALES");
                campos.add("SIGLA_MATERIA");
                campos.add("SIGLA_GRUPO");
                campos.add("NOMBRES_DOCENTE");
                campos.add("APELLIDOS_DOCENTE");
                LinkedList<String> datos = new LinkedList<>();
                ncargahorariagrupo = new NCargaHorariaGrupo();
                datos = ncargahorariagrupo.listar_por_horas_asignadas(horas_asignadas);
                salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("LISTADO DE CARGA HORARIA AUXILIARES FILTRADO POR HORAS ASIGNADAS: "+horas_asignadas, ToHTML.getTable(datos, campos)));
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;        
        
        
//----------------------------------------------------------------------------------------------------------------------
//  VER USUARIO
//  VERUSUARIO:id
//  VERUSUARIO:3
        case "VERUSUARIO":
            if (parametros.size() == 2) {
                String id = parametros.get(1);
                if (validar.isNumber(id)){ 
                    nusuario = new NUsuario();
                    if (nusuario.existe(id)){
                        LinkedList<String> datos = new LinkedList<>();
                        datos = nusuario.mostrar(Integer.parseInt(id));
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("ID");
                        campos.add("NOMBRES");
                        campos.add("APELLIDOS");
                        campos.add("GENERO");
                        campos.add("FECHA_DE_NACIMIENTO");
                        campos.add("DIRECCION_DE_VIVIENDA");
                        campos.add("CARNET_DE_IDENTIDAD");
                        campos.add("EMAIL");
                        campos.add("NOMBRE_DEL_ROL");
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("MOSTRAR USUARIO CON ID: "+id, ToHTML.getTable(datos, campos)));                        
                    
                    } else {
                        System.out.println("ERROR: EL ID DE USUARIO NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE USUARIO NO EXISTE", ToHTML.getHTMLMessage("El ID: "+id+", utilizado no se encuentra registrado en Usuario", "Asegurese que este ingresando un id registrado en los usuarios"));
                    }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los codigos contenga algun dato"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  VER CONVOCATORIA
//  VERCONVOCATORIA:id
//  VERCONVOCATORIA:3
        case "VERCONVOCATORIA":
            if (parametros.size() == 2) {
                String id = parametros.get(1);
                if (validar.isNumber(id)){ 
                    nactividad = new NActividad();
                    if (nactividad.existe(id)){
                        LinkedList<String> datos = new LinkedList<>();
                        datos = nactividad.mostrar(Integer.parseInt(id));
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("NRO");
                        campos.add("NOMBRE");
                        campos.add("DESCRIPCION");
                        campos.add("ACTOR");
                        campos.add("ACTIVIDAD_CREADA_POR_NOMBRES");
                        campos.add("ACTIVIDAD_CREADA_POR_APELLIDOS");
                        campos.add("ACTIVIDAD_DEL_PERIODO");
                        campos.add("FECHA_DE_INICIO");
                        campos.add("FECHA_FIN");
                        campos.add("HORA_DE_INICIO");
                        campos.add("HORA_FIN");
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("MOSTRAR CONVOCATORIA CON ID: "+id, ToHTML.getTable(datos, campos)));
                    } else {
                        System.out.println("ERROR: EL ID DE CONVOCATORIA NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE CONVOCATORIA NO EXISTE", ToHTML.getHTMLMessage("El ID: "+id+", utilizado no se encuentra registrado en Convocatoria", "Asegurese que este ingresando un id registrado en las convocatorias"));
                    }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los codigos contenga algun dato"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  VER CARGA HORARIA AUXILIAR
//  VERCARGAHORARIAAUXILIAR:id
//  VERCARGAHORARIAAUXILIAR:3
        case "VERCARGAHORARIAAUXILIAR":
            if (parametros.size() == 2) {
                String id = parametros.get(1);
                if (validar.isNumber(id)){ 
                    ncargahorariagrupo = new NCargaHorariaGrupo();
                    if (ncargahorariagrupo.existe(id)){
                        LinkedList<String> datos = new LinkedList<>();
                        datos = ncargahorariagrupo.mostrar(Integer.parseInt(id));
                        LinkedList<String> campos = new LinkedList<>();
                        campos.add("CODIGO_AUXILIAR");
                        campos.add("NOMBRES_AUXILIAR");
                        campos.add("APELLIDOS_AUXILIAR");
                        campos.add("HORAS_ASIGNADAS");
                        campos.add("HORAS_ADICIONALES");
                        campos.add("SIGLA_MATERIA");
                        campos.add("SIGLA_GRUPO");
                        campos.add("NOMBRES_DOCENTE");
                        campos.add("APELLIDOS_DOCENTE");
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("MOSTRAR CARGA HORARIA AUXILIAR CON ID: "+id, ToHTML.getTable(datos, campos)));
                    } else {
                        System.out.println("ERROR: EL ID DE CARGA HORARIA AUXILIAR NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE CARGA HORARIA AUXILIAR NO EXISTE", ToHTML.getHTMLMessage("El ID: "+id+", utilizado no se encuentra registrado en carga horaria auxiliar", "Asegurese que este ingresando un id registrado en las cargas horarias auxiliares"));
                    }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los codigos contenga algun dato"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  DAR DE BAJA USUARIO
//  DARDEBAJAUSUARIO:id
//  DARDEBAJAUSUARIO:3
        case "DARDEBAJAUSUARIO":
            if (parametros.size() == 2) {
                String id = parametros.get(1);
                if (validar.isNumber(id)){ 
                    nusuario = new NUsuario();
                    if (nusuario.existe(id)){
                        nusuario.eliminar(Integer.parseInt(id));
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Comando: " + comando + " enviado, el usuario de id: "+id+" fue dado de baja exitosamente", "El comando " + comando + " se envio exitosamente"));
                    } else {
                        System.out.println("ERROR: EL ID DE USUARIO NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE USUARIO NO EXISTE", ToHTML.getHTMLMessage("El ID: "+id+", utilizado no se encuentra registrado en usuario", "Asegurese que este ingresando un id registrado en los usuarios"));
                    }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los codigos contenga algun dato"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  DAR DE BAJA CONVOCATORIA
//  DARDEBAJACONVOCATORIA:id
//  DARDEBAJACONVOCATORIA:3
        case "DARDEBAJACONVOCATORIA":
            if (parametros.size() == 2) {
                String id = parametros.get(1);
                if (validar.isNumber(id)){ 
                    nactividad = new NActividad();
                    if (nactividad.existe(id)){
                        nactividad.eliminar(Integer.parseInt(id));
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Comando: " + comando + " enviado, la convocatoria con id: "+id+ " fue dada de baja", "El comando " + comando + " se envio exitosamente"));
                    } else {
                        System.out.println("ERROR: EL ID DE CONVOCATORIA NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE CONVOCATORIA NO EXISTE", ToHTML.getHTMLMessage("El ID: "+id+", utilizado no se encuentra registrado en convocatoria", "Asegurese que este ingresando un id registrado en las convocatorias"));
                    }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los codigos contenga algun dato"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//----------------------------------------------------------------------------------------------------------------------
//  DAR DE BAJA CARGA HORARIA AUXILIAR
//  DARDEBAJACARGAHORARIAAUXILIAR:id
//  DARDEBAJACARGAHORARIAAUXILIAR:3
        case "DARDEBAJACARGAHORARIAAUXILIAR":
            if (parametros.size() == 2) {
                String id = parametros.get(1);
                if (validar.isNumber(id)){ 
                    ncargahorariagrupo = new NCargaHorariaGrupo();
                    if (ncargahorariagrupo.existe(id)){
                        ncargahorariagrupo.eliminar(Integer.parseInt(id));
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Comando: " + comando + " enviado, la carga horaria auxiliares con id: "+id+" fue dado de baja", "El comando " + comando + " se envio exitosamente"));
                    } else {
                        System.out.println("ERROR: EL ID DE CARGA HORARIA AUXILIAR NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE CARGA HORARIA AUXILIAR NO EXISTE", ToHTML.getHTMLMessage("El ID: "+id+", utilizado no se encuentra registrado en carga horaria auxiliar", "Asegurese que este ingresando un id registrado en las cargas horarias auxiliar"));
                    }
                } else {
                    System.out.println("ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO");
                    salida.sendHtmlEmail(from, "ERROR: LOS DATOS INTRODUCIDOS NO TIENEN EL FORMATO CORRECTO", ToHTML.getHTMLMessage("Los datos introducidos no tiene el formato correcto", "Verifique que los codigos contenga algun dato"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
        
        
        
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  REGISTRAR USUARIO
//  REGISTRARUSUARIO:nombres:apellidos:genero:fecha_de_nacimiento:foto:carnet_de_identidad:direccion_de_vivienda:rol_id:email:password
//  REGISTRARUSUARIO:erick_denis:mercado_oudalova:M:1998-01-16:url_foto:111111:calle_las_petas:1:e1@gmail.com:123123
        case "REGISTRARUSUARIO":
            if (parametros.size() == 11) {
                String nombres = parametros.get(1).replace('_',' ');
                String apellidos = parametros.get(2).replace('_',' ');
                String genero = parametros.get(3);
                String fecha_de_nacimiento = parametros.get(4);
                String rol_id = parametros.get(8);
                String email = parametros.get(9);
                System.out.println(email);
                nrol = new NRol();
                if(validar.isNameText(nombres) && validar.isNameText(apellidos)){
                    if(validar.isSexo(genero)){
                        if(validar.isFecha(fecha_de_nacimiento)){
                            if(validar.isEmail(email)){
                                if (nrol.existe(rol_id)){
                                    LinkedList<String> datos = new LinkedList<>();
                                    for (int i = 1; i < 11; i++) {
                                        datos.add(parametros.get(i).replace('_',' '));
                                    }
                                    nusuario = new NUsuario();
                                    nusuario.insertar(datos);
                                    salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
                                } else {
                                    System.out.println("ERROR: EL ID DE ROL NO EXISTE");
                                    salida.sendHtmlEmail(from, "ERROR: EL ID DE ROL NO EXISTE PARA PODER REGISTRAR USUARIO", ToHTML.getHTMLMessage("El ROL ID: "+rol_id+", utilizado no se encuentra registrado en rol", "Asegurese que este ingresando un id_rol registrado en los roles para poder registrar el usuario"));
                                }
                            } else {
                                System.out.println("ERROR: EL EMAIL: "+email+", INTRODUCIDO NO TIENEN EL FORMATO CORRECTO PARA REGISTRAR UN USUARIO");
                                salida.sendHtmlEmail(from, "EL EMAIL: "+email+", INTRODUCIDO NO TIENEN EL FORMATO CORRECTO PARA REGISTRAR UN USUARIO", ToHTML.getHTMLMessage("El email del usuario introducido no tiene el formato correcto para poder registrar el usuario", "Verifique que el email de usuario introducido sean de formato correcto para poder registrar al usuario"));
                            }
                        } else {
                            System.out.println("ERROR: LA FECHA DE NACIMIENTO: "+fecha_de_nacimiento+", INTRODUCIDO NO TIENEN EL FORMATO CORRECTO PARA REGISTRAR UN USUARIO");
                            salida.sendHtmlEmail(from, "LA FECHA DE NACIMIENTO: "+fecha_de_nacimiento+", INTRODUCIDO NO TIENEN EL FORMATO CORRECTO PARA REGISTRAR UN USUARIO", ToHTML.getHTMLMessage("La fecha de nacimiento del usuario introducido no tiene el formato correcto para poder registrar el usuario", "Verifique que la fecha de nacimiento de usuario introducido sean de formato correcto para poder registrar al usuario"));
                        }
                    } else {
                        System.out.println("ERROR: EL GENERO: "+genero+", INTRODUCIDO NO TIENEN EL FORMATO CORRECTO PARA REGISTRAR UN USUARIO");
                        salida.sendHtmlEmail(from, "EL GENERO: "+genero+", INTRODUCIDO NO TIENEN EL FORMATO CORRECTO PARA REGISTRAR UN USUARIO", ToHTML.getHTMLMessage("El genero del usuario introducido no tiene el formato correcto para poder registrar el usuario", "Verifique que el genero de usuario introducido sean de formato correcto para poder registrar al usuario"));
                    }
                } else {
                    System.out.println("ERROR: EL NOMBRE: "+nombres+" O EL APELLIDO: "+apellidos+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO PARA REGISTRAR UN USUARIO");
                    salida.sendHtmlEmail(from, "EL NOMBRE: "+nombres+" O EL APELLIDO: "+apellidos+",INTRODUCIDO NO TIENEN EL FORMATO CORRECTO PARA REGISTRAR UN USUARIO", ToHTML.getHTMLMessage("El nombre o apellido del usuario introducido no tiene el formato correcto para poder registrar el usuario", "Verifique que el nombre o apellido de usuario introducido sean de formato correcto para poder registrar al usuario"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  REGISTRAR CONVOCATORIA
//  REGISTRARCONVOCATORIA:nombre:descripcion:ator:usuario_id:periodo_id:
//              fecha_inicio1|fecha_fin1|hora_inicio1|hora_fin1|... :
//              fecha_inicio2|fecha_fin2|hora_inicio2|hora_fin2|....:
    
//REGISTRARCONVOCATORIA:nombre_nombre:descripcion_descripcion:Administrativo:9:2:2022-03-13|2022-03-13|01;04;49|03;04;49:2022-03-14|2022-03-14|01;04;49|03;04;49
        case "REGISTRARCONVOCATORIA":
            if (parametros.size() > 5) {
                String usuario_id = parametros.get(4);
                String periodo_id = parametros.get(5);
                nusuario = new NUsuario();
                nperiodo = new NPeriodo();
                if (nusuario.existe(usuario_id)){
                    if (nperiodo.existe(periodo_id)){
                        LinkedList<String> datos = new LinkedList<>();
                        for (int i = 1; i < 6; i++) {
                            datos.add((parametros.get(i).replace('_',' ')));
                        }
                        nactividad = new NActividad();
                        nactividad.insertar(datos);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
                        int id_actividad = nactividad.GetLastID();
                        ncronograma = new NCronograma();
                        if(parametros.size() >= 7){
                            for (int i = 6; i < parametros.size(); i++) {
                                LinkedList<String> datosCronograma = new LinkedList<>();
                                String[] partes = parametros.get(i).split("\\|");
                                if(partes.length==4 && validar.isFecha(partes[0]) && validar.isFecha(partes[1]) && validar.isHoraMinSec(partes[2].replace(';',':')) && validar.isHoraMinSec(partes[3].replace(';',':'))){
                                    datosCronograma.add(partes[0]);
                                    datosCronograma.add(partes[1]);
                                    datosCronograma.add(partes[2].replace(';',':'));
                                    datosCronograma.add(partes[3].replace(';',':'));
                                    datosCronograma.add(Integer.toString(id_actividad));
                                    ncronograma.insertar(datosCronograma);
                                    salida.sendHtmlEmail(from, "EL CRONOGRAMA:"+Arrays.toString(partes)+", SE GUARDO CON EXITO", ToHTML.getHTMLMessage("EL CRONOGRAMA:"+Arrays.toString(partes)+", SE GUARDO CON EXITO", "EL CRONOGRAMA:"+Arrays.toString(partes)+", SE GUARDO CON EXITO"));
                                }else {
                                    System.out.println("ERROR: EL CRONOGRAMA DE LA CONVOCATORIA NO TIENE EL FORMATO CORRECTO");
                                    salida.sendHtmlEmail(from, "ERROR: EL CRONOGRAMA DE LA CONVOCATORIA NO TIENE EL FORMATO CORRECTO", ToHTML.getHTMLMessage("El CRONOGRAMA: "+Arrays.toString(partes)+", utilizado no tiene un formato correcto para registrar en la actividad: "+id_actividad+", los datos para CONVOCATORIA", "Asegurese que este ingresando un formato correcto para registrar en cronograma los datos para la CONVOCATORIA"));
                                }
                            }
                        }
                    } else {
                        System.out.println("ERROR: EL ID DE PERIODO NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE PERIODO NO EXISTE PARA PODER REGISTRAR CONVOCATORIA", ToHTML.getHTMLMessage("El PERIODO ID: "+periodo_id+", utilizado no se encuentra registrado en periodos", "Asegurese que este ingresando un periodo_id registrado en los periodos para poder registrar la convocatoria"));
                    }
                } else {
                    System.out.println("ERROR: EL ID DE USUARIO NO EXISTE");
                    salida.sendHtmlEmail(from, "ERROR: EL ID DE USUARIO NO EXISTE PARA PODER REGISTRAR CONVOCATORIA", ToHTML.getHTMLMessage("El USUARIO ID: "+usuario_id+", utilizado no se encuentra registrado en usuarios", "Asegurese que este ingresando un usuario_id registrado en los usuarios para poder registrar la convocatoria"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  REGISTRAR CARGA HORARIA AUXILIAR
//  REGISTRARCARGAHORARIAAUXILIAR:grupo_id:carga_horaria_id
//  REGISTRARCARGAHORARIAAUXILIAR:
        case "REGISTRARCARGAHORARIAAUXILIAR":
            if (parametros.size() == 3) {
                String grupo_id = parametros.get(1);
                String carga_horaria_id = parametros.get(2);
                ngrupo = new NGrupo();
                ncargahoraria = new NCargaHoraria();
                if (ngrupo.existe(grupo_id)){
                    if (ncargahoraria.existe(carga_horaria_id)){
                        LinkedList<String> datos = new LinkedList<>();
                        for (int i = 1; i < 10; i++) {
                            datos.add(parametros.get(i));
                        }
                        ncargahorariagrupo = new NCargaHorariaGrupo();
                        ncargahorariagrupo.insertar(datos);
                        salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
                    } else {
                        System.out.println("ERROR: EL ID DE CARGA HORARIA NO EXISTE");
                        salida.sendHtmlEmail(from, "ERROR: EL ID DE CARGA HORARIA NO EXISTE PARA PODER REGISTRAR CARGA HORARIA AUXILIAR", ToHTML.getHTMLMessage("La CARGA HORARIA ID: "+carga_horaria_id+", utilizado no se encuentra registrado en carga horaria", "Asegurese que este ingresando un carga_horaria_id registrado en las carga horaria para poder registrar la carga horaria auxiliar"));
                    }
                } else {
                    System.out.println("ERROR: EL ID DE GRUPO NO EXISTE");
                    salida.sendHtmlEmail(from, "ERROR: EL ID DE GRUPO NO EXISTE PARA PODER REGISTRAR CARGA HORARIA AUXILIAR", ToHTML.getHTMLMessage("El GRUPO ID: "+grupo_id+", utilizado no se encuentra registrado en grupo", "Asegurese que este ingresando un grupo_id registrado en los grupos para poder registrar la carga horaria auxiliar"));
                }
            } else {
                System.out.println("ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS");
                salida.sendHtmlEmail(from, "ERROR: LA CANTIDAD DE LOS DATOS SON INCORRECTOS", ToHTML.getHTMLMessage("Cantidad incorrecta de datos ", "Revisa que la cantidad de datos sea correcta"));
            }
        break;        
        
        
        
        
        
        
        /*try {
			
		} catch (Exception e) {
			
		}*/
        
        // -----------------------------------------------------RODRIGO--------------------------------------------------------------------------
        /* MATERIAS */
        case "LISMAT":
        	nMateria = new NMateria();
        	datos = new LinkedList<>();
        	if(parametros.size() == 1) {
        		try {
        			datos = nMateria.listar(null);
        		} catch(Exception e) {
        			salida.sendHtmlEmail(from, "ERROR: Ocurrio un problema al listar los registros ", ToHTML.getHTMLMessage("Problema interno", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}       		
        	}else {
        		try {
					params = Datos.parametrosMapeados(parametros);
				} catch (Exception e1) {
					salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
	    			break;
				}
        		if(!Datos.parametrosValidos(params, new String[] {"id", "nombre", "sigla"}, false)) {
        			salida.sendHtmlEmail(from, "ERROR: ALGUNOS FILTROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Filtros incorrectos ", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        		
        		try {
        			datos = nMateria.listar(params);
        		} catch(Exception e) {
        			salida.sendHtmlEmail(from, "ERROR: Ocurrio un problema al listar los registros ", ToHTML.getHTMLMessage("Problema interno", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}   
        		
        		if(datos.size() == 0) {
        			salida.sendHtmlEmail(from, "NO HAY NINGUNA MATERIA QUE COINCIDA CON LOS FILTROS ENVIADOS", ToHTML.getHTMLMessage("Datos no Encontrados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        	}
        	String[] columnas = {"ID", "NOMBRE", "SIGLA"};
        	LinkedList<String> campos = new LinkedList<>(Arrays.asList(columnas));
        	salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Lista de Materias", ToHTML.getTable(datos, campos)));
        break;
        
        /*---------------------------------------------------------------------------------------------------------------------------------*/
        
        case "REGMAT":
        	if(parametros.size() < 3) {
        		salida.sendHtmlEmail(from, "ERROR: PARAMETROS FALTANTES", ToHTML.getHTMLMessage("Faltan Parametros para registrar la materia", "Revise el comando HELP para ver el formato que debe enviar"));
    			break;
        	}
			try {
				params = Datos.parametrosMapeados(parametros);
			} catch (Exception e8) {
				salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
    			break;
			}
        	if(!Datos.parametrosValidos(params, new String[] {"nombre", "sigla"}, true)) {
    			salida.sendHtmlEmail(from, "ERROR: ALGUNOS PARAMETROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Algunos parametros enviados para registrar la materia no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	nMateria = new NMateria();
        	try {
        		nMateria.insertar(params);
    		} catch (Exception e) {
    			salida.sendHtmlEmail(from, "ERROR: No se pudo registrar la materia ", ToHTML.getHTMLMessage("Error interno o la materia con esa sigla ya existe", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}  	
        	salida.sendHtmlEmail(from, "La materia se registró Correctamente!", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
        	break;
        
        /*---------------------------------------------------------------------------------------------------------------------------------*/
        
        case "VERMAT":
        	if(parametros.size() < 2) {
        		salida.sendHtmlEmail(from, "ERROR: PARAMETROS FALTANTES", ToHTML.getHTMLMessage("Faltan Parametros para ver la materia", "Revise el comando HELP para ver el formato que debe enviar"));
    			break;
        	}
			try {
				params = Datos.parametrosMapeados(parametros);
			} catch (Exception e7) {
				salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
    			break;
			}
        	if(!Datos.parametrosValidos(params, new String[] {"id"}, true)) {
    			salida.sendHtmlEmail(from, "ERROR: ALGUNOS PARAMETROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Algunos parametros enviados para ver la materia no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	nMateria = new NMateria();
        	try {
        		datos = nMateria.mostrar(Integer.valueOf((String) params.get("id")));
    		} catch (Exception e) {
    			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener el registro ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	if(datos == null) {
        		salida.sendHtmlEmail(from, "No existe la Materia con id " + params.get("id"), ToHTML.getHTMLMessage("Consulte la lista de materias para ver los ids", "Revise los filtros permitidos con el comando HELP"));
    			break;
        	}
        	campos = new LinkedList<>(Arrays.asList(new String[] {"ID", "NOMBRE", "SIGLA"}));
        	salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Materia " + params.get("id"), ToHTML.getTable(datos, campos)));
        	break;
        
       /*---------------------------------------------------------------------------------------------------------------------------------*/
            
        case "DELMAT":
        	if(parametros.size() < 2) {
        		salida.sendHtmlEmail(from, "ERROR: PARAMETROS FALTANTES", ToHTML.getHTMLMessage("Faltan Parametros para ver la materia", "Revise el comando HELP para ver el formato que debe enviar"));
    			break;
        	}
			try {
				params = Datos.parametrosMapeados(parametros);
			} catch (Exception e6) {
				salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
    			break;
			}
        	if(!Datos.parametrosValidos(params, new String[] {"id"}, true)) {
    			salida.sendHtmlEmail(from, "ERROR: ALGUNOS PARAMETROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Algunos parametros enviados para ver la materia no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	nMateria = new NMateria();
        	try {
        		nMateria.eliminar(Integer.valueOf((String) params.get("id")));
    		} catch (Exception e) {
    			salida.sendHtmlEmail(from, "ERROR: No se pudo eliminar la materia ", ToHTML.getHTMLMessage("Error interno o Algunos parametros enviados para eliminar la materia no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	salida.sendHtmlEmail(from, "La materia se eliminó Correctamente!", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
        	break;
        	
        /*----------------------------------------------------------------------------------------------------*/
        /* GRUPOS */
        case "LISMATGRUP":
        	nGrupoMateria = new NGrupoMateria();
        	datos = new LinkedList<>();
        	if(parametros.size() == 1) {
        		try {
        			datos = nGrupoMateria.listar(null);	
        		} catch (Exception e) {
        			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener los registros ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}        		
        	}else {
        		try {
					params = Datos.parametrosMapeados(parametros);
				} catch (Exception e1) {
					salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
	    			break;
				}
        		if(!Datos.parametrosValidos(params, new String[] {"materia", "sigla", "grupo", "carrera"}, false)) {
        			salida.sendHtmlEmail(from, "ERROR: ALGUNOS FILTROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Filtros incorrectos ", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        		
        		try {
        			datos = nGrupoMateria.listar(params);	
        		} catch (Exception e) {
        			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener los registros ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        		
        		if(datos.size() == 0) {
        			salida.sendHtmlEmail(from, "NO HAY NINGUN GRUPO DE MATERIA QUE COINCIDA CON LOS FILTROS ENVIADOS", ToHTML.getHTMLMessage("Datos no Encontrados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        	}
        	campos = new LinkedList<>(Arrays.asList(new String[] {"IDGRUPO", "MATERIA", "SIGLA", "GRUPO", "HORARIO", "CARRERA", "DOCENTE", "AUXILIAR"}));
        	salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Lista de Materias", ToHTML.getTable(datos, campos)));
        	break;
        	
        /*----------------------------------------------------------------------------------------------------*/
        	
        case "REGMATGRUP":
        	if(parametros.size() < 6) {
        		salida.sendHtmlEmail(from, "ERROR: PARAMETROS FALTANTES", ToHTML.getHTMLMessage("Faltan Parametros para registrar la materia", "Revise el comando HELP para ver el formato que debe enviar"));
    			break;
        	}
			try {
				params = Datos.parametrosMapeados(parametros);
			} catch (Exception e5) {
				salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
    			break;
			}
        	if(!Datos.parametrosValidos(params, new String[] {"materia", "grupo", "horario", "carrera", "docente"}, true)) {
    			salida.sendHtmlEmail(from, "ERROR: ALGUNOS PARAMETROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Algunos parametros enviados para registrar el grupo no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	nGrupoMateria = new NGrupoMateria();
        	
        	try {
        		nGrupoMateria.insertar(params);	
    		} catch (Exception e) {
    			System.out.println(e.getStackTrace());
    			salida.sendHtmlEmail(from, "ERROR: No se pudo insertar el grupo ", ToHTML.getHTMLMessage("Error interno o el grupo de la materia ya existe o datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	
        	salida.sendHtmlEmail(from, "El grupo se registró Correctamente!", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
        	break;
        
        /*----------------------------------------------------------------------------------------------------*/
        	
        /* POSTULANTES */	
        case "LISPOS":
        	nPostulante = new NPostulante();
        	datos = new LinkedList<>();
        	if(parametros.size() == 1) {
        		try {
        			datos = nPostulante.listar(null);	
        		} catch (Exception e) {
        			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener los registros de Postulantes ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}        		
        	}else {
        		try {
					params = Datos.parametrosMapeados(parametros);
				} catch (Exception e1) {
					salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
	    			break;
				}
        		if(!Datos.parametrosValidos(params, new String[] {"nombre", "periodo", "carrera"}, false)) {
        			salida.sendHtmlEmail(from, "ERROR: ALGUNOS FILTROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Filtros incorrectos ", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        		
        		try {
        			datos = nPostulante.listar(params);	
        		} catch (Exception e) {
        			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener los registros de Postulantes ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        		
        		if(datos.size() == 0) {
        			salida.sendHtmlEmail(from, "NO HAY NINGUN POSTULANTE QUE COINCIDA CON LOS FILTROS ENVIADOS", ToHTML.getHTMLMessage("Datos no Encontrados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        	}
        	campos = new LinkedList<>(Arrays.asList(new String[] {"ID", "NOMBRES", "REGISTRO", "PERIODO", "CARRERA", "MATERIAS"}));
        	salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Lista de Postulantes", ToHTML.getTable(datos, campos)));
        	break;
        	
        /*----------------------------------------------------------------------------------------------------*/
        	
        case "REGPOS":
        	if(parametros.size() < 8) {
        		salida.sendHtmlEmail(from, "ERROR: PARAMETROS FALTANTES", ToHTML.getHTMLMessage("Faltan Parametros para registrar la materia", "Revise el comando HELP para ver el formato que debe enviar"));
    			break;
        	}
			try {
				params = Datos.parametrosMapeados(parametros);
			} catch (Exception e4) {
				salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
    			break;
			}
        	if(!Datos.parametrosValidos(params, new String[] {"nombres", "apellidos", "registro", "ci", "carrera", "periodo", "materias"}, true)) {
    			salida.sendHtmlEmail(from, "ERROR: ALGUNOS PARAMETROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Algunos parametros enviados para registrar el postulante no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	nPostulante = new NPostulante();
        	
        	try {
        		nPostulante.insertar(params);	
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    			salida.sendHtmlEmail(from, "ERROR: No se pudo insertar el postulante ", ToHTML.getHTMLMessage("Error interno o el postulante ya existe en el periodo actual", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	
        	salida.sendHtmlEmail(from, "El postulante se registró Correctamente!", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
        	break;
        	
        /*----------------------------------------------------------------------------------------------------*/
        
        case "VERPOS":
        	if(parametros.size() < 2) {
        		salida.sendHtmlEmail(from, "ERROR: PARAMETROS FALTANTES", ToHTML.getHTMLMessage("Faltan el parametro numero de registro del postulante", "Revise el comando HELP para ver el formato que debe enviar"));
    			break;
        	}
			try {
				params = Datos.parametrosMapeados(parametros);
			} catch (Exception e3) {
				salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
    			break;
			}
        	if(!Datos.parametrosValidos(params, new String[] {"registro"}, true)) {
    			salida.sendHtmlEmail(from, "ERROR: ALGUNOS PARAMETROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Algunos parametros enviados para ver el postulante no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	nPostulante = new NPostulante();
        	try {
        		String registro = (String) params.get("registro");
        		datos = nPostulante.mostrarPorRegistro(registro);
    		} catch (Exception e) {
    			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener el postulante ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	if(datos == null) {
        		salida.sendHtmlEmail(from, "No existe el Postulante con registro " + params.get("registro"), ToHTML.getHTMLMessage("Consulte la lista de postulantes para ver los registros", "Revise los filtros permitidos con el comando HELP"));
    			break;
        	}
        	campos = new LinkedList<>(Arrays.asList(new String[] {"ID", "NOMBRES", "REGISTRO", "PERIODO", "CARRERA", "MATERIAS"}));
        	salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Postulante con registro: " + params.get("registro"), ToHTML.getTable(datos, campos)));
        	break;
        	
        /*----------------------------------------------------------------------------------------------------*/
            
        case "LISEVAL":
        	nExamen = new NExamen();
        	datos = new LinkedList<>();
        	if(parametros.size() == 1) {
        		try {
        			datos = nExamen.listar(null);	
        		} catch (Exception e) {
        			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener los registros de Evaluaciones ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}        		
        	}else {
        		try {
					params = Datos.parametrosMapeados(parametros);
				} catch (Exception e1) {
					salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
	    			break;
				}
        		if(!Datos.parametrosValidos(params, new String[] {"sigla", "periodo", "docente"}, false)) {
        			salida.sendHtmlEmail(from, "ERROR: ALGUNOS FILTROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Filtros incorrectos ", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        		
        		try {
        			datos = nExamen.listar(params);	
        		} catch (Exception e) {
        			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener los registros de Evaluaciones ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        		
        		if(datos.size() == 0) {
        			salida.sendHtmlEmail(from, "NO HAY NINGUNA EVALUACION QUE COINCIDA CON LOS FILTROS ENVIADOS", ToHTML.getHTMLMessage("Datos no Encontrados", "Revise los filtros permitidos con el comando HELP"));
        			break;
        		}
        	}
        	campos = new LinkedList<>(Arrays.asList(new String[] {"ID", "MATERIA", "SIGLA", "PERIODO", "DOCENTE", "FECHA", "TOTAL EVALUADOS", "APROBADOS"}));
        	salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Lista de Evaluaciones", ToHTML.getTable(datos, campos)));
        	break;
        	
        /*----------------------------------------------------------------------------------------------------*/
        	
        case "LISEVALPOS":
        	if(parametros.size() < 2) {
        		salida.sendHtmlEmail(from, "ERROR: PARAMETROS FALTANTES", ToHTML.getHTMLMessage("Faltan el parametro numero de registro del postulante", "Revise el comando HELP para ver el formato que debe enviar"));
    			break;
        	}
			try {
				params = Datos.parametrosMapeados(parametros);
			} catch (Exception e2) {
				salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
    			break;
			}
        	if(!Datos.parametrosValidos(params, new String[] {"registro"}, true)) {
    			salida.sendHtmlEmail(from, "ERROR: ALGUNOS PARAMETROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Algunos parametros enviados para ver el postulante no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	nExamen = new NExamen();
        	try {
        		String registro = (String) params.get("registro");
        		datos = nExamen.listarPorRegistro(registro);
    		} catch (Exception e) {
    			salida.sendHtmlEmail(from, "ERROR: No se pudo obtener los examenes del postulante ", ToHTML.getHTMLMessage("Error interno o formato de datos mal pasados", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	if(datos.size() == 0) {
        		salida.sendHtmlEmail(from, "El postulante no existe o no tiene evaluaciones registradas " + params.get("registro"), ToHTML.getHTMLMessage("Consulte la lista de postulantes para ver los registros", "Revise los filtros permitidos con el comando HELP"));
    			break;
        	}
        	campos = new LinkedList<>(Arrays.asList(new String[] {"GESTION", "MATERIA", "NOTA", "TOMADO POR", "FECHA"}));
        	salida.sendHtmlEmail(from, "Comando: " + comando + " enviado", ToHTML.getHTMLMessage("Evaluaciones de Postulante con registro: " + params.get("registro"), ToHTML.getTable(datos, campos)));
        	break;
        
        /*----------------------------------------------------------------------------------------------------*/
        
        case "REGEVAL":
        	if(parametros.size() < 5) {
        		salida.sendHtmlEmail(from, "ERROR: PARAMETROS FALTANTES", ToHTML.getHTMLMessage("Faltan Parametros para registrar la evaluacion", "Revise el comando HELP para ver el formato que debe enviar"));
    			break;
        	}
			try {
				params = Datos.parametrosMapeados(parametros);
			} catch (Exception e1) {
				salida.sendHtmlEmail(from, "El formato de parametros no es válido ", ToHTML.getHTMLMessage("El formato de parametros pasado no es el correcto", "Revise el ejemplo con el comando HELP"));
    			break;
			}
        	if(!Datos.parametrosValidos(params, new String[] {"materia", "periodo", "docente", "fecha"}, true)) {
    			salida.sendHtmlEmail(from, "ERROR: ALGUNOS PARAMETROS ENVIADOS NO SON VALIDOS ", ToHTML.getHTMLMessage("Algunos parametros enviados para registrar la evaluacion no son validos", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	nExamen = new NExamen();
        	
        	try {
        		nExamen.insertar(params);	
    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    			salida.sendHtmlEmail(from, "ERROR: No se pudo insertar la evaluacion", ToHTML.getHTMLMessage("Error interno o la materia pasada no existe", "Revise los filtros permitidos con el comando HELP"));
    			break;
    		}
        	
        	salida.sendHtmlEmail(from, "La evaluación se añadió correctamente", ToHTML.getHTMLMessage("Comando: " + comando + " enviado", "El comando " + comando + " se envio exitosamente"));
        	break;
        	
        	
      }
      
  
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                                           METODO AUXILIARES                                
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  SEPARA EL SUBJECT, OBTENIENDO ASI LAS DIRECTRICES NECESARIAS [1]
    public void separar() {
        this.entrada = new POP3();
        this.parametros = new LinkedList<>();
        String subject = "";
        try {
            this.entrada.connect();
            this.entrada.logIn();
            subject = this.entrada.getSubject(this.stat);
            this.entrada.logOut();
            this.entrada.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        subject = subject.trim();
        System.out.println("Subject: " + subject);
        if (!"".equals(subject)) {
            String[] partes = subject.split(":");
            this.parametros.addAll(Arrays.asList(partes));
            System.out.println("Parametros: " + this.parametros);
        }
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// OBTIENE EL MENSAJE HASTA EL FROM DE UN MENSAJE QUE RECIEN LE LLEGA
    public void setFrom() {
        this.entrada = new POP3();
        try {
            entrada.connect();
            entrada.logIn();
            this.from = entrada.getFrom(this.stat); 
            entrada.logOut();
            entrada.close();
            System.out.println("From: " + this.from);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  HACE UNA LECTURA DE LOS NUEVOS MENSAJES RECIBIDOS [2]
    public void setStat() {
        this.entrada = new POP3();
        try {
            entrada.connect();
            entrada.logIn();
            this.stat = entrada.getStat();//me da el ultimo mensaje que obtuve el usuario grupo07sc, es decir la cantidad de mensajes
            entrada.logOut();
            entrada.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  MODIFICA LA VARIABLE STAT, SEGUN EL VALOR DADO [3]
    public void setStat(int stat) {
        this.stat = String.valueOf(stat);
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//  DEVUELVE LA VARIABLE STAT [4]
    public int getStat() {
        return Integer.parseInt(this.stat);
    }
    
//----------------------------------------------------------------------------------
//METODO PARA LAS TABLAS 
//--------------------------------------------------------------------------------------------------------------
//            TABLA PERIODO   
//--------------------------------------------------------------------------------------------------------------
//Verifica si existe el usuario por su CI. existeUser(ci)
//Por ejemplo: existeUser("111")  -> verifica en la BD si existe ese usuario
    public boolean existePeriodo(String id) {
        NPeriodo periodoExiste = new NPeriodo();
        return periodoExiste.existe(id);
    }
}




