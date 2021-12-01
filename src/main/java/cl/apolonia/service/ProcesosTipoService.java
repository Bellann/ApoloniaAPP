package cl.apolonia.service;

import cl.apolonia.domain.ProcesosTipo;
import java.util.List;
import java.util.Optional;

public interface ProcesosTipoService {

    /**
     *
     * @param rutUnidad
     * @return Lista de procesos según la unidad
     */
    public List<ProcesosTipo> listarXUnidad(String rutUnidad);
    
    public Integer getId(String nombre);
    
    public List<ProcesosTipo> listarXSubUnidad(int idSubunidad);
    

}
