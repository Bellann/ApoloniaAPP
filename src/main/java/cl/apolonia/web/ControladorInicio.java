package cl.apolonia.web;

import cl.apolonia.dao.UsuarioDao;
import cl.apolonia.service.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {
    
    @Autowired
    private FuncionariosService funcionariosService; 
   
    
    @GetMapping("/")
    public String inicio(Model model){
        var funcionarios = funcionariosService.listarFuncionarios();
        
        model.addAttribute("funcionarios", funcionarios);
        
    return "index";
    }
    
        @GetMapping("/login")
    public String login() {
 
        return "login";
 
    }
}
