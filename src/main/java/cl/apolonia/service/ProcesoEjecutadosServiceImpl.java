package cl.apolonia.service;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesoEjecutadosDao;
import cl.apolonia.domain.ProcesoEjecutados;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

@Service("procesoEjecutadosService")
public class ProcesoEjecutadosServiceImpl implements ProcesoEjecutadosService {

    @Autowired
    private ProcesoEjecutadosDao procesoEjecutadosDao;
    
    @Autowired
    private FuncionariosDao funcionariosDao;

    @Override
    public List<ProcesoEjecutados> listarProcesosEjec() {
         List<ProcesoEjecutados> procesos = procesoEjecutadosDao.findAll();
         return procesos;
    }

    @Override
    public ProcesoEjecutados encontrarproceso(Integer idproceso) {
        return procesoEjecutadosDao.findById(idproceso).orElse(null);
    }

    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public boolean crearProceso(ProcesoEjecutados proc, String fechaInicio, String runD,int duracion) {
        

        Date fecha;
        String fechaTerm = "";
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicio);
            fechaTerm = sumaDiasDeDuracion(fecha,duracion).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (ParseException ex) {
            Logger.getLogger(ProcesoEjecutadosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            StoredProcedureQuery creaTarea = entityManager
                    .createStoredProcedureQuery("c_proceso_ejecutado")
                    .registerStoredProcedureParameter("i_id_subunidad", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_nombre", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_descripcion", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_run_ejecutor", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_fecha_inicio", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_fecha_prevista_fin", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_run_disenador", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("o_id_proceso_creado", Integer.class, ParameterMode.OUT);
            creaTarea.setParameter("i_id_subunidad", proc.getId_subunidad());
            creaTarea.setParameter("i_nombre", proc.getNombre());
            creaTarea.setParameter("i_descripcion", proc.getDescripcion());
            creaTarea.setParameter("i_run_ejecutor", proc.getRun_ejecutor());
            creaTarea.setParameter("i_fecha_inicio", fechaInicio);
            creaTarea.setParameter("i_fecha_prevista_fin", fechaTerm);
            creaTarea.setParameter("i_run_disenador", runD);

            creaTarea.execute();
            var id = (Integer) creaTarea.getOutputParameterValue("o_id_proceso_creado");
            proc.setId_proceso_ejecutado(id);
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public LocalDate sumaDiasDeDuracion(Date date, int days) {

        //pasar el util.date a local date
        LocalDate result = date.toInstant()
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
