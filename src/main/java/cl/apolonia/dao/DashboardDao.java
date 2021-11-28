package cl.apolonia.dao;

import cl.apolonia.domain.Dashboard;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DashboardDao extends JpaRepository<Dashboard, String>{
    
    List<Dashboard> findByRunFuncionario(String run);
    List<Dashboard> findByIdSubunidad (Integer id);
    List<Dashboard> findByRutUnidad (String rut);

}
