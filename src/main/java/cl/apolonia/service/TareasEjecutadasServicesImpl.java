/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.apolonia.service;

import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.domain.TareasEjecutadas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        int idTareaEjecutada = 0;
            
            //Pasar de String a Date las variables de fechas
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

}
