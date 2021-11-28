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
      
    @Column(name="FECHA_SUBIDA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name="RUN_FUNCIONARIO")
    private String runFuncionario;
    
    @Column(name="ID_TAREA_EJECUTADA")
    private Integer idTarea;

    public Archivo() {
    }

    public Archivo(Integer id, String nombre, String tipo, Date fecha, String run_funcionario, Integer id_tarea) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.runFuncionario = run_funcionario;
        this.idTarea = id_tarea;
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


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRun_funcionario() {
        return runFuncionario;
    }

    public void setRun_funcionario(String run_funcionario) {
        this.runFuncionario = run_funcionario;
    }

    public Integer getId_tarea() {
        return idTarea;
    }

    public void setId_tarea(Integer id_tarea) {
        this.idTarea = id_tarea;
    }
    
    
}
