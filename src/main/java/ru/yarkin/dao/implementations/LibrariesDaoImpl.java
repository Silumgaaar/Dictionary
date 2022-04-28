package ru.yarkin.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.SessionUtil;
import ru.yarkin.dao.interfaces.LibrariesDao;
import ru.yarkin.models.demobase.Libraries;

import java.util.List;

@Repository
@Transactional
public class LibrariesDaoImpl extends SessionUtil implements LibrariesDao {

    public LibrariesDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Libraries> findAllDictionary() {
        return sessionFactory.getCurrentSession().createQuery("from Libraries",Libraries.class).getResultList();
    }

    @Override
    public Libraries findDictionary(Long id) {
        return  currentSession().createQuery("from Libraries where id=:id", Libraries.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
