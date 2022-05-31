package ru.yarkin.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ru.yarkin.models.demobase.Languages;
import ru.yarkin.models.demobase.Words;

import java.util.ArrayList;
import java.util.List;
@Repository
public class WordsDao extends SessionUtil{
    public WordsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<String> findAll() {

        List<Words> words = currentSession().createQuery("from Words", Words.class)
                .getResultList();
        List<String> result = new ArrayList<>();

        for (Words word : words) {
            result.add(word.getValue());
        }
        return result;
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
