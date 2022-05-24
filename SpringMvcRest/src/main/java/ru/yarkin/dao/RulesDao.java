package ru.yarkin.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.yarkin.models.demobase.Languages;
import ru.yarkin.models.demobase.Rules;

@Repository
public class RulesDao extends SessionUtil{

    @Autowired
    public RulesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Rules getRule(Languages languages) {
        return  currentSession().createQuery("from Rules where languages =: language", Rules.class)
                .setParameter("language",languages)
                .getSingleResult();
    }

}
