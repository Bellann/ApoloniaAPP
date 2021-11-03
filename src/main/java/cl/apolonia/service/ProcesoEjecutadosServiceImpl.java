package cl.apolonia.service;

import cl.apolonia.dao.ProcesoEjecutadosDao;
import cl.apolonia.domain.ProcesoEjecutados;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("procesoEjecutadosService")
public class ProcesoEjecutadosServiceImpl implements ProcesoEjecutadosService {

    @Autowired
    private ProcesoEjecutadosDao procesoEjecutadosDao;

    @Override
    public List<ProcesoEjecutados> listarProcesosEjec() {
        return (List<ProcesoEjecutados>) procesoEjecutadosDao.findAll();
    }

    

}
