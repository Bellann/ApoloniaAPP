package cl.apolonia.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;


@Entity
@Table(name="OBSERVACIONES")
public class Observaciones implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", insertable = false, updatable = false)
    private Integer id;
    
    @Column(name="ID_TAREA_EJECUTADA")
    private Integer idtarea;
    
    @Column(name="FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name="RUN")
    private String run;
    
    @Column(name="COMENTARIO")
    private String comentario;
    
    @Column(name="NOMBRE")
    private String nombre;

    public Observaciones() {
    }

    public Observaciones(Integer id, Integer idtarea, Date fecha, String run, String comentario, String nombre) {
        this.id = id;
        this.idtarea = idtarea;
        this.fecha = fecha;
        this.run = run;
        this.comentario = comentario;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(Integer idtarea) {
        this.idtarea = idtarea;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}