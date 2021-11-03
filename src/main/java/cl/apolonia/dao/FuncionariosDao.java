package cl.apolonia.dao;

import cl.apolonia.domain.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionariosDao extends JpaRepository<Funcionarios, String>{
    
    
}
