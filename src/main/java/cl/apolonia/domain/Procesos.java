package cl.apolonia.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name = "r_proc_eject_all",
        callable= true,
        query = "(? = call r_proc_eject_all())",
        resultClass = Procesos.class)
@Table(name="PROCESO_WEB")

public class Procesos implements Serializable{
    
    private static final long serialversionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PROCESO", insertable = false, updatable = false)
    private Integer id_proceso;
    
    @Column(name="PROCESO")
    private String proceso;
    
    @Column(name="EJECUTOR")
    private String ejecutor;
    
    @Column(name="SUBUNIDAD")
    private String subunidad;
    
    @Column(name="FECHA_LIMITE")
    @Temporal(javax.persistence.TemporalType.DATE)
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

    public Procesos() {
    }

    public Procesos(Integer id_proceso, String proceso, String ejecutor, String subunidad, Date fechaLimite, String estado, Integer idSubunidad, String runEjecutor, String unidad, String rutUnidad) {
        this.id_proceso = id_proceso;
        this.proceso = proceso;
        this.ejecutor = ejecutor;
        this.subunidad = subunidad;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.idSubunidad = idSubunidad;
        this.runEjecutor = runEjecutor;
        this.unidad = unidad;
        this.rutUnidad = rutUnidad;
    }

    public Integer getId_proceso() {
        return id_proceso;
    }

    public void setId_proceso(Integer id_proceso) {
        this.id_proceso = id_proceso;
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
    
    
    
    
}
