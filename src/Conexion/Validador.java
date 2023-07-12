package Conexion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import Negocio.*;

public class Validador {

    private static final String[] COMANDOS_VALIDOS
            = { "HELP", 
                "REGPER", 
                "EDITPER", 
                "VERPER", 
                "LISPER", 
                "DELPER",
                
                "REGLEC",
                "REGADM",
                "REGCARR",
                "REGCAT",
                "REGLIB",
                "REGTES",
                
                //Comandos de Erick
                "LISTARUSUARIO",
                "LISTARUSERFILTRADOPORROLID",
                "LISTARUSERFILTRADOPORNOMBREDEROL",
                "LISTARUSERFILTRADOPORNOMBREDEUSUARIO",
                "LISTARUSERFILTRADOPORAPELLIDODEUSUARIO",
                
                "LISTARCONVOCATORIA",
                "LISTARCONVOCATORIAFILTRADOPORPERIODOID",
                "LISTARCONVOCATORIAFILTRADOPORPERIODONOMBRE",
                "LISTARCONVOCATORIAFILTRADOPORNOMBRECREADOR",
                "LISTARCONVOCATORIAFILTRADOPORAPELLIDOCREADOR",
                "LISTARCONVOCATORIAFILTRADOPORIDDELCREADOR",
                
                "LISTARCARGAHORARIAAUXILIARES",
                "LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEAUXILIAR",
                "LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEAUXILIAR",
                "LISTARCARGAHORARIAAUXILIARESFILTRADOPORNOMBREDEDOCENTE",
                "LISTARCARGAHORARIAAUXILIARESFILTRADOPORAPELLIDODEDOCENTE",
                "LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAMATERIA",
                "LISTARCARGAHORARIAAUXILIARESFILTRADOPORSIGLAGRUPO",
                "LISTARCARGAHORARIAAUXILIARESFILTRADOPORHORASASIGNADAS",
                
                "VERUSUARIO",
                "VERCONVOCATORIA",
                "VERCARGAHORARIAAUXILIAR",
                
                "DARDEBAJAUSUARIO",
                "DARDEBAJACONVOCATORIA",
                "DARDEBAJACARGAHORARIAAUXILIAR",
                
                "REGISTRARUSUARIO",
                //para registrar una convocatoria se debe registrar la actividad y luego cada cornograma
                "REGISTRARCONVOCATORIA",
                "REGISTRARCARGAHORARIAAUXILIAR",
                
                /*comandos rodrigo*/
                //materias
                "LISMAT",
                "REGMAT",
                "VERMAT",
                "DELMAT",
                //grupos
                "LISMATGRUP",
                "REGMATGRUP",
                //postulantes
                "LISPOS",
                "REGPOS",
                "VERPOS",
                //examenes
                "LISEVAL",
                "LISEVALPOS",
                "REGEVAL",
                /* comandos alejandro */
                "REPORPOSPER",
                "REPORAUXCAR",
                "REPORMATGRUP",
                "REPORMATAPRO"
            };

   /*************           UTILIZADO PARA VERIFICAR SI ES UN COMANDO VALIDO        ***************/
    public boolean isComando(String comando) {  
        for (int i = 0; i < COMANDOS_VALIDOS.length; i++) {
          if(comando.equals(COMANDOS_VALIDOS[i])){
              return true;
          }
        }
        return false;
    }
    
    /************      VERIFICACION DE LOS PARAMETROS QUE SEAN CORRECTOS       **************/
    public boolean isCI(String ci) {
        return ci.matches("[0-9]{3,9}$");
    }
    public boolean isCelular(String celular) {
        return celular.matches("[0-9]{3,12}$");
    }
    public boolean isName(String name) {
        return (name.length() <= 50 && name.matches("[a-zA-Z]+$"));
    }
    public boolean isNameText(String name) {
        return (name.length() <= 100 && name.matches("[a-zA-Z ]+$"));
    }
    public boolean isSexo(String sexo) {
        return (sexo.equals("M") || sexo.equals("F"));
    }
    public boolean isTipo(String tipo){
        return tipo.equals("administrador") || tipo.equals("entrenador") || tipo.equals("cliente");
    }
    public boolean isFecha(String fecha) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public boolean isHoraMinSec(String fecha) {
        try {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setLenient(false);
            df.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public boolean isDireccion(String data){
        return data.length()>0;
    }
    public boolean isEmail(String email) {
        return (email.length() <= 50 && email.matches(".*@.*"));
    }
    public boolean isNumber(String id) {
        try {
            Integer.parseInt(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean isAll(String all){
        return all.equals("*");
    }
    public boolean isFloat(String flota){
        return flota.matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$");
    }
    public boolean isFiltro(String filtro){
        return filtro.equals("id") || filtro.equals("nom") || filtro.equals("pre") || filtro.equals("stock");
    }
    
    public boolean isFiltroMen(String filtro){//{id|dur|pre|des}
            return filtro.equals("id") || filtro.equals("dur") || filtro.equals("pre") || filtro.equals("des");
    }
    
    public boolean isPeriodo(String get) {
        String[] partes = get.split("\\|");
        if (partes.length==2&&isNumber(partes[0])&&isNumber(partes[1])){
            NPeriodo verificar = new NPeriodo();
            if (verificar.existe(partes[0])){
                return true;
            }
        }
        return false;
    }

    public boolean isAño(String get){
        boolean b=false;
        int año=Integer.parseInt(get);
        if (año>=1900 && año<=2100){
            b= true;
        }
        return b;
    }   
}