package cl.apolonia.service;

import cl.apolonia.domain.procParticipo;
import java.util.List;

public interface procParticipoService {
    
    List<Object[]> procParticipo(String runIN);
}
