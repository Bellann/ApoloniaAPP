package cl.apolonia.service;

import cl.apolonia.dao.UsuarioDao;
import cl.apolonia.domain.Usuario;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author monse
 */
@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null || usuario.getId_estado_usuario() != 1) {

            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>();

        
        switch (usuario.getId_perfil()) {
            case 1:
                roles.add(new SimpleGrantedAuthority("ROLE_GERENCIA"));
                break;
            case 2:
                roles.add(new SimpleGrantedAuthority("ROLE_SUPERVISOR"));
                break;

            default:
                if (usuario.getId_perfil() >2){
                roles.add(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
                }
                break;
        }
        return new User(usuario.getUsername(), usuario.getPassword(), roles);

    }
}
