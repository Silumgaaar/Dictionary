package ru.yarkin.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yarkin.dao.SessionUtil;
import ru.yarkin.dao.interfaces.WordsDao;
import ru.yarkin.models.demobase.Languages;
import ru.yarkin.models.demobase.Words;

import java.util.List;
@Repository
@Transactional
public class WordsDaoImpl extends SessionUtil implements WordsDao {
    public WordsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Words> findAll() {

        return currentSession().createQuery("from Words", Words.class)
                .getResultList();
    }
    @Override
    public List<Words> findAll(Languages languages_id){
        return currentSession().createQuery("from Words where languages_id =: languages", Words.class)
                .setParameter("languages", languages_id)
                .getResultList();
    }
    public Words findWord(String key, Languages languages){

        return currentSession().createQuery("from Words  where value=:word and languages_id =: language", Words.class)
                .setParameter("word", key)
                .setParameter("language",languages)
                .getSingleResult();
    }
}
