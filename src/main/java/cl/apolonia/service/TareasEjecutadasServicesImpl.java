/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.apolonia.service;

import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.domain.TareasEjecutadas;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareasEjecutadasServicesImpl implements TareasEjecutadasServices {

    @Autowired
    private TareasEjecutadasDao tareasEjecutadasDao;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public  boolean crearTarea(TareasEjecutadas tarea) {

        //Dar fomato a las fechas Date 
        String fechaini = new SimpleDateFormat("dd/MM/yyyy").format(tarea.getfPrevInicio());
        try { 
        StoredProcedureQuery creaTarea = entityManager
                .createStoredProcedureQuery("c_tarea_ejecutada_prueba")
                .registerStoredProcedureParameter("i_id_proceso_ejecutado", int.class, ParameterMode.IN)
                .registerStoredProcedureParameter("i_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("i_descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("i_duracion", int.class, ParameterMode.IN)
                .registerStoredProcedureParameter("i_fch_previs_inicio", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("i_fch_previs_fin", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("i_id_tarea", Integer.class, ParameterMode.OUT);
        creaTarea.setParameter("i_id_proceso_ejecutado", tarea.getIdProcesoEjecutado());
        creaTarea.setParameter("i_nombre", tarea.getTarea());
        creaTarea.setParameter("i_descripcion", tarea.getDescTarea());
        creaTarea.setParameter("i_duracion", 1);
        creaTarea.setParameter("i_fch_previs_inicio", fechaini);
        creaTarea.setParameter("i_fch_previs_fin", fechaini);
          
        creaTarea.execute();
        var id = (Integer)creaTarea.getOutputParameterValue("i_id_tarea");
        System.out.println(id);
        tarea.setIdtarea(id);

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        
        
       
        return true;
    }

    public String sumaDiasDeDuracion(Date fechaInicial, int days) {
      
        System.out.println(fechaInicial);
        
        //pasar el util.date a local date
        LocalDate result = fechaInicial.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
        System.out.println(result);
      //agregar días
       int addedDays = 0;
        while (addedDays < days) {
           
            System.out.println(result);
            System.out.println(result.getDayOfWeek());
            
            result = result.plusDays(1);
            
            System.out.println(result);
            System.out.println(result.getDayOfWeek());

           if (!(result.getDayOfWeek().toString() == "SATURDAY" || result.getDayOfWeek().toString() == "SUNDAY")) {
               ++addedDays;
           }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return result.format(formatter);
    }
}
