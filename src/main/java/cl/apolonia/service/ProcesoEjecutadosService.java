package cl.apolonia.service;

import cl.apolonia.domain.ProcesoEjecutados;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ProcesoEjecutadosService {

    public List<ProcesoEjecutados> listarProcesosEjec();
    

    
}
