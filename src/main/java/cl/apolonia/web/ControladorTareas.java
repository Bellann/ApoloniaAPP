package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosTipoDao;
import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.dao.TareasTipoDao;
import cl.apolonia.domain.TareasEjecutadas;
import cl.apolonia.service.FuncionariosService;
import cl.apolonia.service.ProcesosTipoService;
import cl.apolonia.service.TareasEjecutadasServices;
import cl.apolonia.service.procParticipoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorTareas {

    @Autowired
    private FuncionariosService funcionariosService;

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

    //desde mi flujo de trabajo
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

    //Desde gestionar tarea
    @PostMapping("/AceptarTarea")
    public ModelAndView AceptarTarea(@RequestParam(value = "id") Integer urlParam,
            TareasEjecutadas tareaEjecutada,
            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para el historico
        var runUser = funcionariosService.runResponsable();
        
        //Llamar método para cambio de estado, despues de conversar
        if(tareasEjecutadasService.cambiarEstado(tarea,2)){
            return new ModelAndView("redirect:/flujotrabajo");
        }
        else
        {
            return new ModelAndView("/gestionarTarea");
        }

    }

    //Desde gestionar tarea
    @PostMapping("/iniciarTarea")
    public ModelAndView IniciarTarea(@RequestParam(value = "id") Integer urlParam,
            TareasEjecutadas tareaEjecutada,
            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para el historico
        var runUser = funcionariosService.runResponsable();

        //Llamar método para cambio de estado, despues de conversar 
        if(tareasEjecutadasService.cambiarEstado(tarea,3)){
            return new ModelAndView("redirect:/flujotrabajo");
        }
        else
        {
            return new ModelAndView("/gestionarTarea");
        }
    }

    //Desde gestionar tarea CREAR TAREA EJECUTADA + CREAR TAREA EJECUTADA SUBORDINADA
      @GetMapping("/subordinar")
    public ModelAndView subordinar(@RequestParam(value = "idtarea") Integer urlParam,
                             @RequestParam(value = "nombreSub") String nombreSub,
                             @RequestParam(value = "descripcionSub") String descripcionSub,
                             @RequestParam(value = "responsableSub") List<String> responsableSub,
                             @RequestParam(value = "idproceso") Integer idproceso,
                             @RequestParam(value = "duracionSub") int duracion,
                             TareasEjecutadas tareaEjecutada,
                             Model model) {
        var runUser = funcionariosService.runResponsable();

        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        TareasEjecutadas subordinada = new TareasEjecutadas(nombreSub, null, idproceso, descripcionSub, runUser);
        if(tareasEjecutadasService.crearTarea(subordinada, duracion, responsableSub, null, tarea.getIdtarea()))
        {
            return "prueba";
        }
        return "prueba";
    }
    
        //Desde gestionar tarea
    @PostMapping("/reportarProblema")
    public ModelAndView reportarProblema(@RequestParam(value = "id") Integer urlParam,
                                         @RequestParam(value = "descripcion") String descripcion,
                                            TareasEjecutadas tareaEjecutada,
                                            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para registrar el problema
        var runUser = funcionariosService.runResponsable();

        //Llamar método para registrar problema

        return new ModelAndView("redirect:/flujotrabajo");
    }

            //Desde gestionar tarea
    @PostMapping("/terminarTarea")
    public ModelAndView terminarTarea(@RequestParam(value = "id") Integer urlParam,
                                            TareasEjecutadas tareaEjecutada,
                                            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para registrar el problema
        var runUser = funcionariosService.runResponsable();

        //Llamar método para registrar problema

        return new ModelAndView("redirect:/flujotrabajo");
    }
    
      @PostMapping("/rechazarTarea")
    public ModelAndView rechazarTarea(@RequestParam(value = "id") Integer urlParam,
                                         @RequestParam(value = "descRechazo") String descRechazo,
                                            TareasEjecutadas tareaEjecutada,
                                            Model model) {
        TareasEjecutadas tarea = tareasEjecutadasService.encontrarTarea(urlParam);
        //run usuario logeado, para registrar el problema
        var runUser = funcionariosService.runResponsable();

        //Llamar método para registrar problema

        return new ModelAndView("redirect:/flujotrabajo");
    }
    

    //Nueva tarea a partir de un proceso seleccionado
    @PostMapping("/nuevaTarea")
    public String nuevaTarea(@RequestParam(value = "r") int urlParam,
            @RequestParam(value = "n") String n,
            Model model) {
        //variables

        var r = urlParam;
        var funcionariosList = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        var tareas = tareasEjecutadasDao.findByIdProcesoEjecutado(r);
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        //modelo
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("tareas", tareas);
        model.addAttribute("r", r);
        model.addAttribute("nombreProceso", n);
        model.addAttribute("funcionariosList", funcionariosList);
        return "nuevaTarea";
    }

    //Nueva tarea desde un proceso seleccionado
    @PostMapping("/CrearNuevaTarea")
    public ModelAndView CrearNuevaTarea(
            @RequestParam(value = "nombre") String urlParam,
            @RequestParam(value = "descripcion") String descripcion,
            @RequestParam(value = "responsable", required = false) List<String> responsable,
            @RequestParam(value = "idproceso") int idproceso,
            @RequestParam(value = "fechai") String fechai,
            @RequestParam(value = "duracion") int duracion,
            @RequestParam(value = "dependencia", required = false) List<String> dependencia,
            Model model) throws ParseException {
        //variables
        var nombre = urlParam;
        String runUser = funcionariosService.runResponsable();
        Date d = new SimpleDateFormat("yyyy/MM/dd").parse(fechai);
        TareasEjecutadas tarea = new TareasEjecutadas(nombre, d, idproceso, descripcion, runUser);
        if (tareasEjecutadasService.crearTarea(tarea, duracion, responsable, dependencia)) {
            return new ModelAndView("redirect:/flujotrabajo");
        } else {
            return new ModelAndView("redirect:/nuevaTarea");
        }
    }

}
