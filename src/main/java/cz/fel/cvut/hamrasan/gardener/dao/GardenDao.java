package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Garden;
import cz.fel.cvut.hamrasan.gardener.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class GardenDao extends BaseDao<Garden> {

    public GardenDao(){super(Garden.class);}

    public Garden findByName(String name, User user) {
        try {
            return em.createNamedQuery("Garden.findByName", Garden.class).setParameter("name", name).setParameter("user", user)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
