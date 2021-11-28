package cl.apolonia.service;

import cl.apolonia.dao.ObservacionesDao;
import cl.apolonia.domain.Observaciones;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ObservacionesService")
public class ObservacionesServiceImpl implements ObservacionesService {
    
    @Autowired
    private ObservacionesDao observacionesDao;

    @Override
    public List<Observaciones> ListarXIdtarea(Integer idtarea) {
        return observacionesDao.findByIdtarea(idtarea);
    }
    
}
