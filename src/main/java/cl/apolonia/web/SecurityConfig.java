package cl.apolonia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase para personalizar la seguridad de SpringBoot, ojo que se debe mantener
 * el NoOpPasswordEncoder o comenzará a pedir pass encriptadas a la base de
 * datos
 *
 * @author monse
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
    
        build.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }
    
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()                  
                .antMatchers("/")
                    .hasAnyRole("FUNCIONARIO", "SUPERVISOR", "GERENCIA")
                .and()
                    .formLogin()
                    .loginPage("/login");
    }
}

