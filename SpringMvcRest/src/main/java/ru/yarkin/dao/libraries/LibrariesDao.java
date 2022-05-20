package ru.yarkin.dao.libraries;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.SessionUtil;
import ru.yarkin.models.demobase.Libraries;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
public class LibrariesDao extends SessionUtil{

    public LibrariesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<Libraries> findAllDictionary() {
        return sessionFactory.getCurrentSession().createQuery("from Libraries",Libraries.class).getResultList();
    }


    public Libraries findDictionary(Long id) {
        try {
            return currentSession().createQuery("from Libraries where id=:id", Libraries.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }
}
