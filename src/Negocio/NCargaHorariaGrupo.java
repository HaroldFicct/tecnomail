package Negocio;
import Datos.DCargaHorariaGrupo;
import java.util.LinkedList;

public class NCargaHorariaGrupo {
    public DCargaHorariaGrupo dcargahorariagrupo;
    public NCargaHorariaGrupo(){}
    
    public void insertar(LinkedList<String> arg) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        dcargahorariagrupo.setGrupoId(Integer.parseInt(arg.get(0)));
        dcargahorariagrupo.setCargaHorariaId(Integer.parseInt(arg.get(1)));
        dcargahorariagrupo.insertar();
    }

    public void modificar(LinkedList<String> arg) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        dcargahorariagrupo.setId(Integer.parseInt(arg.get(0)));
        dcargahorariagrupo.setGrupoId(Integer.parseInt(arg.get(1)));
        dcargahorariagrupo.setCargaHorariaId(Integer.parseInt(arg.get(2)));
        dcargahorariagrupo.modificar();
    }

    public LinkedList<String> listar() {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        return dcargahorariagrupo.listar();
    }
    public LinkedList<String> listar_por_nombres_de_auxiliar(String nombres_de_aux) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        return dcargahorariagrupo.listar_por_nombres_de_auxiliar(nombres_de_aux);
    }
    public LinkedList<String> listar_por_apellidos_de_auxiliar(String apellidos_de_aux) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        return dcargahorariagrupo.listar_por_apellidos_de_auxiliar(apellidos_de_aux);
    }
    public LinkedList<String> listar_por_nombres_de_docente(String nombres_de_docente) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        return dcargahorariagrupo.listar_por_nombres_de_docente(nombres_de_docente);
    }
    public LinkedList<String> listar_por_apellidos_de_docente(String apellidos_de_docente) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        return dcargahorariagrupo.listar_por_apellidos_de_docente(apellidos_de_docente);
    }
    public LinkedList<String> listar_por_sigla_materia(String sigla_materia) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        return dcargahorariagrupo.listar_por_sigla_materia(sigla_materia);
    }
    public LinkedList<String> listar_por_sigla_grupo(String sigla_grupo) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        return dcargahorariagrupo.listar_por_sigla_grupo(sigla_grupo);
    }
    public LinkedList<String> listar_por_horas_asignadas(String horas_asignadas) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        return dcargahorariagrupo.listar_por_horas_asignadas(horas_asignadas);
    }

    public void eliminar(int id) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        dcargahorariagrupo.setId(id);
        dcargahorariagrupo.eliminar();
    }

    public LinkedList<String> mostrar(int id) {
        dcargahorariagrupo = new DCargaHorariaGrupo();
        dcargahorariagrupo.setId(id);
        return dcargahorariagrupo.mostrarID();
    }
    
    public boolean existe(String id){
        dcargahorariagrupo = new DCargaHorariaGrupo();
        int idE=Integer.parseInt(id);
        dcargahorariagrupo.setId(idE);
        return dcargahorariagrupo.existeID();
    }
}
