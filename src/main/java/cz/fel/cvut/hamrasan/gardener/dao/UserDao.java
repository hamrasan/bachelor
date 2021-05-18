package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDao extends BaseDao<User>{
    public UserDao(){super(User.class);}

//    public User findByUsername(String username){
//        {
//            try {
//                return em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username)
//                        .getSingleResult();
//            } catch (NoResultException e) {
//                return null;
//            }
//        }
//    }

    public User findByEmail(String email) {
        try {
            return em.createNamedQuery("User.findByEmail", User.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
