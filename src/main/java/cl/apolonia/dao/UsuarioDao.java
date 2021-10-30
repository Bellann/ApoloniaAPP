package cl.apolonia.dao;

import cl.apolonia.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioDao extends JpaRepository<Usuario, String> {
    Usuario findByUsername(String username);
    
}
