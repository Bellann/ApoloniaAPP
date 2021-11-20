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
    public boolean crearTarea(int idproceso, String nombre, String descripcion, int duracion, String fecha1, String fecha2, int dependencia, int desagregada) {
        try {
            int idTareaEjecutada = 0;
            //Pasar de String a Date las variables de fechas
            String s = fecha1;
            Date d = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.S").parse(s);
            String sd = new SimpleDateFormat("dd/MM/yyyy").format(d);

            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("c_tarea_ejecutada")
                    .registerStoredProcedureParameter(1, int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Date.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Date.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(9, int.class, ParameterMode.OUT);
            query.setParameter(1, idproceso);
            query.setParameter(2, nombre);
            query.setParameter(3, descripcion);
            query.setParameter(4, duracion);
            query.setParameter(5, sd);
            query.setParameter(6, sd);
            query.setParameter(7, dependencia);
            query.setParameter(8, desagregada);

            query.execute();

            idTareaEjecutada = (int) query.getOutputParameterValue(idTareaEjecutada);
            if (idTareaEjecutada == 0) {
                return true;
            }

        } catch (ParseException ex) {
            Logger.getLogger(TareasEjecutadasServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
