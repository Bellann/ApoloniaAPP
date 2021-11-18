package cl.apolonia.service;

import cl.apolonia.dao.procParticipoDao;
import cl.apolonia.domain.procParticipo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("procParticipoService")
public class procParticipoServiceImp implements procParticipoService {

    @Autowired
    procParticipoDao procParticipoDao;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<procParticipo> procParticipo(String runIN) {
                StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("r_proc_by_run")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, void.class, ParameterMode.REF_CURSOR);
        query.setParameter(1, runIN);


        query.execute();

        List<Object[]> result = query.getResultList();
        List<procParticipo> listaNegocio = new ArrayList<procParticipo>();
        result.stream().forEach((r)->{
        procParticipo p = new procParticipo();
        BigDecimal b = (BigDecimal)r[0];
        p.setIdProceso(b.intValue());
        p.setNombreProceso((String)r[1]);
        p.setFechaPrevistaFin((Date)r[2]);
        p.setAsignadoPor((String)r[3]);
        p.setEstado((String)r[4]);
        listaNegocio.add(p);
        });
        return listaNegocio;
    }
}
