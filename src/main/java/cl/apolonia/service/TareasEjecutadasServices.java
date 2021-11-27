package cl.apolonia.service;

import cl.apolonia.domain.Responsables;
import cl.apolonia.domain.TareasEjecutadas;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TareasEjecutadasServices {
    
    
    boolean crearTarea(TareasEjecutadas tarea, int duracion, List<String>responsables, List<String> dependencias);
    boolean crearTarea(TareasEjecutadas tarea, int duracion, String responsables);
    boolean crearTarea(TareasEjecutadas tarea, int duracion, List<String>responsables, List<String> dependencias, int idTarea);
    boolean crearDesagregada(int idTarea, int idPadre);
    boolean actualizarTarea(TareasEjecutadas tarea);
    
    boolean crearResponsables(int tarea, String responsable);
    boolean eliminarResponsable(int id);
    
    boolean crearDependencia(int tarea, String dependencia);
    
    boolean crearEstado(TareasEjecutadas tarea);
    boolean cambiarEstado(TareasEjecutadas tarea, int estado);
    
    boolean crearObservacion(TareasEjecutadas tarea, String run, String comentario);
    

   LocalDate sumaDiasDeDuracion(Date fechaInicial, int days);
   TareasEjecutadas encontrarTarea (Integer id);
   List<TareasEjecutadas> listarXProceso(Integer id_proceso);
   
   
    
    
}
