package ru.yarkin.dao.dictionarydao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.yarkin.dao.AbstractHibernateDao;
import ru.yarkin.models.database.Language;
import ru.yarkin.models.database.Word;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class WordDao extends AbstractHibernateDao<Word> {
    @Autowired
    public WordDao() {
        setClazz(Word.class);
    }


    public Map<String, Long> getAllValueAndLanguageWord() {
        List<Word> words = findAll();
        Map<String, Long> valueAndLanguageWords = new HashMap<>();

        for (Word word : words) {
            valueAndLanguageWords.put(word.getValue(), word.getLanguage().getId());
        }

        return valueAndLanguageWords;
    }

    public Word createWord(String value, Long idLanguage) {
        Language language = getCurrentSession().createQuery("from Language where id =: idLanguage", Language.class)
                .setParameter("idLanguage", idLanguage)
                .getSingleResult();

        return new Word(value, language);

    }

    public Word findWordByValueAndIdLanguage(String value, Long idLanguage) {
        return getCurrentSession().createQuery("from Word where value =: value and language.id =: idLanguage", Word.class)
                .setParameter("value", value)
                .setParameter("idLanguage", idLanguage)
                .getSingleResult();
    }
}
