package cl.apolonia.web;

import cl.apolonia.dao.DashboardDao;
import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesosDao;
import cl.apolonia.dao.ProcesosTipoDao;
import cl.apolonia.dao.TareasEjecutadasDao;
import cl.apolonia.dao.TareasTipoDao;
import cl.apolonia.domain.Dashboard;
import cl.apolonia.service.DashboardService;
import cl.apolonia.service.FuncionariosService;
import cl.apolonia.service.ProcesoEjecutadosService;
import cl.apolonia.service.ProcesosSerivce;
import cl.apolonia.service.ProcesosTipoService;
import cl.apolonia.service.TareasEjecutadasServices;
import cl.apolonia.service.procParticipoService;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladosPrueba {
    
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
    private DashboardDao dashboardDao;
    
    @Autowired
    private DashboardService dashboardService;
    
    @GetMapping("/prueba")
    public String inicio(Model model) {

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

        
        
        return "prueba";
    }
    
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    
    @GetMapping("/pruebaDashboard")
    public String dashBoard(Model model) {

        model.addAttribute("chartData", getChartData()); 
        return "pruebaDashboard";
    }
    
    private List<List<Object>> getChartData() {
        
        Dashboard dash = dashboardService.listarFuncionario("88444005k");
        return List.of(
                List.of("Programadas", dash.getProgramada())
                ,List.of("Aceptadas", dash.getAceptada())
                ,List.of("Desarrollo", dash.getDesarrollo())
                ,List.of("Revision", dash.getRevision())
                ,List.of("Rechazada", dash.getRechazada())
                ,List.of("Finalizada", dash.getFinalizada())
                
        );
    }
}
