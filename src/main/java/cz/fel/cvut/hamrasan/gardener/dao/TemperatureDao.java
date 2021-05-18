package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Garden;
import cz.fel.cvut.hamrasan.gardener.model.Temperature;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class TemperatureDao extends BaseDao<Temperature> {
    public TemperatureDao(){super(Temperature.class);}

    public Temperature findLatest(Garden garden){
        try {
            return em.createNamedQuery("Temperature.findLatest", Temperature.class).setParameter("garden", garden).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Temperature> findHistoryOfGarden (Garden garden) {
        try {
            return em.createNamedQuery("Temperature.findLatest", Temperature.class).setParameter("garden", garden).setMaxResults(6).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
