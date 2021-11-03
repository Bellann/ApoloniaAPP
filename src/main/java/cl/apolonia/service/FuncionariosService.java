package cl.apolonia.service;

import cl.apolonia.domain.Funcionarios;
import java.util.List;

public interface FuncionariosService {
    public List<Funcionarios> listarFuncionarios();
    
    public Funcionarios encontrarFuncionarios(Funcionarios funcionarios);
    
    
    public String nombreCompleto();
    
    public String rolSaludo();
}
