package cl.apolonia.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RESP_TAREA_EJECUTADAS")
public class Responsables implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", insertable = false, updatable = false)
    private Integer id;
    
    @Column(name="ID_TAREA_EJECUTADA")
    private Integer idtarea;
    
    @Column(name="RUN_FUNCIONARIO")
    private String runFuncionario;

    public Responsables() {
    }

    public Responsables(Integer id, Integer idtarea, String runFuncionario) {
        this.id = id;
        this.idtarea = idtarea;
        this.runFuncionario = runFuncionario;
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

    public String getRunFuncionario() {
        return runFuncionario;
    }

    public void setRunFuncionario(String runFuncionario) {
        this.runFuncionario = runFuncionario;
    }
    
    
}
