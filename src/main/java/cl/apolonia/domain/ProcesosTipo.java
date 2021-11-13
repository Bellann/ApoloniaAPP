package cl.apolonia.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
        name = "r_proce_tipo_all_w",
        callable = true,
        query = "(? = call r_proce_tipo_all_w())",
        resultClass = ProcesosTipo.class)
@Table(name = "PROCESOSTIPO_WEB")
public class ProcesosTipo implements Serializable {

    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ID_SUBUNIDAD")
    private int idSubunidad;

    @Column(name = "ROL_EJECUTOR_MIN")
    private int idRolEjecMin;

    @Column(name = "RUN_DISENADOR")
    private String runDisenador;

    @Column(name = "NIVEL")
    private int nivel;

    @Column(name = "RUT_UNIDAD")
    private String rutUnidad;

    public ProcesosTipo() {
    }

    public ProcesosTipo(Integer id, String nombre, String descripcion, int idSubunidad, int idRolEjecMin, String runDisenador, int nivel, String rutUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idSubunidad = idSubunidad;
        this.idRolEjecMin = idRolEjecMin;
        this.runDisenador = runDisenador;
        this.nivel = nivel;
        this.rutUnidad = rutUnidad;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdSubunidad() {
        return idSubunidad;
    }

    public void setIdSubunidad(int idSubunidad) {
        this.idSubunidad = idSubunidad;
    }

    public int getIdRolEjecMin() {
        return idRolEjecMin;
    }

    public void setIdRolEjecMin(int idRolEjecMin) {
        this.idRolEjecMin = idRolEjecMin;
    }

    public String getRunDisenador() {
        return runDisenador;
    }

    public void setRunDisenador(String runDisenador) {
        this.runDisenador = runDisenador;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getRutUnidad() {
        return rutUnidad;
    }

    public void setRutUnidad(String rutUnidad) {
        this.rutUnidad = rutUnidad;
    }

   
}
