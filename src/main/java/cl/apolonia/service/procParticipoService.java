package cl.apolonia.service;

import cl.apolonia.domain.procParticipo;
import java.util.List;

public interface procParticipoService {
    
    List<procParticipo> procesoParticipo(String runIN);
}
