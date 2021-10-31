package cl.apolonia.dao;

import cl.apolonia.domain.Funcionarios;
import cl.apolonia.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UsuarioDao extends JpaRepository<Usuario, String> {
    Usuario findByUsername(String username);
    

}
