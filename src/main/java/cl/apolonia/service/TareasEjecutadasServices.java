package cl.apolonia.service;

import cl.apolonia.domain.TareasEjecutadas;
import java.util.Date;

public interface TareasEjecutadasServices {
    
    /**
     *
     * @return confirmacion de creacion de tarea ejecutada segun proceso tipo
     */
    boolean crearTarea(int idproceso, String nombre, String descripcion, int duracion, String fecha1, String fecha2, int dependencia, int desagregada);
    
}
