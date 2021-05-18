package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Garden;
import cz.fel.cvut.hamrasan.gardener.model.Soil;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class SoilDao extends BaseDao<Soil> {

    public SoilDao(){super(Soil.class);}

    public Soil findLatest(Garden garden) {

        try {
            return em.createNamedQuery("Soil.findLatest", Soil.class).setParameter("garden", garden).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Soil> findHistoryOfGarden (Garden garden) {
        try {
            return em.createNamedQuery("Soil.findLatest", Soil.class).setParameter("garden", garden).setMaxResults(6).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
