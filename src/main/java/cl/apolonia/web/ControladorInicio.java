package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosDao;
import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.domain.TareasEjecutadas;
import cl.apolonia.service.FuncionariosService;
import cl.apolonia.service.ProcesoEjecutadosService;
import cl.apolonia.service.ProcesosSerivce;
import cl.apolonia.service.TareasEjecutadasServices;
import cl.apolonia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("procesosUni", procesosUni);
        model.addAttribute("procesosSub", procesosSub);
        model.addAttribute("tareasRun", tareasRun);

        return "flujotrabajo";

    }

    @GetMapping("/ejecutarproceso")
    public String ejecutarproceso(Model model) {
        var procesos = procesosService.listarProcesos();
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("procesos", procesos);

        return "ejecutarproceso";
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

    @GetMapping("/gestionartarea")
    public String gestionarTarea(@RequestParam(value = "r") String urlParam,
            @RequestParam(value = "i") Integer i, Model model) {
        var tareasEjecutadas = tareasEjecutadasDao.findByRunResponsableAndIdtarea(urlParam, i);
        var estado = tareasEjecutadasService.estado(urlParam, i);
        var funcionarios = funcionariosService.listarFuncionarios();
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        var funcionariosList = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        model.addAttribute("run", urlParam);
        model.addAttribute("id", i);
        model.addAttribute("tareasEjecutadas", tareasEjecutadas);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("funcionariosList", funcionariosList);

        return "gestionartarea";
    }

}
