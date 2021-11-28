package cl.apolonia.dao;

import cl.apolonia.domain.Archivo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoDao extends JpaRepository<Archivo, Integer> {
    
    List<Archivo> findByIdTarea(Integer idTarea);
    

    
}
