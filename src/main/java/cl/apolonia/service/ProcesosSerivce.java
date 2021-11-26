package cl.apolonia.service;

import cl.apolonia.domain.Procesos;
import java.util.List;

public interface ProcesosSerivce {
    public List<Procesos> listarProcesos();
    
    public Integer encontrarProceso (String proceso,Integer idSubunidad);


    
}
