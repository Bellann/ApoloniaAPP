package cl.apolonia.service;

import cl.apolonia.domain.Procesos;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ProcesosSerivce {
    public List<Procesos> listarProcesos();
    
    public Procesos encontrarproceso(Integer idproceso);


        boolean crearProceso (Procesos proc, String fechaInicio, String runD, int duracion);
    
        LocalDate sumaDiasDeDuracion(Date date, int duracion);
}
