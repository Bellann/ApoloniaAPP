/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.apolonia.service;

import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.domain.Responsables;
import cl.apolonia.domain.TareasEjecutadas;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
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
    public boolean crearTarea(TareasEjecutadas tarea, int duracion) {

        //Dar fomato a las fechas Date 
        String fechaini = new SimpleDateFormat("dd/MM/yyyy").format(tarea.getfPrevInicio());
        //Sumar días de duracion
        LocalDate fechaSumar = sumaDiasDeDuracion(tarea.getfPrevInicio(), duracion);
        //paso a String fecha final
        String fechaTerm = fechaSumar.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
            creaTarea.setParameter("i_fch_previs_fin", fechaTerm);

            creaTarea.execute();
            var id = (Integer) creaTarea.getOutputParameterValue("i_id_tarea");
            tarea.setIdtarea(id);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public LocalDate sumaDiasDeDuracion(Date fechaInicial, int days) {

        //pasar el util.date a local date
        LocalDate result = fechaInicial.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        //agregar días
        int addedDays = 0;
        while (addedDays < days) {

            result = result.plusDays(1);

            if (!(result.getDayOfWeek().toString() == "SATURDAY" || result.getDayOfWeek().toString() == "SUNDAY")) {
                ++addedDays;
            }
        }

        return result;
    }





}
