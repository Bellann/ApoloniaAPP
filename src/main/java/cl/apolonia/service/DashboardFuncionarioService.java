package cl.apolonia.service;

import cl.apolonia.domain.DashboardFuncionario;
import java.util.List;
        
public interface DashboardFuncionarioService {
    
    public List<DashboardFuncionario> listar(String run);
    
}
