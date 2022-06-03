package ru.yarkin.dao.word;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ru.yarkin.dao.SessionUtil;
import ru.yarkin.models.database.Language;
import ru.yarkin.models.database.Word;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WordDao extends SessionUtil {
    public WordDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Word> addWords(Long sourceLanguage, Long targetLanguage, String key, String value) {
        Session session = currentSession();
        List<Word> pair = new ArrayList<>();

        try {
            Word keyWord = currentSession().createQuery("from Word where value =: newWord", Word.class)
                    .setParameter("newWord", key)
                    .getSingleResult();
            pair.add(keyWord);
        } catch (NoResultException e) {
            Word sourceWord = new Word(key, currentSession().createQuery("from Language where id =: sourceLanguageId", Language.class)
                    .setParameter("sourceLanguageId", sourceLanguage)
                    .getSingleResult());
            pair.add(sourceWord);
            session.save(sourceWord);
        }
        try {
            Word valueWord = currentSession().createQuery("from Word where value =: newWord", Word.class)
                    .setParameter("newWord", value)
                    .getSingleResult();
            pair.add(valueWord);
        } catch (NoResultException e) {
            Word targetWord = new Word(value, currentSession().createQuery("from Language where id =: targetLanguageId", Language.class)
                    .setParameter("targetLanguageId", targetLanguage)
                    .getSingleResult());
            pair.add(targetWord);
            session.save(targetWord);
        }
        return pair;
    }
}
