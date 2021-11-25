package cl.apolonia.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name="ARCHIVOS")
public class Archivo 
{
    private static final long serialversionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", insertable = false, updatable = false)
    private Integer id;
    
    @Column(name="NOMBRE")
    private String nombre;
    
    @Column(name="TIPO")
    private String tipo;
    
    @Column(name="FECHA_SUBIDA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name="RUN_FUNCIONARIO")
    private String run_funcionario;
    
    @Column(name="ID_TAREA_EJECUTADA")
    private Integer id_tarea;

    public Archivo() {
    }

    public Archivo(Integer id, String nombre, String tipo, Date fecha, String run_funcionario, Integer id_tarea) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.run_funcionario = run_funcionario;
        this.id_tarea = id_tarea;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRun_funcionario() {
        return run_funcionario;
    }

    public void setRun_funcionario(String run_funcionario) {
        this.run_funcionario = run_funcionario;
    }

    public Integer getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Integer id_tarea) {
        this.id_tarea = id_tarea;
    }
    
    
}
