package cl.apolonia.service;

import cl.apolonia.dao.DashboardFuncionarioDao;
import cl.apolonia.domain.DashboardFuncionario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DashboardFuncionarioService")
public class DashboardFuncionarioServiceImpl implements DashboardFuncionarioService {

    @Autowired
    private DashboardFuncionarioDao dashboard;
   
    
    @Override
    public List<DashboardFuncionario> listar(String run) {
        
        List<DashboardFuncionario> lista = dashboard.findAll();
        
        lista.removeIf(p -> !p.getRun().equals(run));
        
        return lista;
        
        
        
    }
    
}
