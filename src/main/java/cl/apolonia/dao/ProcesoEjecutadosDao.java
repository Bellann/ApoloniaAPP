/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cl.apolonia.dao;

import cl.apolonia.domain.ProcesoEjecutados;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author monse
 */
public interface ProcesoEjecutadosDao extends JpaRepository<ProcesoEjecutados, Integer>{
    

    
}
