package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Subcategory;
import org.springframework.stereotype.Repository;

@Repository
public class SubcategoryDao extends BaseDao<Subcategory> {
    public SubcategoryDao(){super(Subcategory.class);}
}
