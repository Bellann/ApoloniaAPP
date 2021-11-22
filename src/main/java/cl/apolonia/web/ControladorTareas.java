package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosDao;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorTareas {

    @Autowired
    private FuncionariosService funcionariosService;

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

    @PostMapping("/CrearNuevaTarea")
    public String CrearNuevaTarea(
            @RequestParam(value = "nombre") String urlParam,
            @RequestParam(value = "descripcion") String descripcion,
            @RequestParam(value = "responsable", required = false) String responsable,
            @RequestParam(value = "idproceso") int idproceso,
            @RequestParam(value = "fechai") String fechai,
            @RequestParam(value = "duracion") int duracion,
            @RequestParam(value = "dependencia", required = false) String dependencia,
            Model model) throws ParseException {
        //variables
        var funcionariosList = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        var nombre = urlParam;
        Date d = new SimpleDateFormat("yyyy/MM/dd").parse(fechai);
        var creaTarea = tareasEjecutadasService.crearTarea(idproceso, nombre, descripcion, duracion, d);
        
       
        
       
        return "nuevaTarea";
    }

}
