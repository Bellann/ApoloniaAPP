package cl.apolonia.dao;

import cl.apolonia.domain.Procesos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcesosDao extends JpaRepository<Procesos, String>{
    
}
