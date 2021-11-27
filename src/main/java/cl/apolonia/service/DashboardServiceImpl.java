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
        
        var aceptada = 0;
        Integer desarrollo = 0;
        Integer revision = 0;
        Integer finalizada = 0;
        Integer carga = 0;
        
        for (int i = 0; i < lista.size(); i++) {
            
                switch (lista.get(0).getEstadoTarea())
                {
                    case "Aceptada":
                        aceptada ++;
                        break;
                    case "En Desarrollo":
                        desarrollo ++;
                        break;
                    case "En Revision":
                        revision ++;
                        break;
                    case "Finalizada":
                        finalizada ++;
                        break;
                    default:
                        break;
                }
                carga += lista.get(i).getDuracionTarea();
        }
        
        Dashboard dash = new Dashboard(run, lista.get(0).getNombreFuncionario(), aceptada, desarrollo, revision, finalizada, carga);
        return dash;
    }

    @Override
    public Dashboard listarSubunnidad(int id) {
        
       List<Dashboard> lista = dashboardDao.findByIdSubunidad(id);
        
        var aceptada = 0;
        Integer desarrollo = 0;
        Integer revision = 0;
        Integer finalizada = 0;
        Integer carga = 0;
        
        for (int i = 0; i < lista.size(); i++) {
            
                switch (lista.get(0).getEstadoTarea())
                {
                    case "Aceptada":
                        aceptada ++;
                        break;
                    case "En Desarrollo":
                        desarrollo ++;
                        break;
                    case "En Revision":
                        revision ++;
                        break;
                    case "Finalizada":
                        finalizada ++;
                        break;
                    default:
                        break;
                }
                carga += lista.get(i).getDuracionTarea();
        }
        Dashboard dash = new Dashboard(id, lista.get(0).getNombreSubunidad(), aceptada, desarrollo, revision, finalizada, carga);
        return dash;
    }

    @Override
    public Dashboard listarUnidad(String rut) {
        List<Dashboard> lista = dashboardDao.findByRutUnidad(rut);
        
        var aceptada = 0;
        Integer desarrollo = 0;
        Integer revision = 0;
        Integer finalizada = 0;

        
        for (int i = 0; i < lista.size(); i++) {
            
                switch (lista.get(0).getEstadoTarea())
                {
                    case "Aceptada":
                        aceptada ++;
                        break;
                    case "En Desarrollo":
                        desarrollo ++;
                        break;
                    case "En Revision":
                        revision ++;
                        break;
                    case "Finalizada":
                        finalizada ++;
                        break;
                    default:
                        break;
                }

        }
        Dashboard dash = new Dashboard(rut, lista.get(0).getNombreSubunidad(), aceptada, desarrollo, revision, finalizada);
        return dash;
    }
    
}
