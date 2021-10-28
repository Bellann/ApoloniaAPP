package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {
    
    @Autowired
    private FuncionariosDao funcionarioDao; 
    
    @GetMapping("/")
    public String inicio(Model model){
        var funcionarios = funcionarioDao.findAll();
        model.addAttribute("funcionarios", funcionarios);
    return "index";
    }
    
}
