package cl.apolonia.service;

import cl.apolonia.domain.Dashboard;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DashboardService {
    
    Dashboard listarFuncionario (String run);
    Dashboard listarSubunnidad (int id);
    Dashboard listarUnidad    (String rut);
}
