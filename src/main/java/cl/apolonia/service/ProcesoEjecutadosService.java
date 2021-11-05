package cl.apolonia.service;

import cl.apolonia.domain.ProcesoEjecutados;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ProcesoEjecutadosService {

    public List<ProcesoEjecutados> listarProcesosEjec();
    
    @Query("SELECT * from procesoejecutados p where p.rut_unidad=?1 ")
    public List<ProcesoEjecutados> findByRut_unidad(String rut_unidad);

    
}
