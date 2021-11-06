package cl.apolonia.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name = "r_tarea_eject_all",
        callable= true,
        query = "(? = call r_tarea_eject_all())",
        resultClass = Procesos.class)
@Table(name="TAREASEJECUTADAS")
public class TareasEjecutadas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_TAREA", insertable = false, updatable = false)
    private Integer idtarea;
    
    @Column(name="TAREA")
    private String tarea;
    
    @Column(name="EJECUTOR")
    private String ejecutor;
    
    @Column(name="RUN_EJECUTOR")
    private String runEjecutor;
    
    @Column(name="RESPONSABLE")
    private String responsable;
    
    @Column(name="RUN_RESPONSABLE")
    private String runResponsable;
    
    @Column(name="FCH_PREV_INICIO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fPrevInicio;
    
    @Column(name="FCH_PREV_FIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fPrevFin;
    
    @Column(name="FCH_REAL_INICIO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fRealInicio;
    
    @Column(name="FCH_REAL_FIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fRealFin;
    
    @Column(name="CON_DEPENDENCIAS")
    private int dependencias;
    
    @Column(name="ESTA_DESAGREGADA")
    private int segregada;
    
    @Column(name="ESTADO")
    private String estado;
    
    @Column(name="UNIDAD")
    private String unidad;
    
    @Column(name="SUBUNIDAD")
    private String subunidad;
    
    @Column(name="RUT_UNIDAD")
    private String rutUnidad;
    
    @Column(name="ID_SUBUNIDAD")
    private int idSubUnidad;
    
    @Column(name="PROCESO_EJECUTADO")
    private String procesoEjecutado;
    
    @Column(name="ID_PROCESO_EJECUTADO")
    private String idProcesoEjecutado;

    public TareasEjecutadas() {
    }

    public TareasEjecutadas(Integer idtarea, String tarea, String ejecutor, String runEjecutor, String responsable, String runResponsable, Date fPrevInicio, Date fPrevFin, Date fRealInicio, Date fRealFin, int dependencias, int segregada, String estado, String unidad, String subunidad, String rutUnidad, int idSubUnidad, String procesoEjecutado, String idProcesoEjecutado) {
        this.idtarea = idtarea;
        this.tarea = tarea;
        this.ejecutor = ejecutor;
        this.runEjecutor = runEjecutor;
        this.responsable = responsable;
        this.runResponsable = runResponsable;
        this.fPrevInicio = fPrevInicio;
        this.fPrevFin = fPrevFin;
        this.fRealInicio = fRealInicio;
        this.fRealFin = fRealFin;
        this.dependencias = dependencias;
        this.segregada = segregada;
        this.estado = estado;
        this.unidad = unidad;
        this.subunidad = subunidad;
        this.rutUnidad = rutUnidad;
        this.idSubUnidad = idSubUnidad;
        this.procesoEjecutado = procesoEjecutado;
        this.idProcesoEjecutado = idProcesoEjecutado;
    }

    public Integer getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(Integer idtarea) {
        this.idtarea = idtarea;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getEjecutor() {
        return ejecutor;
    }

    public void setEjecutor(String ejecutor) {
        this.ejecutor = ejecutor;
    }

    public String getRunEjecutor() {
        return runEjecutor;
    }

    public void setRunEjecutor(String runEjecutor) {
        this.runEjecutor = runEjecutor;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getRunResponsable() {
        return runResponsable;
    }

    public void setRunResponsable(String runResponsable) {
        this.runResponsable = runResponsable;
    }

    public Date getfPrevInicio() {
        return fPrevInicio;
    }

    public void setfPrevInicio(Date fPrevInicio) {
        this.fPrevInicio = fPrevInicio;
    }

    public Date getfPrevFin() {
        return fPrevFin;
    }

    public void setfPrevFin(Date fPrevFin) {
        this.fPrevFin = fPrevFin;
    }

    public Date getfRealInicio() {
        return fRealInicio;
    }

    public void setfRealInicio(Date fRealInicio) {
        this.fRealInicio = fRealInicio;
    }

    public Date getfRealFin() {
        return fRealFin;
    }

    public void setfRealFin(Date fRealFin) {
        this.fRealFin = fRealFin;
    }

    public int getDependencias() {
        return dependencias;
    }

    public void setDependencias(int dependencias) {
        this.dependencias = dependencias;
    }

    public int getSegregada() {
        return segregada;
    }

    public void setSegregada(int segregada) {
        this.segregada = segregada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getSubunidad() {
        return subunidad;
    }

    public void setSubunidad(String subunidad) {
        this.subunidad = subunidad;
    }

    public String getRutUnidad() {
        return rutUnidad;
    }

    public void setRutUnidad(String rutUnidad) {
        this.rutUnidad = rutUnidad;
    }

    public int getIdSubUnidad() {
        return idSubUnidad;
    }

    public void setIdSubUnidad(int idSubUnidad) {
        this.idSubUnidad = idSubUnidad;
    }

    public String getProcesoEjecutado() {
        return procesoEjecutado;
    }

    public void setProcesoEjecutado(String procesoEjecutado) {
        this.procesoEjecutado = procesoEjecutado;
    }

    public String getIdProcesoEjecutado() {
        return idProcesoEjecutado;
    }

    public void setIdProcesoEjecutado(String idProcesoEjecutado) {
        this.idProcesoEjecutado = idProcesoEjecutado;
    }
    
    
    
    
    
    
}
