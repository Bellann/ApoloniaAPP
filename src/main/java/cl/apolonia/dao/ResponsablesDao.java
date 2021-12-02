package cl.apolonia.dao;

import cl.apolonia.domain.Responsables;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsablesDao extends JpaRepository<Responsables, Integer>{
    
    List<Responsables> findByIdtarea(Integer idtarea);
    
}
