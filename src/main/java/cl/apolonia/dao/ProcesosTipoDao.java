package cl.apolonia.dao;


import cl.apolonia.domain.ProcesosTipo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcesosTipoDao extends JpaRepository<ProcesosTipo, Integer> {
    
    List<ProcesosTipo> findByRutUnidad(String rutUnidad);
    
    ProcesosTipo findByNombre(String nombre);
        

}
