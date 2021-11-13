package cl.apolonia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAREAS_TIPO_WEB")
public class TareasTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION_TAREA")
    private String descripcionTarea;

    @Column(name = "DURACION")
    private Integer duracion;

    @Column(name = "ID_PROCESO_TIPO")
    private Integer idProcesoTipo;

    @Column(name = "DESCRIPCION_PROCESO")
    private String descripcionProceso;

    @Column(name = "ID_SUBUNIDAD")
    private Integer idSubunidad;

    @Column(name = "RUN_DISENADOR")
    private String runDisenador;

    @Column(name = "RUN_FUNCIONARIO")
    private String runFuncionario;

    @Column(name = "NOMBRES")
    private String nombres;

    @Column(name = "APELLIDOP")
    private String apellidop;

    @Column(name = "RUT_UNIDAD")
    private String rutUnidad;

    @Column(name = "SUBUNIDAD")
    private String subunidad;

    public TareasTipo() {
    }

    public TareasTipo(Integer id, String nombre, String descripcionTarea, Integer duracion, Integer idProcesoTipo, String descripcionProceso, Integer idSubunidad, String runDisenador, String runFuncionario, String nombres, String apellidop, String rutUnidad, String subunidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcionTarea = descripcionTarea;
        this.duracion = duracion;
        this.idProcesoTipo = idProcesoTipo;
        this.descripcionProceso = descripcionProceso;
        this.idSubunidad = idSubunidad;
        this.runDisenador = runDisenador;
        this.runFuncionario = runFuncionario;
        this.nombres = nombres;
        this.apellidop = apellidop;
        this.rutUnidad = rutUnidad;
        this.subunidad = subunidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getIdProcesoTipo() {
        return idProcesoTipo;
    }

    public void setIdProcesoTipo(Integer idProcesoTipo) {
        this.idProcesoTipo = idProcesoTipo;
    }

    public String getDescripcionProceso() {
        return descripcionProceso;
    }

    public void setDescripcionProceso(String descripcionProceso) {
        this.descripcionProceso = descripcionProceso;
    }

    public Integer getIdSubunidad() {
        return idSubunidad;
    }

    public void setIdSubunidad(Integer idSubunidad) {
        this.idSubunidad = idSubunidad;
    }

    public String getRunDisenador() {
        return runDisenador;
    }

    public void setRunDisenador(String runDisenador) {
        this.runDisenador = runDisenador;
    }

    public String getRunFuncionario() {
        return runFuncionario;
    }

    public void setRunFuncionario(String runFuncionario) {
        this.runFuncionario = runFuncionario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getRutUnidad() {
        return rutUnidad;
    }

    public void setRutUnidad(String rutUnidad) {
        this.rutUnidad = rutUnidad;
    }

    public String getSubunidad() {
        return subunidad;
    }

    public void setSubunidad(String subunidad) {
        this.subunidad = subunidad;
    }
    
    
    
    
}
