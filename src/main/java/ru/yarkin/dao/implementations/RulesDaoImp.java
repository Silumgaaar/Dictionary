package ru.yarkin.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.SessionUtil;
import ru.yarkin.dao.interfaces.RulesDao;
import ru.yarkin.models.demobase.Languages;
import ru.yarkin.models.demobase.Rules;

@Repository
@Transactional
public class RulesDaoImp extends SessionUtil implements RulesDao {
    public RulesDaoImp(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    @Override
    public Rules getRule(Languages languages) {
        return  currentSession().createQuery("from Rules where languages =: language", Rules.class)
                .setParameter("language",languages)
                .getSingleResult();
    }
}
