package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.ValveSchedule;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ValveScheduleDao extends BaseDao<ValveSchedule> {

    public ValveScheduleDao(){super(ValveSchedule.class);}

    public List<ValveSchedule> findByValve (Long valveId) {
        try {
            return em.createNamedQuery("ValveSchedule.findByValve", ValveSchedule.class).setParameter("valveId", valveId).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
