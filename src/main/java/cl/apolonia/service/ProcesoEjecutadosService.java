package cl.apolonia.service;

import cl.apolonia.domain.ProcesoEjecutados;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ProcesoEjecutadosService {

    public List<ProcesoEjecutados> listarProcesosEjec();
    
    public ProcesoEjecutados encontrarproceso(Integer idproceso);
    
    boolean crearProceso (ProcesoEjecutados proc, String fechaInicio, String runD, int duracion);
    
    LocalDate sumaDiasDeDuracion(Date date, int duracion);
    
}
