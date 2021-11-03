/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cl.apolonia.service;

import cl.apolonia.domain.ProcesoEjecutados;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author monse
 */
public class ProcesoEjecutadosServiceImplTest {
    
    public ProcesoEjecutadosServiceImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of listarProcesosEjec method, of class ProcesoEjecutadosServiceImpl.
     */
    @Test
    public void testListarProcesosEjec() {
        System.out.println("listarProcesosEjec");
        ProcesoEjecutadosServiceImpl instance = new ProcesoEjecutadosServiceImpl();
        List<ProcesoEjecutados> expResult = null;
        List<ProcesoEjecutados> result = instance.listarProcesosEjec();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
