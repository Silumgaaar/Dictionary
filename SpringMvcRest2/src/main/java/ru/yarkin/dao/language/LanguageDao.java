package ru.yarkin.dao.language;

import org.springframework.stereotype.Repository;

import ru.yarkin.dao.AbstractHibernateDao;
import ru.yarkin.dictionary.Dictionary;
import ru.yarkin.models.database.Language;

import java.util.ArrayList;
import java.util.List;


@Repository
public class LanguageDao extends AbstractHibernateDao<Language> {

    public LanguageDao() {
        setClazz(Language.class);
    }

    public List<String> findRulesDictionary(Long sourceLanguageId, Long targetLanguageId) {
        List<Language> languages = getCurrentSession().createQuery("from Language where id =: sourceLanguage or id =: targetLanguage", Language.class)
                .setParameter("sourceLanguage", sourceLanguageId)
                .setParameter("targetLanguage", targetLanguageId)
                .getResultList();

        List<String> rules = new ArrayList<>();
        rules.add(languages.get(0).getRule());
        rules.add(languages.get(1).getRule());

        return rules;
    }
    public Dictionary findNameDictionaryById(Dictionary dictionary) throws IndexOutOfBoundsException {
        List<Language> language = getCurrentSession().createQuery("from Language where id =: sourceLanguage or id =: targetLanguage", Language.class)
                .setParameter("sourceLanguage", dictionary.getSourceLanguageId())
                .setParameter("targetLanguage", dictionary.getTargetLanguageId())
                .getResultList();

        dictionary.setSourceLanguage(language.get(0).getLanguage());
        dictionary.setTargetLanguage(language.get(1).getLanguage());
        return dictionary;
    }
}
