package cl.apolonia.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="PROCPARTICIPO")
public class procParticipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PROCESO", insertable = false, updatable = false)
    private Integer idProceso;
    
    @Column(name="NOMBRE_PROCESO")
    private String nombreProceso;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="FECHa_PREVISTA_FIN")
    private Date fechaPrevistaFin;
    
    @Column(name="ASIGNADO_POR")
    private String asignadoPor;
    
    @Column(name="ESTADO")
    private String estado;

    public procParticipo() {
    }

    public procParticipo(Integer idProceso, String nombreProceso, Date fechaPrevistaFin, String asignadoPor, String estado) {
        this.idProceso = idProceso;
        this.nombreProceso = nombreProceso;
        this.fechaPrevistaFin = fechaPrevistaFin;
        this.asignadoPor = asignadoPor;
        this.estado = estado;
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

    public Date getFechaPrevistaFin() {
        return fechaPrevistaFin;
    }

    public void setFechaPrevistaFin(Date fechaPrevistaFin) {
        this.fechaPrevistaFin = fechaPrevistaFin;
    }

    public String getAsignadoPor() {
        return asignadoPor;
    }

    public void setAsignadoPor(String asignadoPor) {
        this.asignadoPor = asignadoPor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
