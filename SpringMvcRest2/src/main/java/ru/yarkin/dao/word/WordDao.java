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

        Pair pair = new Pair();
        if (unique(key, languageSourceWord)) {
            Word sourceWord = new Word(key, languageSourceWord);
            pair.setSourceWord(sourceWord);
            create(sourceWord);
        } else {
            pair.setSourceWord(getOneByName(key));
        }

        if (unique(value, languageTargetWord)) {
            Word targetWord = new Word(value, languageTargetWord);
            pair.setTargetWord(targetWord);
            create(targetWord);
        } else{
            pair.setTargetWord(getOneByName(value));
        }

        return pair;
    }

    public Word getOneByName(String name) {
        return getCurrentSession().createQuery("from Word  where value =: name", Word.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    private boolean unique(String key, Language language) {
        return getCurrentSession().createQuery("from Word where value =: key and language =: language")
                .setParameter("key", key)
                .setParameter("language", language)
                .uniqueResult() == null;
    }
}
