package ru.yarkin.dao.securitydao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.yarkin.dao.AbstractHibernateDao;
import ru.yarkin.models.database.User;



@Repository
public class UserDao extends AbstractHibernateDao<User> {

    public UserDao() {
        setClazz(User.class);
    }

    public User findByName(String name) {
        Query<User> q = getCurrentSession().createQuery("from User where name =: name", User.class);
                q.setParameter("name", name);
        return q.list().stream().findAny().orElse(null);
    }
}