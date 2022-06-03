package ru.yarkin.dao.language;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ru.yarkin.dao.SessionUtil;
import ru.yarkin.models.database.Language;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class LanguageDao extends SessionUtil {

    public LanguageDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<String> findAllLanguages() {
        List<Language> resultList = currentSession().createQuery("FROM Language", Language.class)
                .getResultList();

        List<String> languages = new ArrayList<>();
        for (Language language : resultList) {
            languages.add(language.getLanguage());
        }

        return languages;
    }

    public List<Long> findAllLanguagesId() {
        List<Language> resultList = currentSession().createQuery("FROM Language", Language.class)
                .getResultList();

        List<Long> languages = new ArrayList<>();
        for (Language language : resultList) {
            languages.add(language.getId());
        }

        return languages;
    }

    public List<String> findRulesOfPair(Long sourceLanguageId, Long targetLanguageId) {
        List<Language> languages = currentSession().createQuery("from Language where id =: sourceLanguage or id =: targetLanguage", Language.class)
                .setParameter("sourceLanguage", sourceLanguageId)
                .setParameter("targetLanguage", targetLanguageId)
                .getResultList();

        List<String> rules = new ArrayList<>();
        rules.add(languages.get(0).getRule());
        rules.add(languages.get(1).getRule());

        return rules;
    }

    public List<String> findNameDictionaryById(Long sourceLanguageId, Long targetLanguageId) {
        List<Language> language = currentSession().createQuery("from Language where id =: sourceLanguage or id =: targetLanguage", Language.class)
                .setParameter("sourceLanguage", sourceLanguageId)
                .setParameter("targetLanguage", targetLanguageId)
                .getResultList();

        List<String> nameLanguage = new ArrayList<>();
        nameLanguage.add(language.get(0).getLanguage());
        nameLanguage.add(language.get(1).getLanguage());

        return nameLanguage;
    }

    public boolean checkLanguageById(Long id) {
        try {
            currentSession().createQuery("from Language where id =: id", Language.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
