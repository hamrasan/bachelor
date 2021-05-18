package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.PlantCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class PlantCategoryDao extends BaseDao<PlantCategory> {
    public PlantCategoryDao(){super(PlantCategory.class);}

    public PlantCategory findByName(String name) {
        try {
            return em.createNamedQuery("PlantCategory.findByName", PlantCategory.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
