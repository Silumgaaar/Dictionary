package ru.yarkin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.yarkin.models.demobase.Libraries;
import ru.yarkin.models.demobase.Translate;
import ru.yarkin.models.demobase.Words;

import javax.persistence.NoResultException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TranslateDao extends SessionUtil{
    private  static final String UNIQUE_ERROR = " Уже есть в словаре";
    private static final String SUCCESSFULLY_ADDED = "Пара успешно добавлена";
    private static final String NOT_PAIR = "Пара не найдена";
    private static final String SUCCESSFUL_REMOVAL = "Удаление прошло успешно";
    private static final String NOT_DICTIONARY = "Словарь не найден";


    @Autowired
    public TranslateDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public String add(Long id, String key, String value){
        var session = currentSession();
        List<String> allWords = new WordsDao(sessionFactory).findAll();

        for (String word : allWords) {
            if (word.equals(key)) {
                return (key + UNIQUE_ERROR);
            }

            if (word.equals(value)) {
                return (value + UNIQUE_ERROR);
            }
        }
        try {

            Libraries libraries = new LanguageDao(sessionFactory).get(id);

            Words sourceWord = new Words(key, libraries.getSourceLanguageId());
            Words targetWord = new Words(value, libraries.getTargetLanguageId());

            Translate translate = new Translate(sourceWord, sourceWord.getLanguages_id(), targetWord, targetWord.getLanguages_id());

            session.save(translate);
            return SUCCESSFULLY_ADDED;
        }catch (NoResultException e){
            return NOT_DICTIONARY;
        }
    }

    public Map<String, String> findAllPair(Long id) {
        try {
            Libraries libraries = new LanguageDao(sessionFactory).get(id);

            List<Words> sourceWords = new WordsDao(sessionFactory).findAll(libraries.getSourceLanguageId());
            List<Words> targetWords = new WordsDao(sessionFactory).findAll(libraries.getTargetLanguageId());

            Map<String, String> result = new HashMap<>();
            for (int i = 0; i < sourceWords.size(); i++) {
                result.put(sourceWords.get(i).getValue(), targetWords.get(i).getValue());
            }
            return result;
        }catch (NoResultException e){
            return new HashMap<>();
        }
    }

    public List<String> search(Long id, String key){
        var session = currentSession();
        List<String> result = new ArrayList<>();
        try {
            Libraries libraries = new LanguageDao(sessionFactory).get(id);


            try {

                Words words = new WordsDao(sessionFactory).findWord(key, libraries.getSourceLanguageId());
                Translate translate = session.createQuery("from Translate where sourceWordsId.id=:id", Translate.class)
                        .setParameter("id", words.getId())
                        .getSingleResult();

                result.add(translate.getSourceWordsId().getValue());
                result.add(translate.getTargetWordsId().getValue());
            } catch (NoResultException e) {
                result.add(NOT_PAIR);
            }
        }catch (NoResultException e){
            result.add(NOT_DICTIONARY);
        }

        return result;
    }

    public List<String> delete(Long id, String key){
        Session session = currentSession();
        List<String> resultList = new ArrayList<>();
        try {
            Libraries library = new LanguageDao(sessionFactory).get(id);

            try {
                var translate = session.createQuery("from Translate where sourceWordsId.value =: word and sourceLanguageId =: language", Translate.class)
                        .setParameter("word", key)
                        .setParameter("language", library.getSourceLanguageId())
                        .getSingleResult();
                session.delete(translate);

                resultList.add(SUCCESSFUL_REMOVAL);
            } catch (NoResultException e) {
                resultList.add(NOT_PAIR);
            }
        }catch (NoResultException e){
            resultList.add(NOT_DICTIONARY);
        }

        return resultList;
    }
}
