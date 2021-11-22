package cl.apolonia.service;

import cl.apolonia.domain.TareasEjecutadas;
import java.time.LocalDate;
import java.util.Date;

public interface TareasEjecutadasServices {
    
    
    boolean crearTarea(TareasEjecutadas tarea, int duracion);
    //boolean crearResponsable ():
    
    boolean crearResponsable(String runResponsable);
    
   
    
    
}
