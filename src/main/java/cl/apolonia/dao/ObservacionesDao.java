package cl.apolonia.dao;
import cl.apolonia.domain.Observaciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservacionesDao extends JpaRepository<Observaciones, Integer>{
    
  List<Observaciones> findByIdtarea(Integer idtarea);

    
}
