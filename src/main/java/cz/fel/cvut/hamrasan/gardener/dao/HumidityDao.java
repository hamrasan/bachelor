package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Garden;
import cz.fel.cvut.hamrasan.gardener.model.Humidity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class HumidityDao extends BaseDao<Humidity> {
    public HumidityDao(){super(Humidity.class);}

    public Humidity findLatest(Garden garden){
        try {
            return em.createNamedQuery("Humidity.findLatest", Humidity.class).setParameter("garden", garden).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Humidity> findHistoryOfGarden (Garden garden) {
        try {
            return em.createNamedQuery("Humidity.findLatest", Humidity.class).setParameter("garden", garden).setMaxResults(6).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
