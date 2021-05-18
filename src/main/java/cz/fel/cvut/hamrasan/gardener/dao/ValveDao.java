package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Valve;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class ValveDao extends BaseDao<Valve> {

    public ValveDao(){super(Valve.class);}

    public Valve findByName(String name) {
        try {
            return em.createNamedQuery("Valve.findByName", Valve.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
