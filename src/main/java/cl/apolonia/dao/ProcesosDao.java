package cl.apolonia.dao;

import cl.apolonia.domain.Procesos;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcesosDao extends JpaRepository<Procesos, Integer>{

    List<Procesos> findByRutUnidad(String rutUnidad);
    List<Procesos> findByIdSubunidad(Integer idSubunidad);
    Procesos findByProcesoAndIdSubunidad (String proceso,Integer idSubunidad);
    
}
