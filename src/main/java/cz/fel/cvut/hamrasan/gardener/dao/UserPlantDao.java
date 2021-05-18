package cz.fel.cvut.hamrasan.gardener.dao;

import com.sun.mail.imap.protocol.UIDSet;
import cz.fel.cvut.hamrasan.gardener.model.UserPlant;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserPlantDao extends BaseDao<UserPlant> {

    public UserPlantDao() {super(UserPlant.class);}

    public UserPlant findByName(String name) {
        try {
            return em.createNamedQuery("UserPlant.findByName", UserPlant.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
