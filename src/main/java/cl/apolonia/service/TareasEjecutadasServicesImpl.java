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
    public boolean crearTarea(int idproceso, String nombre, String descripcion, int duracion, Date fecha1, int dependencia, int desagregada) {

        //Dar fomato a las fechas Date 
        String sd = new SimpleDateFormat("dd/MM/yyyy").format(fecha1);

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("c_tarea_ejecutada_prueba")
                .registerStoredProcedureParameter(0, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, int.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, int.class, ParameterMode.IN);
        //.registerStoredProcedureParameter(8, int.class, ParameterMode.OUT);
        query.setParameter(0, idproceso);
        query.setParameter(1, nombre);
        query.setParameter(2, descripcion);
        query.setParameter(3, duracion);
        query.setParameter(4, sd);
        query.setParameter(5, sd);
        query.setParameter(6, dependencia);
        query.setParameter(7, desagregada);

        query.execute();

        return true;
    }

    public LocalDate sumaDiasDeDuracion(Date dateToConvert, int days) {
        LocalDate result = dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();

       int addedDays = 0;
        while (addedDays < days) {
           result = result.plusDays(1);
           if (!(result.getDayOfWeek() == DayOfWeek.SUNDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
               ++addedDays;
           }
        }
        
        return result;
    }
}
