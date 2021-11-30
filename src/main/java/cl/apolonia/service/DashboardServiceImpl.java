package cl.apolonia.service;

import cl.apolonia.dao.DashboardDao;
import cl.apolonia.domain.Dashboard;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DashboardService")
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardDao dashboardDao;
    
    @Override
    public Dashboard listarFuncionario(String run) {
        
        Dashboard dash = dashboardDao.findById(run).orElse(null);
        
        return dash;
    }

    @Override
    public Dashboard listarSubunnidad(int id) {
        
       List<Dashboard> lista = dashboardDao.findByIdSubunidad(id);
        
        var programada = 0;
        var aceptada = 0;
        var desarrollo = 0;
        var revision = 0;
        var finalizada = 0;
        var rechazada = 0;
        var total = 0;
        var carga = 0;
        
        for(Dashboard dash : lista)
        {
            programada += dash.getProgramada();
            aceptada += dash.getAceptada();
            desarrollo += dash.getDesarrollo();
            revision += dash.getRevision();
            finalizada += dash.getFinalizada();
            rechazada += dash.getRechazada();
            total += dash.getTotal();
            carga += dash.getCarga();
        }
        
        Dashboard dash = new Dashboard(id, lista.get(0).getNombreSubunidad(),programada, aceptada, desarrollo, revision, finalizada,rechazada,total, carga);
        return dash;
    }

    @Override
    public Dashboard listarUnidad(String rut) {
        List<Dashboard> lista = dashboardDao.findByRutUnidad(rut);
        
        var programada = 0;
        var aceptada = 0;
        var desarrollo = 0;
        var revision = 0;
        var finalizada = 0;
        var rechazada = 0;
        var total = 0;
        var carga = 0;
        
        for(Dashboard dash : lista)
        {
            programada += dash.getProgramada();
            aceptada += dash.getAceptada();
            desarrollo += dash.getDesarrollo();
            revision += dash.getRevision();
            finalizada += dash.getFinalizada();
            rechazada += dash.getRechazada();
            total += dash.getTotal();
            carga += dash.getCarga();
        }
        
        Dashboard dash = new Dashboard(rut, lista.get(0).getNombreUnidad(),programada, aceptada, desarrollo, revision, finalizada,rechazada,total, carga);
        
        
        return dash;
    }

    @Override
    public List<Dashboard> encontrarXSubUnidad(Integer id) {
     
        return dashboardDao.findByIdSubunidad(id);
    }

    @Override
    public List<Dashboard> encontrarXUnidad(String rut) {
       return dashboardDao.findByRutUnidad(rut);
    }
    
}
