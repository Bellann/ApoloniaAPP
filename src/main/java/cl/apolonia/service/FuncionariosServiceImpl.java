package cl.apolonia.service;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.domain.Funcionarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("funcionarioService")
public class FuncionariosServiceImpl implements FuncionariosService {

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

    @Override
    public List<Funcionarios> rFuncionariosAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nombreCompleto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        Funcionarios funcionarioEncontrado = new Funcionarios();
        funcionarioEncontrado = funcionariosDao.findById(currentPrincipalName).orElse(null);
        
        String nombre = funcionarioEncontrado.getNombres();
        String apellido = funcionarioEncontrado.getApellidop();
        String nombreCompleto = nombre+' '+apellido;
        
        return nombreCompleto;
    }

    @Override
    public String rolSaludo() {
        String rolSaludo = "SIN ROL";
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        Funcionarios funcionarioEncontrado = new Funcionarios();
        funcionarioEncontrado = funcionariosDao.findById(currentPrincipalName).orElse(null);
        
        
        
        switch (funcionarioEncontrado.getId_perfil()) {
            case 1:
                rolSaludo = " (Gerencia)";
                break;
            case 2:
                rolSaludo = " (Supervisor)";
                break;
            case 3:
                rolSaludo =  " (Funcionario)";
                break;

            default:
                break;
        }        
                
        return rolSaludo;
    }

}
