package cl.apolonia.service;

import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.domain.TareasEjecutadas;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    public boolean crearTarea(TareasEjecutadas tarea, int duracion, List<String> responsables, List<String> dependencias) {

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
            
            responsables.stream().forEach((p)-> crearResponsables(tarea, p) );
            dependencias.stream().forEach((p)-> crearDependencia(tarea, p));
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

    @Override
    public boolean crearResponsables(TareasEjecutadas tarea, String responsable) {
        try {
            
            System.out.println(responsable);
            System.out.println(tarea.getIdtarea());
            
            StoredProcedureQuery crearResponsable = entityManager
                    .createStoredProcedureQuery("c_resp_tarea_ejec")
                    .registerStoredProcedureParameter("i_id_tarea_ejecutada", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_run_funcionario", String.class, ParameterMode.IN);
            crearResponsable.setParameter("i_id_tarea_ejecutada", tarea.getIdtarea());
            crearResponsable.setParameter("i_run_funcionario", responsable);

            crearResponsable.execute();


        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean crearDependencia(TareasEjecutadas tarea, String dependencia) {
        try {
            System.out.println("Entre");
            int id = Integer.parseInt(dependencia);

            StoredProcedureQuery crearResponsable = entityManager
                    .createStoredProcedureQuery("c_dependen_tarea_ej")
                    .registerStoredProcedureParameter("i_id_tarea_ejecutada", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_id_tarea_previa", int.class, ParameterMode.IN);
            crearResponsable.setParameter("i_id_tarea_ejecutada", tarea.getIdtarea());
            crearResponsable.setParameter("i_id_tarea_previa", id);

            crearResponsable.execute();


        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean crearEstado(TareasEjecutadas tarea) {
        try {
            
            //Estado inicial siempre sera 1, para avanzar en los estados sera el update
            int estado=1;
            //Fecha del dia de ejecucion LocalDate + paso a String para mandar a oracle
            
            String fecha =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            StoredProcedureQuery creaEstado = entityManager
                    .createStoredProcedureQuery("c_tarea_eject_estados")
                    .registerStoredProcedureParameter("i_id_tarea_ejecutada", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_fecha_estado", int.class, ParameterMode.IN)
            .registerStoredProcedureParameter("i_run_funcionario", int.class, ParameterMode.IN)
            .registerStoredProcedureParameter("i_id_estado_tarea", int.class, ParameterMode.IN);
            creaEstado.setParameter("i_id_tarea_ejecutada", tarea.getIdtarea());
            creaEstado.setParameter("i_fecha_estado", fecha);
            creaEstado.setParameter("i_run_funcionario", tarea.getResponsables());
            creaEstado.setParameter("i_id_estado_tarea", estado);

            creaEstado.execute();


        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public TareasEjecutadas encontrarTarea(Integer id) {
        
        return tareasEjecutadasDao.findFirstByIdtarea(id).orElse(null);
    }





}
