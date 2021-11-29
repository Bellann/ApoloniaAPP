package cl.apolonia.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="DASHBOARD")

public class Dashboard implements Serializable {
    
    private static final long serialversionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDENTIFICADOR", insertable = false, updatable = false)
    private Integer id;
    
    @Column(name="RUT_UNIDAD")
    private String rutUnidad;
    
    @Column(name="NOMBRE_UNIDAD")
    private String nombreUnidad;
    
    @Column(name="ID_SUBUNIDAD")
    private Integer idSubunidad;
    
    @Column(name="NOMBRE_SUBUNIDAD")
    private String nombreSubunidad;
    
    @Column(name="ID_PROCESO")
    private Integer idProceso;
    
    @Column(name="NOMBRE_PROCESO")
    private String nombreProceso;
    
    @Column(name="ESTADO_PROCESO")
    private String estadoProceso;
    
    @Column(name="ID_TAREA")
    private Integer idTarea;
    
    @Column(name="NOMBRE_TAREA")
    private String nombreTarea;
    
    @Column(name="DURACION_TAREA")
    private Integer duracionTarea;
    
    @Column(name="ID_ESTADO_TAREA")
    private Integer idEstadoTarea;
    
    @Column(name="ESTADO_TAREA")
    private String estadoTarea;
    
    @Column(name="RUN_FUNCIONARIO")
    private String runFuncionario;
    
    @Column(name="NOMBRE_FUNCIONARIO")
    private String nombreFuncionario;
    
    @Column(name="PROGRAMADA")
    Integer programada;
    
    @Column(name="ACEPTADA")
    Integer aceptada;
    
    @Column(name="DESARROLLO")
    Integer desarrollo;
    
    @Column(name="REVISION")
    Integer revision;
    
    @Column(name="FINALIZADA")
    Integer finalizada;
    
    @Column(name="RECHAZADA")
    Integer rechazada;
    
    @Column(name="TOTAL_TAREAS")
    Integer total;
    
    @Column(name="CARGA")
    Integer carga;
    
    public Dashboard() {
    }

    public Dashboard(Integer id, String rutUnidad, String nombreUnidad, Integer idSubunidad, String nombreSubunidad, Integer idProceso, String nombreProceso, String estadoProceso, Integer idTarea, String nombreTarea, Integer duracionTarea, Integer idEstadoTarea, String estadoTarea, String runFuncionario, String nombreFuncionario, Integer programada, Integer aceptada, Integer desarrollo, Integer revision, Integer finalizada, Integer rechazada, Integer total, Integer carga) {
        this.id = id;
        this.rutUnidad = rutUnidad;
        this.nombreUnidad = nombreUnidad;
        this.idSubunidad = idSubunidad;
        this.nombreSubunidad = nombreSubunidad;
        this.idProceso = idProceso;
        this.nombreProceso = nombreProceso;
        this.estadoProceso = estadoProceso;
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.duracionTarea = duracionTarea;
        this.idEstadoTarea = idEstadoTarea;
        this.estadoTarea = estadoTarea;
        this.runFuncionario = runFuncionario;
        this.nombreFuncionario = nombreFuncionario;
        this.programada = programada;
        this.aceptada = aceptada;
        this.desarrollo = desarrollo;
        this.revision = revision;
        this.finalizada = finalizada;
        this.rechazada = rechazada;
        this.total = total;
        this.carga = carga;
    }
    
    

    public Dashboard(String runFuncionario, Integer programada, Integer aceptada, Integer desarrollo, Integer revision, Integer finalizada, Integer rechazada,Integer total, Integer carga) {
        this.runFuncionario = runFuncionario;
        this.programada = programada;
        this.aceptada = aceptada;
        this.desarrollo = desarrollo;
        this.revision = revision;
        this.finalizada = finalizada;
        this.rechazada = rechazada;
        this.total = total;
        this.carga = carga;
    }

    public Dashboard(Integer idSubunidad, String nombreSubunidad, Integer programada, Integer aceptada, Integer desarrollo, Integer revision, Integer finalizada, Integer rechazada, Integer total, Integer carga) {
        this.idSubunidad = idSubunidad;
        this.nombreSubunidad = nombreSubunidad;
        this.programada = programada;
        this.aceptada = aceptada;
        this.desarrollo = desarrollo;
        this.revision = revision;
        this.finalizada = finalizada;
        this.rechazada = rechazada;
        this.total = total;
        this.carga = carga;
    }

    public Dashboard(String rutUnidad, String nombreUnidad, Integer programada, Integer aceptada, Integer desarrollo, Integer revision, Integer finalizada, Integer rechazada, Integer total, Integer carga) {
        this.rutUnidad = rutUnidad;
        this.nombreUnidad = nombreUnidad;
        this.programada = programada;
        this.aceptada = aceptada;
        this.desarrollo = desarrollo;
        this.revision = revision;
        this.finalizada = finalizada;
        this.rechazada = rechazada;
        this.total = total;
        this.carga = carga;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRutUnidad() {
        return rutUnidad;
    }

    public void setRutUnidad(String rutUnidad) {
        this.rutUnidad = rutUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public Integer getIdSubunidad() {
        return idSubunidad;
    }

    public void setIdSubunidad(Integer idSubunidad) {
        this.idSubunidad = idSubunidad;
    }

    public String getNombreSubunidad() {
        return nombreSubunidad;
    }

    public void setNombreSubunidad(String nombreSubunidad) {
        this.nombreSubunidad = nombreSubunidad;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public String getRunFuncionario() {
        return runFuncionario;
    }

    public void setRunFuncionario(String runFuncionario) {
        this.runFuncionario = runFuncionario;
    }

    public String getNombreFuncionario() {
        return nombreFuncionario;
    }

    public void setNombreFuncionario(String nombreFuncionario) {
        this.nombreFuncionario = nombreFuncionario;
    }

    public Integer getDuracionTarea() {
        return duracionTarea;
    }

    public void setDuracionTarea(Integer duracionTarea) {
        this.duracionTarea = duracionTarea;
    }

    public Integer getDesarrollo() {
        return desarrollo;
    }

    public void setDesarrollo(Integer desarrollo) {
        this.desarrollo = desarrollo;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public Integer getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Integer finalizada) {
        this.finalizada = finalizada;
    }

    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
    }

    public Integer getAceptada() {
        return aceptada;
    }

    public void setAceptada(Integer aceptada) {
        this.aceptada = aceptada;
    }

    public Integer getProgramada() {
        return programada;
    }

    public void setProgramada(Integer programada) {
        this.programada = programada;
    }

    public Integer getRechazada() {
        return rechazada;
    }

    public void setRechazada(Integer rechazada) {
        this.rechazada = rechazada;
    }

    public Integer getIdEstadoTarea() {
        return idEstadoTarea;
    }

    public void setIdEstadoTarea(Integer idEstadoTarea) {
        this.idEstadoTarea = idEstadoTarea;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
     
    
}
