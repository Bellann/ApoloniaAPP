package cl.apolonia.service;

import cl.apolonia.dao.ProcesosDao;
import cl.apolonia.domain.Procesos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("procesosService")
public class ProcesosSerivceImpl implements ProcesosSerivce {
    
    @Autowired
    private ProcesosDao procesosDao;
    
    

    @Override
    public List<Procesos> listarProcesos() {
        return (List<Procesos>)procesosDao.findAll();
    }
    
}
