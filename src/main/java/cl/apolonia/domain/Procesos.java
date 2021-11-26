package cl.apolonia.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="PROCESO_WEB")

public class Procesos implements Serializable{
    
    private static final long serialversionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PROCESO", insertable = false, updatable = false)
    private Integer idproceso;
    
    @Column(name="PROCESO")
    private String proceso;
    
    @Column(name="EJECUTOR")
    private String ejecutor;
    
    @Column(name="SUBUNIDAD")
    private String subunidad;
    
    @Column(name="FECHA_LIMITE")
    @Temporal(TemporalType.DATE)
    private Date fechaLimite;
    
    @Column(name="ESTADO")
    private String estado;
    
    @Column(name="ID_SUBUNIDAD")
    private Integer idSubunidad;
    
    @Column(name="RUN_EJECUTOR")
    private String runEjecutor;
    
    @Column(name="UNIDAD")
    private String unidad;
    
    @Column(name="RUT_UNIDAD")
    private String rutUnidad;
    
    @Column(name="DESCRIPCION")
    private String descripcion;

    public Procesos() {
    }

    public Procesos(Integer id_proceso, String proceso, String ejecutor, String subunidad, Date fechaLimite, String estado, Integer idSubunidad, String runEjecutor, String unidad, String rutUnidad, String descripcion) {
        this.idproceso = id_proceso;
        this.proceso = proceso;
        this.ejecutor = ejecutor;
        this.subunidad = subunidad;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.idSubunidad = idSubunidad;
        this.runEjecutor = runEjecutor;
        this.unidad = unidad;
        this.rutUnidad = rutUnidad;
        this.descripcion = descripcion;
    }

    public Integer getId_proceso() {
        return idproceso;
    }

    public void setId_proceso(Integer id_proceso) {
        this.idproceso = id_proceso;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getEjecutor() {
        return ejecutor;
    }

    public void setEjecutor(String ejecutor) {
        this.ejecutor = ejecutor;
    }

    public String getSubunidad() {
        return subunidad;
    }

    public void setSubunidad(String subunidad) {
        this.subunidad = subunidad;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdSubunidad() {
        return idSubunidad;
    }

    public void setIdSubunidad(Integer idSubunidad) {
        this.idSubunidad = idSubunidad;
    }

    public String getRunEjecutor() {
        return runEjecutor;
    }

    public void setRunEjecutor(String runEjecutor) {
        this.runEjecutor = runEjecutor;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getRutUnidad() {
        return rutUnidad;
    }

    public void setRutUnidad(String rutUnidad) {
        this.rutUnidad = rutUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
