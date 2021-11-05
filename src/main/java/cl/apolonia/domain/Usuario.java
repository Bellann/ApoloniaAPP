package cl.apolonia.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQuery;


/**
 * Esta clase apunta a la misma entidad de funcionarios, pero será necesria con sus
 * metodos para el login y la seguridad
 * @author monse
 */

@Entity
@NamedNativeQuery(
        name = "R_USUARIOS_WEB",
        callable= true,
        query = "(? = call R_USUARIOS_WEB())",
        resultClass = Procesos.class)
@Table(name="USUARIOS_WEB")
public class Usuario implements Serializable {
    
    private static final long serialversionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USERNAME")
    private String username;
    
    @Column(name="APELLIDO")
    private String apellido;
    
    @Column(name="IDSUBUNIDAD")
    private int idsubunidad;
    
   @Column(name="NIVEL")
    private int nivel;
    
    @Column(name="NOMBRE")
    private String nombre;
  
    @Column(name="PASSWORD")
    private String password;
    
    @Column(name="RAZONSOCIAL")
    private String razonsocial;
    
    @Column(name="RUTUNIDAD")
    private String rutunidad;
    
    @Column(name="SUBUNIDAD")
    private String subunidad;
    

    

    


    public Usuario() {
    }

    public Usuario(String username, String apellido, int idsubunidad, int nivel, String nombre, String password, String razonsocial, String rutunidad, String subunidad) {
        this.username = username;
        this.apellido = apellido;
        this.idsubunidad = idsubunidad;
        this.nivel = nivel;
        this.nombre = nombre;
        this.password = password;
        this.razonsocial = razonsocial;
        this.rutunidad = rutunidad;
        this.subunidad = subunidad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdsubunidad() {
        return idsubunidad;
    }

    public void setIdsubunidad(int idsubunidad) {
        this.idsubunidad = idsubunidad;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRutunidad() {
        return rutunidad;
    }

    public void setRutunidad(String rutunidad) {
        this.rutunidad = rutunidad;
    }

    public String getSubunidad() {
        return subunidad;
    }

    public void setSubunidad(String subunidad) {
        this.subunidad = subunidad;
    }

   

}
