package cl.apolonia.web;

import cl.apolonia.service.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCarga {
    @Autowired
    private FuncionariosService funcionariosService;
    
        @GetMapping(value = "/micarga")
    public String micarga(Model model) {
        
        //Para el saludo
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);


        
        return "micarga";
    }
    
}
