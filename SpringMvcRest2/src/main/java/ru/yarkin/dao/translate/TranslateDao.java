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

    public void addPair(Pair pair) {
        Translate translate = new Translate(pair.getSourceWord(), pair.getTargetWord());
        create(translate);

    }

    public void deletePair(Long sourceLanguage, Long targetLanguage, String key) {
        Session session = getCurrentSession();
        List<String> allPair = findAllPairsByDictionary(sourceLanguage, targetLanguage);

        for (String pair : allPair) {
            String[] arr = pair.split("-");
            if (arr[0].equals(key)) {
                var translate = session.createQuery("from Translate where sourceWord.value =: source and targetWord.language.id =: targetLanguageId", Translate.class)
                        .setParameter("source", key)
                        .setParameter("targetLanguageId", targetLanguage)
                        .getResultList();
                for (Translate pairForDelete : translate) {
                    session.delete(pairForDelete);
                }
            }
        }
    }


}
