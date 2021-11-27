package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosDao;
import cl.apolonia.dao.ProcesosTipoDao;
import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.dao.TareasTipoDao;
import cl.apolonia.service.DashboardFuncionarioService;
import cl.apolonia.service.DashboardService;
import cl.apolonia.service.FuncionariosService;
import cl.apolonia.service.ProcesoEjecutadosService;
import cl.apolonia.service.ProcesosSerivce;
import cl.apolonia.service.ProcesosTipoService;
import cl.apolonia.service.TareasEjecutadasServices;
import cl.apolonia.service.procParticipoService;
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

    @Autowired
    private ProcesosDao procesosDao;

    @Autowired
    private TareasEjecutadasDao tareasEjecutadasDao;

    @Autowired
    private TareasEjecutadasServices tareasEjecutadasService;

    @Autowired
    private FuncionariosDao funcionariosDao;

    @Autowired
    private procParticipoService procParticipoService;

    @Autowired
    private ProcesosTipoService procesosTipoService;

    @Autowired
    private ProcesosTipoDao procesosTipoDao;

    @Autowired
    private TareasTipoDao tareasTipoDao;

    @Autowired
    private DashboardService dashboard;
    
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

        var funcionarios = funcionariosService.listarFuncionarios();
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        var procesosUni = procesosDao.findByRutUnidad(funcionariosService.rutUnidad());
        var procesosSub = procesosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        var tareasRun = tareasEjecutadasDao.findByRunResponsable(funcionariosService.runResponsable());
        var misProcesos = procParticipoService.procParticipo(funcionariosService.runResponsable());
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("procesosUni", procesosUni);
        model.addAttribute("procesosSub", procesosSub);
        model.addAttribute("tareasRun", tareasRun);
        model.addAttribute("misProcesos", misProcesos);

        
        return "flujotrabajo";

    }

   
    @GetMapping("/flujoempresa")
    public String flujoempresa(Model model) {

        var funcionarios = funcionariosService.listarFuncionarios();
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        var procesosUni = procesosDao.findByRutUnidad(funcionariosService.rutUnidad());
        var procesosSub = procesosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        var tareasRun = tareasEjecutadasDao.findByRunResponsable(funcionariosService.runResponsable());
        var tareasUnidad = tareasEjecutadasDao.findByRutUnidad(funcionariosService.rutUnidad());
        var tareasSubUni = tareasEjecutadasDao.findByIdSubUnidad(funcionariosService.idSubunidad());
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("procesosUni", procesosUni);
        model.addAttribute("procesosSub", procesosSub);
        model.addAttribute("tareasRun", tareasRun);
        model.addAttribute("tareasUnidad", tareasUnidad);
        model.addAttribute("tareasSubUni", tareasSubUni);

        return "flujoempresa";

    }

    
    
}
