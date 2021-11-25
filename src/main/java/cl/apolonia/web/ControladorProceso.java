package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosDao;
import cl.apolonia.dao.ProcesosTipoDao;
import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.dao.TareasTipoDao;
import cl.apolonia.domain.TareasTipo;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorProceso {
           
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
    public String tareasporproceso(@RequestParam(value = "nombre") String urlParam, Model model, TareasTipo tareaTipo) {
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

     @GetMapping(value = {"/responsableTarea"})
    public String responsableTarea(TareasTipo tareasTipo) {
        
        
        return "ejecutarproceso";
    }

    
}
