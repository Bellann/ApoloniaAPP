/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.apolonia.service;

import cl.apolonia.dao.TareasEjecutadasDao;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public boolean crearTarea(int idproceso, String nombre, String descripcion, int duracion, Date fecha1) {

        //Dar fomato a las fechas Date 
        String fechaini = new SimpleDateFormat("dd/MM/yyyy").format(fecha1);
        

        StoredProcedureQuery creaTarea = entityManager
                .createStoredProcedureQuery("c_tarea_ejecutada")
                .registerStoredProcedureParameter(0, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, int.class, ParameterMode.IN);
                //.registerStoredProcedureParameter(8, int.class, ParameterMode.OUT);
        creaTarea.setParameter(0, idproceso);
        creaTarea.setParameter(1, nombre);
        creaTarea.setParameter(2, descripcion);
        creaTarea.setParameter(3, duracion);
        creaTarea.setParameter(4, fechaini);
        creaTarea.setParameter(5, fechaini);


        creaTarea.execute();
        
        StoredProcedureQuery creaResponsable = entityManager
                .createStoredProcedureQuery("c_resp_tarea_ejec")
                .registerStoredProcedureParameter(0, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, int.class, ParameterMode.IN);
                //.registerStoredProcedureParameter(8, int.class, ParameterMode.OUT);
        creaResponsable.setParameter(0, idproceso);
        creaResponsable.setParameter(1, nombre);


        creaResponsable.execute();

        return true;
    }

    public LocalDate sumaDiasDeDuracion(Date fechaInicial, int days) {
      
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
        return result;
    }
}
