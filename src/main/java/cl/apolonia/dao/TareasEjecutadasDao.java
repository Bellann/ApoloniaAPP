package cl.apolonia.dao;

import cl.apolonia.domain.TareasEjecutadas;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasEjecutadasDao extends JpaRepository<TareasEjecutadas, Integer> {

    List<TareasEjecutadas> findByRunResponsable(String runResponsable);

    List<TareasEjecutadas> findByRutUnidad(String rutUnidad);

    List<TareasEjecutadas> findByIdSubUnidad(Integer idSubUnidad);
    
    List<TareasEjecutadas> findByRunResponsableAndIdtarea(String runResponsable, Integer idtarea);
    
    @Override
    Optional<TareasEjecutadas> findById(Integer id); 
}
