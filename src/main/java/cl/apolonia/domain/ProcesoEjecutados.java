package cl.apolonia.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="PROCESO_EJECUTADOS", schema="APOLONIADB")
@SecondaryTable(name="SUBUNIDADES", pkJoinColumns = @PrimaryKeyJoinColumn(name ="ID"))
@SecondaryTable(name="PROCESO_EJECT_ESTADOS", pkJoinColumns = @PrimaryKeyJoinColumn(name ="ID_PROCESO_EJECUTADO"))

public class ProcesoEjecutados implements Serializable {
    
    private static final long serialversionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id_proceso;
    
    @Column(name="NOMBRE")
    private String nombre;
    
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @Column(name="RUN_EJECUTOR")
    private String run_ejecutor;
    
    @Column(name="FECHA_EJECUCION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_ejecucion;
    
    @Column(name="FECHA_INICIO")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_inicio;
    
    @Column(name="FECHA_PREVISTA_FIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_prevista_fin;
    
    @Column(name="FECHA_REAL_FIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_real_fin;
    
    @Column(name="ID_SUBUNIDAD")
    private int id_subunidad;
    
    @Column(name="RUN_DISENADOR")
    private int run_disenador;
    
    @Column(name="ID", table ="SUBUNIDADES",  insertable = false, updatable = false)
    private int id_subunidad2;
    
    @Column(name="NOMBRE", table ="SUBUNIDADES")
    private String nombreSubUni;
    
    @Column(name="DESCRIPCION", table ="SUBUNIDADES")
    private String descripcionSubUni;
    
    @Column(name="SUBUNIDAD_PADRE", table ="SUBUNIDADES", nullable = true)
    private Integer subunidad_padre;
    
    @Column(name="RUT_UNIDAD", table ="SUBUNIDADES")
    private String rut_unidad;
    
    @Column(name="ID_PROCESO_EJECUTADO", table ="PROCESO_EJECT_ESTADOS", insertable = false, updatable = false)
    private int id_proceso_ejecutado;
    
    @Column(name="FECHA_ESTADO", table ="PROCESO_EJECT_ESTADOS")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_estado;
    
    @Column(name="RUN_FUNCIONARIO", table ="PROCESO_EJECT_ESTADOS")
    private String run_funcionario;
    
    @Column(name="ID_ESTADO")
    private int id_estado_proceso;

    public ProcesoEjecutados(String nombre, String descripcion, String run_ejecutor, int id_subunidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.run_ejecutor = run_ejecutor;
        this.id_subunidad = id_subunidad;

    }
    
}
