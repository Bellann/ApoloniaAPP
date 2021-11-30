package cl.apolonia.service;

import cl.apolonia.dao.DashboardDao;
import cl.apolonia.domain.Dashboard;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DashboardService")
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardDao dashboardDao;
    
    @Override
    public Dashboard listarFuncionario(String run) {
        
        List<Dashboard> lista = dashboardDao.findByRunFuncionario(run);
        
        var programada = 0;
        var aceptada = 0;
        var desarrollo = 0;
        var revision = 0;
        var finalizada = 0;
        var rechazada = 0;
        var total = 0;
        var carga = 0;
        
        for (int i = 0; i < lista.size(); i++) {
            
                switch (lista.get(i).getIdEstadoTarea())
                {   case 1:
                        programada ++;
                        break;
                    case 2:
                        aceptada ++;
                        break;
                    case 3:
                        desarrollo ++;
                        break;
                    case 4:
                        revision ++;
                        break;
                    case 5:
                        rechazada ++;
                        break;
                    case 6:
                        finalizada ++;
                        break;
                    default:
                        break;
                }
                total ++;
                carga += lista.get(i).getDuracionTarea();
        }
        
        Dashboard dash = new Dashboard(run, lista.get(0).getNombreFuncionario(), programada, aceptada, desarrollo, revision, finalizada, rechazada, total,carga);
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
        
        for (int i = 0; i < lista.size(); i++) {
            
                switch (lista.get(i).getIdEstadoTarea())
                {   case 1:
                        programada ++;
                        break;
                    case 2:
                        aceptada ++;
                        break;
                    case 3:
                        desarrollo ++;
                        break;
                    case 4:
                        revision ++;
                        break;
                    case 5:
                        rechazada ++;
                        break;
                    case 6:
                        finalizada ++;
                        break;
                    default:
                        break;
                }
                total ++;
                carga += lista.get(i).getDuracionTarea();
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

        
        for (int i = 0; i < lista.size(); i++) {
            
                switch (lista.get(i).getIdEstadoTarea())
                {   case 1:
                        programada ++;
                        break;
                    case 2:
                        aceptada ++;
                        break;
                    case 3:
                        desarrollo ++;
                        break;
                    case 4:
                        revision ++;
                        break;
                    case 5:
                        rechazada ++;
                        break;
                    case 6:
                        finalizada ++;
                        break;
                    default:
                        break;
                }
                total ++;
                carga += lista.get(i).getDuracionTarea();
        }
        Dashboard dash = new Dashboard(rut, lista.get(0).getNombreUnidad(),programada, aceptada, desarrollo, revision, rechazada, total, finalizada, carga);
        return dash;
    }

    @Override
    public List<Dashboard> encontrarXSubUnidad(Integer id) {
        return dashboardDao.findByIdSubunidad(id);
    }
    
}
