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
        var nombreCompleto = funcionariosService.nombreCompleto();
        var prueba = "Soy string de prueba";
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("prueba", prueba);
        model.addAttribute("rolsaludo", rolSaludo);
        
    return "index";
    }
    
        @GetMapping("/login")
    public String login() {
 
        return "login";
 
    }
}
