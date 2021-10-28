package cl.apolonia.dao;

import cl.apolonia.domain.Funcionarios;
import org.springframework.data.repository.CrudRepository;

public interface FuncionariosDao extends CrudRepository<Funcionarios, String>{
    
}
