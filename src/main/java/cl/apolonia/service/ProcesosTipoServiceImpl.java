/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.apolonia.service;

import cl.apolonia.dao.ProcesosTipoDao;
import cl.apolonia.domain.ProcesosTipo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author monse
 */
@Service("ProcesosTipoService")
public class ProcesosTipoServiceImpl implements ProcesosTipoService {
    
    @Autowired
    private ProcesosTipoDao procesosTipoDao;

    @Override
    public List<ProcesosTipo> listarXUnidad(String rutUnidad) {
        return procesosTipoDao.findByRutUnidad(rutUnidad);
    }

    @Override
    public Integer getId(String nombre) {
        ProcesosTipo procesoEncontrado = new ProcesosTipo();
        procesoEncontrado = procesosTipoDao.findByNombre(nombre);
        
        int idProceso = procesoEncontrado.getId();
        return idProceso;
        
    }


    @Override
    public List<ProcesosTipo> listarXSubUnidad(int idSubunidad) {
        return procesosTipoDao.findByIdSubunidad(idSubunidad);
    }














    
}
