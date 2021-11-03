package cl.apolonia.web;

import cl.apolonia.service.FuncionariosService;
import cl.apolonia.service.ProcesoEjecutadosService;
import cl.apolonia.service.ProcesosSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorInicio {

    @Autowired
    private FuncionariosService funcionariosService;
    
    @Autowired
    private ProcesoEjecutadosService procesoEjecutadosService;
    
    @Autowired
    private ProcesosSerivce procesosService;

    @GetMapping("/")
    public String inicio(Model model) {
        
        var funcionarios = funcionariosService.listarFuncionarios();
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);

        return "index";
    }

    @GetMapping("/login")
    public String login() {

        return "login";

    }

    @GetMapping("/flujotrabajo")
    public String flujotrabajo(Model model) {
        
        var procesosEjec = procesoEjecutadosService.listarProcesosEjec();
        var funcionarios = funcionariosService.listarFuncionarios();
        var procesos = procesosService.listarProcesos();
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        var noimplementado = "No implementado";
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("procesoejec", procesosEjec);
        model.addAttribute("noimplementado", noimplementado);
         model.addAttribute("procesos", procesos);

        return "flujotrabajo";

    }

}
