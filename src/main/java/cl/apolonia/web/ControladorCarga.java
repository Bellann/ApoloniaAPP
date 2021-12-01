package cl.apolonia.web;

import cl.apolonia.domain.Dashboard;
import cl.apolonia.service.DashboardService;
import cl.apolonia.service.FuncionariosService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCarga {

    @Autowired
    private FuncionariosService funcionariosService;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(value = "/micarga")
    public String micarga(Model model) {

        //Para el saludo
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);

        //grafico de barra
        Dashboard dash = dashboardService.listarFuncionario(funcionariosService.runResponsable());
        var miCarga = dash.getCarga();
        Dashboard depto = dashboardService.listarSubunnidad(funcionariosService.idSubunidad());
        var cargaDepto = depto.getCarga();

        //grafico de barras
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Mi carga", miCarga);
        graphData.put("Departamento", cargaDepto);;
        model.addAttribute("chartData", graphData);

        //Grafico cirsular
        model.addAttribute("chartData2", graficoPieMiCarga());
        model.addAttribute("dash", dash);

        return "micarga";
    }

    private List<List<Object>> graficoPieMiCarga() {

        Dashboard dash = dashboardService.listarFuncionario(funcionariosService.runResponsable());
        return List.of(
                List.of("Programadas", dash.getProgramada()),
                List.of("Aceptadas", dash.getAceptada()),
                List.of("Desarrollo", dash.getDesarrollo()),
                List.of("Revision", dash.getRevision()),
                List.of("Rechazada", dash.getRechazada()),
                List.of("Finalizada", dash.getFinalizada())
        );

    }

    @GetMapping(value = "/cargadepartamento")
    public String cargadepartamento(Model model) {
        Dashboard depto = dashboardService.listarSubunnidad(funcionariosService.idSubunidad());
        //Para el saludo
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);

        //datos para renderizar
        model.addAttribute("depto", depto);

        model.addAttribute("chartData2", graficoPieDepto(dashboardService.listarSubunnidad(funcionariosService.idSubunidad())));
        model.addAttribute("chartData",graficoBarDepto(dashboardService.encontrarXSubUnidad(funcionariosService.idSubunidad())));


        return "cargadepartamento";
    }

    private List<List<Object>> graficoPieDepto(Dashboard dash) {

        return List.of(
                List.of("Programadas", dash.getProgramada()),
                List.of("Aceptadas", dash.getAceptada()),
                List.of("Desarrollo", dash.getDesarrollo()),
                List.of("Revision", dash.getRevision()),
                List.of("Rechazada", dash.getRechazada()),
                List.of("Finalizada", dash.getFinalizada())
        );
    }

    private Map<String, Integer> graficoBarDepto(List<Dashboard> lista) {
        Map<String, Integer> mapDeCargas = new HashMap<String, Integer>();

        for (Dashboard dashboard : lista) {
            mapDeCargas.put(dashboard.getNombreFuncionario(), dashboard.getCarga());
        }
        return mapDeCargas;
    }

    @GetMapping(value = "/cargaempresa")
    public String cargaempresa(Model model) {
        Dashboard empresa = dashboardService.listarUnidad(funcionariosService.rutUnidad());
        //Para el saludo
        var nombreCompleto = funcionariosService.nombreCompleto();
        var rolSaludo = funcionariosService.rolSaludo();
        model.addAttribute("nusuario", nombreCompleto);
        model.addAttribute("rolsaludo", rolSaludo);

        //datos para renderizar
        model.addAttribute("empresa", empresa);
        model.addAttribute("chartData2", graficoPieEmp());
        model.addAttribute("chartData", graficoBarEmp());

        return "cargaempresa";
    }

    private List<List<Object>> graficoPieEmp() {

        Dashboard dash = dashboardService.listarUnidad(funcionariosService.rutUnidad());
        return List.of(
                List.of("Programadas", dash.getProgramada()),
                List.of("Aceptadas", dash.getAceptada()),
                List.of("Desarrollo", dash.getDesarrollo()),
                List.of("Revision", dash.getRevision()),
                List.of("Rechazada", dash.getRechazada()),
                List.of("Finalizada", dash.getFinalizada())
        );
    }

    private Map<String, Integer> graficoBarEmp() {
        Map<String, Integer> mapDeCargas = new HashMap<String, Integer>();

        List<Dashboard> lista = dashboardService.encontrarXUnidad(funcionariosService.rutUnidad());
        for (Dashboard dashboard : lista) {
            mapDeCargas.put(dashboard.getNombreSubunidad(), dashboard.getCarga());
        }
        return mapDeCargas;
    }
    
    private Map<String, Integer> graficoBarEmp(List<Dashboard> lista) {
        Map<String, Integer> mapDeCargas = new HashMap<String, Integer>();
           
        for (Dashboard dashboard : lista) {
            mapDeCargas.put(dashboard.getNombreSubunidad(),dashboard.getCarga());                 
        }
        return mapDeCargas;
    }

}
