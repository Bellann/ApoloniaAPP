package cl.apolonia.service;

import cl.apolonia.dao.FuncionariosDao;
import cl.apolonia.dao.ProcesoEjecutadosDao;
import cl.apolonia.domain.ProcesoEjecutados;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service("procesoEjecutadosService")
public class ProcesoEjecutadosServiceImpl implements ProcesoEjecutadosService {

    @Autowired
    private ProcesoEjecutadosDao procesoEjecutadosDao;
    
    @Autowired
    private FuncionariosDao funcionariosDao;

    @Override
    public List<ProcesoEjecutados> listarProcesosEjec() {
        return (List<ProcesoEjecutados>) procesoEjecutadosDao.findAll();
    }

    @Override
    public List<ProcesoEjecutados> findByRut_unidad(String rut_unidad) {
       return null;
    }




    

}
