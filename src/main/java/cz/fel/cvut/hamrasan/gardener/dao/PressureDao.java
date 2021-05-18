package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Garden;
import cz.fel.cvut.hamrasan.gardener.model.Pressure;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PressureDao extends BaseDao<Pressure> {
    public PressureDao(){super(Pressure.class);}

    public Pressure findLatest(Garden garden){
        try {
            return em.createNamedQuery("Pressure.findLatest", Pressure.class).setParameter("garden", garden).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Pressure> findHistoryOfGarden (Garden garden) {
        try {
            return em.createNamedQuery("Pressure.findLatest", Pressure.class).setParameter("garden", garden).setMaxResults(6).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
