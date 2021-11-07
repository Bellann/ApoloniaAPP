package cl.apolonia.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="FUNCIONARIOS")
public class Funcionarios implements Serializable {
    
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
    private int idSubunidad;
    private String username;
    
    
    
    
    
}
