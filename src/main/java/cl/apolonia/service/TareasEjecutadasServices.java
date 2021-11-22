package cl.apolonia.service;

import cl.apolonia.domain.TareasEjecutadas;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TareasEjecutadasServices {
    
    
    boolean crearTarea(TareasEjecutadas tarea, int duracion, List<String>responsables, List<String> dependencias);
    boolean crearResponsables(TareasEjecutadas tarea, String responsable);
    boolean crearDependencia(TareasEjecutadas tarea, String dependencia);
    boolean crearEstado(TareasEjecutadas tarea);
       
   LocalDate sumaDiasDeDuracion(Date fechaInicial, int days);
   TareasEjecutadas encontrarTarea (Integer id);
    
    
}
