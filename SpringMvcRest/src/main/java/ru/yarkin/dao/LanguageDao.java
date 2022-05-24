package ru.yarkin.dao;

import org.hibernate.SessionFactory;
import ru.yarkin.models.demobase.Libraries;


public class LanguageDao extends SessionUtil {

    public LanguageDao(SessionFactory sessionFactory){
        super(sessionFactory);
    }
    public Libraries get(Long libraryId){
        return currentSession().createQuery("from Libraries where id =:id", Libraries.class)
                .setParameter("id", libraryId)
                .getSingleResult();
    }

}
