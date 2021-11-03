package cl.apolonia.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import lombok.Data;

/**
 * Esta clase apunta a la misma entidad de funcionarios, pero será necesria con sus
 * metodos para el login y la seguridad
 * @author monse
 */

@Data
@Entity
@Table(name="FUNCIONARIOS")

public class Usuario implements Serializable {
    
    private static final long serialversionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String run;
    
    private String nombres;
    private String apellidop;
    private String apellidom;
    private String email;
    private String password;
    private int id_estado_usuario;
    private int id_rol;
    private String rut_unidad;
    private int id_subunidad;
    private int id_perfil;
    private String username;
    
    
    
    
    
}
   
