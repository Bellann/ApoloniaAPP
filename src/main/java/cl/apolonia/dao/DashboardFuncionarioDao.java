package cl.apolonia.dao;

import cl.apolonia.domain.DashboardFuncionario;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardFuncionarioDao extends JpaRepository<DashboardFuncionario, String> {
    
    
}
