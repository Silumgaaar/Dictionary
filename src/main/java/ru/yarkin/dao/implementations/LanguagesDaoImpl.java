package ru.yarkin.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yarkin.dao.SessionUtil;
import ru.yarkin.dao.interfaces.LanguagesDao;

@Repository
@Transactional
public class LanguagesDaoImpl extends SessionUtil implements LanguagesDao {

    public LanguagesDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
