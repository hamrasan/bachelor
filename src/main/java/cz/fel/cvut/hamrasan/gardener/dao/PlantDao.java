package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Plant;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class PlantDao extends BaseDao<Plant>{
    public PlantDao(){super(Plant.class);}

    public Plant findByName(String name) {
        try {
            return em.createNamedQuery("Plant.findByName", Plant.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
