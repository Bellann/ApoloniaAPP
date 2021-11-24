package cl.apolonia.service;

import cl.apolonia.domain.TareasEjecutadas;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TareasEjecutadasServices {
    
    
    boolean crearTarea(TareasEjecutadas tarea, int duracion, List<String>responsables, List<String> dependencias);
    boolean crearTarea(TareasEjecutadas tarea, int duracion, List<String>responsables, List<String> dependencias, int idTarea);
    boolean crearDesagregada(int idTarea, int idPadre);
    boolean crearResponsables(int tarea, String responsable);
    boolean crearDependencia(int tarea, String dependencia);
    boolean crearEstado(TareasEjecutadas tarea);
    boolean aceptarTarea(TareasEjecutadas tarea);
    boolean iniciarTarea(TareasEjecutadas tarea);

   LocalDate sumaDiasDeDuracion(Date fechaInicial, int days);
   TareasEjecutadas encontrarTarea (Integer id);
    
    
}
