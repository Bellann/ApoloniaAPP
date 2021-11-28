package cl.apolonia.service;

import cl.apolonia.domain.Archivo;
import java.util.List;


public interface ArchivoService {
    
    
    int create(String nombre, String funcionario, int idTarea);
    public List<Archivo> ListarXTarea (Integer idTarea);
}
