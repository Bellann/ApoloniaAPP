package cl.apolonia.service;

import cl.apolonia.dao.procParticipoDao;
import cl.apolonia.domain.procParticipo;
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
    public List<Object[]> procParticipo(String runIN) {
                StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("r_proc_by_run")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, void.class, ParameterMode.REF_CURSOR);
        query.setParameter(1, runIN);


        query.execute();

        List<Object[]> result = query.getResultList();
        return result;
    }

}
