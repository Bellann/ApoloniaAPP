package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosDao;
import cl.apolonia.dao.ProcesosTipoDao;
import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.dao.TareasTipoDao;
import cl.apolonia.domain.Funcionarios;
import cl.apolonia.domain.Responsables;
import cl.apolonia.domain.TareasEjecutadas;
import cl.apolonia.service.FuncionariosService;
import cl.apolonia.service.ProcesoEjecutadosService;
import cl.apolonia.service.ProcesosSerivce;
import cl.apolonia.service.ProcesosTipoService;
import cl.apolonia.service.TareasEjecutadasServices;
import cl.apolonia.service.procParticipoService;
import java.util.ArrayList;
import java.util.Date;
import static java.util.Optional.empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/ejecutarproceso")
    public String ejecutarproceso(Model model) {
        var procesos = procesosService.listarProcesos();
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        var listaProcesos = procesosTipoService.listarPorUnidad(funcionariosService.rutUnidad());
        //var procesotipo = procesosTipoDao.findByNombre("Prueba Proceso");
        //model.addAttribute("procesotipo", procesotipo);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("procesos", procesos);
        model.addAttribute("listaProcesos", listaProcesos);

        return "ejecutarproceso";
    }

    @GetMapping(value = {"/enviarid"})
    public String tareasporproceso(@RequestParam(value = "nombre") String urlParam, Model model) {
        var nombre = urlParam;
        var procesotipo = procesosTipoDao.findByNombre(nombre);
        var idProceso = procesosTipoService.getId(nombre);
        var tareasProceso = tareasTipoDao.findByIdProcesoTipo(idProceso);
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        var funcionarios = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        model.addAttribute("procesotipo", procesotipo);
        model.addAttribute("nombre", nombre);
        model.addAttribute("idProceso", idProceso);
        model.addAttribute("tareasProceso", tareasProceso);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("funcionarios", funcionarios);

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
    
    @PostMapping("/nuevaTarea")
    public String nuevaTarea(@RequestParam(value = "r") int urlParam,
                             @RequestParam(value = "n") String n, 
                             Model model){
    //variables
    var r = urlParam;
    var funcionariosList = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
    var tareas = tareasEjecutadasDao.findByIdProcesoEjecutado(r);
    //modelo
    model.addAttribute("tareas", tareas);
    model.addAttribute("r", r);
    model.addAttribute("nombreProceso", n);
    model.addAttribute("funcionariosList", funcionariosList);
    return "nuevaTarea";
    }

    @GetMapping("/CrearNuevaTarea")
    public String CrearNuevaTarea (
                                   @RequestParam(value ="nombre") String urlParam, 
                                   @RequestParam(value = "descripcion") String descripcion,
                                   @RequestParam(value = "responsable",required=false) String responsable,
                                   @RequestParam(value = "idproceso") int idproceso,
                                   @RequestParam(value = "fechai" )String fechai,
                                   @RequestParam(value = "duracion" )int duracion,
                                   @RequestParam(value = "dependencia",required=false)String dependencia,
                                   Model model){
    //variables
    var funcionariosList = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
    
    var nombre = urlParam;
    var creaTarea = tareasEjecutadasService.crearTarea(idproceso, nombre, descripcion, duracion, fechai, 0, 0);
    model.addAttribute("creaTarea", creaTarea);
        return "nuevaTarea";
    }
    
    
    
}
