package cl.apolonia.dao;

import cl.apolonia.domain.Funcionarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionariosDao extends JpaRepository<Funcionarios, String>{
    List<Funcionarios> findByIdSubunidad(Integer idSubunidad);
    List<Funcionarios> findByRutunidad(String rutunidad);
    
  

    
}
