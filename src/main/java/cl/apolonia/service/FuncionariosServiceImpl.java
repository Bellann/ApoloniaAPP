package cl.apolonia.service;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.domain.Funcionarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionariosServiceImpl implements FuncionariosService{

    @Autowired
    private FuncionariosDao funcionariosDao;
    
    
    @Override
    @Transactional
    public List<Funcionarios> listarFuncionarios() {
        return (List<Funcionarios>) funcionariosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionarios encontrarFuncionarios(Funcionarios funcionarios) {
        return funcionariosDao.findById(funcionarios.getRun()).orElse(null);
        
    }
    
}
