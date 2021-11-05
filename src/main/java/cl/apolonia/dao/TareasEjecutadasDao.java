package cl.apolonia.dao;

import cl.apolonia.domain.TareasEjecutadas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasEjecutadasDao extends JpaRepository<TareasEjecutadas, Integer> {
    
}
