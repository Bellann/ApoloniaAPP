package cl.apolonia.dao;

import cl.apolonia.domain.TareasEjecutadas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasEjecutadasDao extends JpaRepository<TareasEjecutadas, Integer> {

    List<TareasEjecutadas> findByRunResponsable(String runResponsable);

    List<TareasEjecutadas> findByRutUnidad(String rutUnidad);

    List<TareasEjecutadas> findByIdSubUnidad(Integer idSubUnidad);
}
