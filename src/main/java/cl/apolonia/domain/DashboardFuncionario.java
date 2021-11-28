package cl.apolonia.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="DASHBOARD_FUNCIONARIO")

public class DashboardFuncionario implements Serializable{
    
    private static final long serialversionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RUN_FUNCIONARIO", insertable = false, updatable = false)
    private String run;
    
    @Column(name="NOMBRE_FUNCIONARIO")
    private String nombre;
    
    @Column(name="NOMBRE_ESTADO")
    private String estado;
    
    @Column(name="TAREAS_ASIGNADAS")
    private int tareas;
    
    @Column(name="CARGA_DE_TRABAJO")
    private int carga;

    public DashboardFuncionario() {
    }

    public DashboardFuncionario(String run, String nombre, String estado, int tareas, int carga) {
        this.run = run;
        this.nombre = nombre;
        this.estado = estado;
        this.tareas = tareas;
        this.carga = carga;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTareas() {
        return tareas;
    }

    public void setTareas(int tareas) {
        this.tareas = tareas;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
    
    
}
