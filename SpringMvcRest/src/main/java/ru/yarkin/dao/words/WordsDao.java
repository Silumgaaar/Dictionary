package ru.yarkin.dao.words;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yarkin.dao.SessionUtil;
import ru.yarkin.models.demobase.Languages;
import ru.yarkin.models.demobase.Words;

import java.util.List;
@Repository
@Transactional
public class WordsDao extends SessionUtil{
    public WordsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<Words> findAll() {

        return currentSession().createQuery("from Words", Words.class)
                .getResultList();
    }

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
