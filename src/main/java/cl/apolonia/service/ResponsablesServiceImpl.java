/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.apolonia.service;

import cl.apolonia.dao.ResponsablesDao;
import cl.apolonia.domain.Responsables;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ResponsablesService")
public class ResponsablesServiceImpl implements ResponsablesService {

    @Autowired
    public ResponsablesDao responsablesDao;

    @Override
    public List<Responsables> listarXIdtarea(Integer idtarea) {
        return responsablesDao.findByIdtarea(idtarea);
    }
    
  
        
    
}
