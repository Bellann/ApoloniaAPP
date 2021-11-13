package cl.apolonia.dao;

import cl.apolonia.domain.TareasTipo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasTipoDao extends JpaRepository<TareasTipo, Integer> {
    
    List<TareasTipo> findByIdProcesoTipo(Integer idProceso);
}
