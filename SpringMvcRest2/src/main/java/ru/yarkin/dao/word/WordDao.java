package ru.yarkin.dao.word;


import org.springframework.stereotype.Repository;

import ru.yarkin.dao.AbstractHibernateDao;
import ru.yarkin.dictionary.Pair;
import ru.yarkin.models.database.Language;
import ru.yarkin.models.database.Word;


@Repository
public class WordDao extends AbstractHibernateDao<Word> {

    public WordDao() {
        setClazz(Word.class);
    }

    public Pair addPairWords(Long sourceLanguage, Long targetLanguage, String key, String value) {
        Language languageSourceWord = getCurrentSession().createQuery("from Language where id=: sourceLanguageId", Language.class)
                .setParameter("sourceLanguageId", sourceLanguage)
                .getSingleResult();
        Language languageTargetWord = getCurrentSession().createQuery("from Language where id=: targetLanguageId", Language.class)
                .setParameter("targetLanguageId", targetLanguage)
                .getSingleResult();

        Word sourceWord = new Word(key, languageSourceWord);
        Word targetWord = new Word(value, languageTargetWord);

        create(sourceWord);
        create(targetWord);

        return new Pair(sourceWord, targetWord);
    }
}
