package ru.yarkin.dao.translate;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ru.yarkin.dao.AbstractHibernateDao;
import ru.yarkin.dictionary.Pair;
import ru.yarkin.models.database.Translate;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TranslateDao extends AbstractHibernateDao<Translate> {
    private static final String UNIQUE = "Пара уже существует";

    public TranslateDao() {
        setClazz(Translate.class);
    }

    public List<String> findAllPairsByDictionary(Long sourceLanguageId, Long targetLanguageId) {
        List<Translate> resultList = getCurrentSession().createQuery("from Translate where sourceWord.language.id  =: sourceLanguage and targetWord.language.id =: targetLanguage", Translate.class)
                .setParameter("sourceLanguage", sourceLanguageId)
                .setParameter("targetLanguage", targetLanguageId)
                .getResultList();

        List<String> pairs = new ArrayList<>();
        for (Translate translate : resultList) {
            pairs.add(translate.getSourceWord().getValue() + "-" + translate.getTargetWord().getValue());
        }
        return pairs;
    }

    public List<String> findPairByKey(Long sourceLanguage, Long targetLanguage, String key) {
        List<Translate> foundPairs = getCurrentSession().createQuery("from Translate where sourceWord.value =: key and sourceWord.language.id =: sourceLanguage  and targetWord.language.id =: targetLanguage", Translate.class)
                .setParameter("key", key)
                .setParameter("sourceLanguage", sourceLanguage)
                .setParameter("targetLanguage", targetLanguage)
                .getResultList();
        List<String> answer = new ArrayList<>();

        for (Translate translate : foundPairs) {
            answer.add(translate.getSourceWord().getValue() + "-" + translate.getTargetWord().getValue());
        }
        return answer;
    }

    public List<String> addPair(Pair pair) {
        List<String> answer = new ArrayList<>();
        if (unique(pair.getSourceWord().getId(), pair.getTargetWord().getId())) {
            Translate translate = new Translate(pair.getSourceWord(), pair.getTargetWord());
            create(translate);
        } else {
            answer.add(UNIQUE);
        }

        return answer;
    }

    public boolean deletePair(Long sourceLanguage, Long targetLanguage, String key) {
        Session session = getCurrentSession();

        var translate = session.createQuery("from Translate where sourceWord.value =: source and targetWord.language.id =: targetLanguageId and sourceWord.language.id =: sourceLanguage", Translate.class)
                .setParameter("sourceLanguage", sourceLanguage)
                .setParameter("source", key)
                .setParameter("targetLanguageId", targetLanguage)
                .getResultList();

        if (translate.isEmpty()) {
            return false;
        }

        for (Translate pairForDelete : translate) {
            session.delete(pairForDelete);
        }
        return true;
    }

    private boolean unique(Long keyId, Long valueId) {
        return getCurrentSession().createQuery("from Translate where sourceWord.id =: key and targetWord.id =: value")
                .setParameter("key", keyId)
                .setParameter("value", valueId)
                .uniqueResult() == null;
    }
}
