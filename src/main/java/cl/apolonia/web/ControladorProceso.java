package cl.apolonia.web;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosDao;
import cl.apolonia.dao.ProcesosTipoDao;
import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.dao.TareasTipoDao;
import cl.apolonia.domain.ProcesoEjecutados;
import cl.apolonia.domain.TareasEjecutadas;
import cl.apolonia.domain.TareasTipo;
import cl.apolonia.service.FuncionariosService;
import cl.apolonia.service.ProcesoEjecutadosService;
import cl.apolonia.service.ProcesosSerivce;
import cl.apolonia.service.ProcesosTipoService;
import cl.apolonia.service.TareasEjecutadasServices;
import cl.apolonia.service.procParticipoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public String tareasporproceso(@RequestParam(value = "nombre") String urlParam, Model model, TareasEjecutadas tarea) {
        var nombre = urlParam;
        var procesotipo = procesosTipoDao.findByNombre(nombre);
        var idProceso = procesosTipoService.getId(nombre);
        var tareasProceso = tareasTipoDao.findByIdProcesoTipo(idProceso);
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        var funcionarios = funcionariosDao.findByIdSubunidad(funcionariosService.idSubunidad());
        var noimplementado = "no implementado";

        model.addAttribute("procesotipo", procesotipo);
        model.addAttribute("nombre", nombre);
        model.addAttribute("idProceso", idProceso);
        model.addAttribute("tareasProceso", tareasProceso);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("noimplementado", noimplementado);

        return "ejecutarproceso";
    }

    @GetMapping(value = {"/ejecuta"})
    public ModelAndView ejecutar(@RequestParam(value = "idSubunidad") Integer urlParam,
            @RequestParam(value = "nombreProceso") String nombreProceso,
            @RequestParam(value = "descripcionProceso") String descipcionroceso,
            @RequestParam(value = "fechaInicioProceso") String fechaInicioProceso,
            @RequestParam(value = "runDisenador") String runDisenador, //hasta aqui son parametros para el proceso ejecutado
            @RequestParam(value = "nombreTarea") List<String> nombreTarea, //desde aqui parametros para tarea ejecutada
            @RequestParam(value = "descripcionTarea") List<String> descripcionTarea,
            @RequestParam(value = "duracionTarea") List<Integer> duracionTarea,
            @RequestParam(value = "fechaInicioTarea") List<String> fechaInicioTarea,
            @RequestParam(value = "responsableTarea") List<String> responsableTarea,
            Model model,
            TareasEjecutadas tarea) throws ParseException {

        //RUN DEL USUARIO CONECTADO, para proceso ejecutado y para tarea ejecutada
        var runUser = funcionariosService.runResponsable();
        
        ProcesoEjecutados proc = new ProcesoEjecutados(nombreProceso, descipcionroceso,runUser,(int)urlParam);
        int duracion = duracionTarea.stream().collect(Collectors.summingInt(Integer::intValue));
        
        if(procesoEjecutadosService.crearProceso(proc, fechaInicioProceso, runDisenador, duracion))
        {
            int id = 0;
            for(int i = 0; i< nombreTarea.size(); i++)
            {
                System.out.println(fechaInicioTarea.get(i));
                Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicioTarea.get(i));
                TareasEjecutadas t = new TareasEjecutadas(nombreTarea.get(i), fecha, proc.getId_proceso_ejecutado(),descripcionTarea.get(i),runUser);
                tareasEjecutadasService.crearTarea(t, duracionTarea.get(i), responsableTarea.get(i));
                id = t.getIdtarea();
                if(i == 0)
                    tareasEjecutadasService.crearDependencia(id, t.getIdtarea().toString());
            }
        }
        
        //crear Proceso ejecutado
        //si lo crea, crear tareas ejecutadas
        //Colocar ID del proceso, resultante del procedimiento esto deberia ser cuando es correcto
        var idProcesoEjecutado = 12;

        return new ModelAndView("redirect:/ejecuta2?idProcesoEjecutado=" + proc.getId_proceso_ejecutado());
    }

    //Ventana para administrar las tareas antes de confirmar
    @GetMapping(value = {"/ejecuta2"})
    public ModelAndView ejecutar(@RequestParam(value = "idProcesoEjecutado") Integer urlParam,
            Model model) {
        //Variables para saludo superior
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();

        //proceso que emerge en la venta + lista de sus tareas
        var proceso = procesoEjecutadosService.encontrarproceso(urlParam);
        var tarea = tareasEjecutadasService.listarXProceso(urlParam);
        
        model.addAttribute("proceso", proceso);
        model.addAttribute("tarea", tarea);
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);

        return new ModelAndView("ejecutarproceso2");
    }

}
