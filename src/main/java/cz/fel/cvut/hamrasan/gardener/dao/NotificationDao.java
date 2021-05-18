package cz.fel.cvut.hamrasan.gardener.dao;

import cz.fel.cvut.hamrasan.gardener.model.Notification;
import cz.fel.cvut.hamrasan.gardener.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class NotificationDao extends BaseDao<Notification> {

    public NotificationDao(){super(Notification.class);}

    public List<Notification> find3Days(LocalDate date) {
        try {
            return em.createNamedQuery("Notification.find3Days", Notification.class).setParameter("date", date.minusDays(2))
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<Notification> notSeenOfUser(User user) {
        try {
            return em.createNamedQuery("Notification.notSeenOfUser", Notification.class).setParameter("user", user)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
