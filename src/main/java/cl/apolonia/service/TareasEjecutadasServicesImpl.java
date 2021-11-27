package cl.apolonia.service;

import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.domain.Responsables;
import cl.apolonia.domain.TareasEjecutadas;
import java.text.DateFormat;
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
    public boolean crearTarea(TareasEjecutadas tarea, int duracion, List<String> responsables, List<String> dependencias, int idTarea) {
            
        String fechaini = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate fechaSumar = sumaDiasDeDuracion(new Date(System.currentTimeMillis()),duracion);
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
                    .registerStoredProcedureParameter("i_usuario_conectado", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("o_id_tarea", Integer.class, ParameterMode.OUT);
            creaTarea.setParameter("i_id_proceso_ejecutado", tarea.getIdProcesoEjecutado());
            creaTarea.setParameter("i_nombre", tarea.getTarea());
            creaTarea.setParameter("i_descripcion", tarea.getDescTarea());
            creaTarea.setParameter("i_duracion", duracion);
            creaTarea.setParameter("i_fch_previs_inicio", fechaini);
            creaTarea.setParameter("i_fch_previs_fin", fechaTerm);
            creaTarea.setParameter("i_usuario_conectado", tarea.getRunEjecutor());

            creaTarea.execute();
            var id = (Integer) creaTarea.getOutputParameterValue("o_id_tarea");
            tarea.setIdtarea(id);
            
            crearDesagregada(id, idTarea);
            if(responsables != null)responsables.stream().forEach((p)-> crearResponsables(id, p) );

        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean crearTarea(TareasEjecutadas tarea, int duracion, List<String> responsables, List<String> dependencias) {
        
        //Dar fomato a las fechas Date 

        String fechaini = new SimpleDateFormat("dd/MM/yyyy").format(tarea.getfPrevInicio());
        LocalDate fechaSumar = sumaDiasDeDuracion(tarea.getfPrevInicio(), duracion);
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
                    .registerStoredProcedureParameter("i_usuario_conectado", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("o_id_tarea", Integer.class, ParameterMode.OUT);
            creaTarea.setParameter("i_id_proceso_ejecutado", tarea.getIdProcesoEjecutado());
            creaTarea.setParameter("i_nombre", tarea.getTarea());
            creaTarea.setParameter("i_descripcion", tarea.getDescTarea());
            creaTarea.setParameter("i_duracion", duracion);
            creaTarea.setParameter("i_fch_previs_inicio", fechaini);
            creaTarea.setParameter("i_fch_previs_fin", fechaTerm);
            creaTarea.setParameter("i_usuario_conectado", tarea.getRunEjecutor());

            creaTarea.execute();
            var id = (Integer) creaTarea.getOutputParameterValue("o_id_tarea");
            tarea.setIdtarea(id);
            
            if(responsables != null)responsables.stream().forEach((p)-> crearResponsables(id, p) );
            if(dependencias != null)dependencias.stream().forEach((p)-> crearDependencia(id, p));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
   
    @Override
    public boolean crearTarea(TareasEjecutadas tarea, int duracion, String responsables) {

        String fechaini = new SimpleDateFormat("dd/MM/yyyy").format(tarea.getfPrevInicio());
        LocalDate fechaSumar = sumaDiasDeDuracion(tarea.getfPrevInicio(), duracion);
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
                    .registerStoredProcedureParameter("i_usuario_conectado", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("o_id_tarea", Integer.class, ParameterMode.OUT);
            creaTarea.setParameter("i_id_proceso_ejecutado", tarea.getIdProcesoEjecutado());
            creaTarea.setParameter("i_nombre", tarea.getTarea());
            creaTarea.setParameter("i_descripcion", tarea.getDescTarea());
            creaTarea.setParameter("i_duracion", duracion);
            creaTarea.setParameter("i_fch_previs_inicio", fechaini);
            creaTarea.setParameter("i_fch_previs_fin", fechaTerm);
            creaTarea.setParameter("i_usuario_conectado", tarea.getRunEjecutor());

            creaTarea.execute();
            var id = (Integer) creaTarea.getOutputParameterValue("o_id_tarea");
            tarea.setIdtarea(id);
            
            crearResponsables(id,responsables);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    @Override
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
    public boolean crearResponsables(int tarea, String responsable) {
        try {
            
            
            StoredProcedureQuery crearResponsable = entityManager
                    .createStoredProcedureQuery("c_resp_tarea_ejec")
                    .registerStoredProcedureParameter("i_id_tarea_ejecutada", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_run_funcionario", String.class, ParameterMode.IN);
            crearResponsable.setParameter("i_id_tarea_ejecutada", tarea);
            crearResponsable.setParameter("i_run_funcionario", responsable);

            crearResponsable.execute();


        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean crearDependencia(int tarea, String dependencia) {
        try {
            int id = Integer.parseInt(dependencia);

            StoredProcedureQuery crearResponsable = entityManager
                    .createStoredProcedureQuery("c_dependen_tarea_ej")
                    .registerStoredProcedureParameter("i_id_tarea_ejecutada", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_id_tarea_previa", int.class, ParameterMode.IN);
            crearResponsable.setParameter("i_id_tarea_ejecutada", tarea);
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

    @Override
    public boolean cambiarEstado(TareasEjecutadas tarea, int estado) {

        String fechaInicio = "";
        String fechaTermino = "";
        String comentario ="";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        switch(estado) {
            case 2:
                comentario = "Aceptada";
                break;
            case 3:
                comentario = "En Desarrollo";
                fechaInicio = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            case 4:
                comentario = "En Revision";
                break;
            case 6:
                fechaInicio = dateFormat.format(tarea.getfRealInicio());
                fechaTermino = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
        }
        
        try {
            StoredProcedureQuery cmd = entityManager
                    .createStoredProcedureQuery("u_tarea_ejec")
                    .registerStoredProcedureParameter("i_id_tarea_ejec", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_fch_real_inicio", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_fch_real_fin", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_id_estado", int.class, ParameterMode.IN);
            cmd.setParameter("i_id_tarea_ejec", tarea.getIdtarea());
            cmd.setParameter("i_fch_real_inicio", fechaInicio);
            cmd.setParameter("i_fch_real_fin", fechaTermino);
            cmd.setParameter("i_id_estado", estado);

            cmd.execute();
            
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    public boolean crearDesagregada(int idTarea, int idPadre) {
        //Dar fomato a las fechas Date 
        try {
            StoredProcedureQuery crearResponsable = entityManager
                    .createStoredProcedureQuery("c_tarea_desagregada")
                    .registerStoredProcedureParameter("i_id_tarea_padre", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_id_tarea_desagregada", int.class, ParameterMode.IN);
            crearResponsable.setParameter("i_id_tarea_padre", idPadre);
            crearResponsable.setParameter("i_id_tarea_desagregada", idTarea);

            crearResponsable.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    

    @Override
    public boolean crearObservacion(TareasEjecutadas tarea, String run, String comentario) {
        
        String fechaini = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        try {
            StoredProcedureQuery cmd = entityManager
                    .createStoredProcedureQuery("c_observaciones")
                    .registerStoredProcedureParameter("i_id_tarea", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_fecha", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_run", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_comentario", String.class, ParameterMode.IN);

            cmd.setParameter("i_id_tarea", tarea.getIdtarea());
            cmd.setParameter("i_fecha", fechaini);
            cmd.setParameter("i_run", run);
            cmd.setParameter("i_comentario", comentario);


            cmd.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<TareasEjecutadas> listarXProceso(Integer id_proceso) {
        return tareasEjecutadasDao.findByIdProcesoEjecutado(id_proceso);
    }

    @Override
    public boolean actualizarTarea(TareasEjecutadas tarea) {
        try {
            
        StoredProcedureQuery cmd = entityManager
                    .createStoredProcedureQuery("u_tarea_ejec_gestion")
                    .registerStoredProcedureParameter("i_id_tarea_ejec", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_descripcion", String.class, ParameterMode.IN);

            cmd.setParameter("i_id_tarea_ejec", tarea.getIdtarea());
            cmd.setParameter("i_descripcion", tarea.getDescTarea());
            
            cmd.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminarResponsable(int id) {
        try {
            
        StoredProcedureQuery cmd = entityManager
                    .createStoredProcedureQuery("d_responsable_te_by_id")
                    .registerStoredProcedureParameter("i_id_tarea_ejec", int.class, ParameterMode.IN);

            cmd.setParameter("i_id_tarea_ejec", id);

            cmd.execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    







}
