package cl.apolonia.service;

import cl.apolonia.dao.ArchivoDao;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ArchivoService")
public class ArchivoServiceImpl implements ArchivoService {

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    private ArchivoDao archivoDao;
    
    @Override
    public int create(String nombre, String funcionario, int idTarea) {
        
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var id = 0;
        try {
            StoredProcedureQuery creaTarea = entityManager
                    .createStoredProcedureQuery("c_archivos")
                    .registerStoredProcedureParameter("i_nombre", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_fecha", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_run", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("i_id_tarea", int.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("o_id", Integer.class, ParameterMode.OUT);
            creaTarea.setParameter("i_nombre", nombre);
            creaTarea.setParameter("i_fecha", fecha);
            creaTarea.setParameter("i_run", funcionario);
            creaTarea.setParameter("i_id_tarea", idTarea);


            creaTarea.execute();
             id = (Integer) creaTarea.getOutputParameterValue("o_id");
            
        } catch (Exception e) {
            return 0;
        }
        return id;
    }
    
}
