package cl.apolonia.service;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.UsuarioDao;
import cl.apolonia.domain.Funcionarios;
import cl.apolonia.domain.Usuario;
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
    
    @Autowired
    private UsuarioDao usuarioDao;

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
        
        String run = funcionarioEncontrado.getRun();
        
        Usuario usuarioSaludo = new Usuario();
        usuarioSaludo = usuarioDao.findByUsername(currentPrincipalName);
        int perfil = usuarioSaludo.getNivel();
        
        
        
        switch (perfil) {
            case 1:
                rolSaludo = " (Gerencia) - área: " + usuarioSaludo.getSubunidad();
                break;
            case 2:
                rolSaludo = " (Supervisor) - área: " + usuarioSaludo.getSubunidad();
                break;
            default:             
                if (perfil >2){
                rolSaludo = " (Funcionario) - área: " + usuarioSaludo.getSubunidad();
                }
                
                break;
        }        
                
        return rolSaludo;
    }

    @Override
    public String rutUnidad() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        Funcionarios funcionarioEncontrado = new Funcionarios();
        funcionarioEncontrado = funcionariosDao.findById(currentPrincipalName).orElse(null);
        
        
        Usuario usuarioSaludo = new Usuario();
        usuarioSaludo = usuarioDao.findByUsername(currentPrincipalName);
        String rut = usuarioSaludo.getRutunidad();
        return rut;
    }

    @Override
    public Integer idSubunidad() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        Funcionarios funcionarioEncontrado = new Funcionarios();
        funcionarioEncontrado = funcionariosDao.findById(currentPrincipalName).orElse(null);
        
        
        Usuario usuarioSaludo = new Usuario();
        usuarioSaludo = usuarioDao.findByUsername(currentPrincipalName);
        Integer idSubunidad = usuarioSaludo.getIdsubunidad();
        return idSubunidad;
    }

    @Override
    public String runResponsable() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        
        return currentPrincipalName;
    }


}
