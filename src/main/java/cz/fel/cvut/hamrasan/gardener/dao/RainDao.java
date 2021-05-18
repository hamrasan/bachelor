package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Garden;
import cz.fel.cvut.hamrasan.gardener.model.Rain;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class RainDao extends BaseDao<Rain> {

    public RainDao(){super(Rain.class);}

    public Rain findLatest(Garden garden){
        try {
            return em.createNamedQuery("Rain.findLatest", Rain.class).setParameter("garden", garden).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Rain> findHistoryOfGarden (Garden garden) {
        try {
            return em.createNamedQuery("Rain.findLatest", Rain.class).setParameter("garden", garden).setMaxResults(6).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
