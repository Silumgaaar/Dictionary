package ru.yarkin.dao.rules;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.SessionUtil;
import ru.yarkin.models.demobase.Languages;
import ru.yarkin.models.demobase.Rules;

@Repository
@Transactional
public class RulesDao extends SessionUtil{
    public RulesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Rules getRule(Languages languages) {
        return  currentSession().createQuery("from Rules where languages =: language", Rules.class)
                .setParameter("language",languages)
                .getSingleResult();
    }
}
