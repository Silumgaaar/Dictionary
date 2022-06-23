package ru.yarkin.dao.dictionarydao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ru.yarkin.dao.AbstractHibernateDao;
import ru.yarkin.dictionary.Dictionary;
import ru.yarkin.dictionary.Pair;
import ru.yarkin.models.database.Translate;
import ru.yarkin.models.database.Word;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TranslateDao extends AbstractHibernateDao<Translate> {

    public TranslateDao() {
        setClazz(Translate.class);
    }

    public List<Pair> findAllPairsByDictionary(Long sourceLanguageId, Long targetLanguageId) {
        List<Translate> resultList = getCurrentSession().createQuery("from Translate where sourceWord.language.id  =: sourceLanguage and targetWord.language.id =: targetLanguage", Translate.class)
                .setParameter("sourceLanguage", sourceLanguageId)
                .setParameter("targetLanguage", targetLanguageId)
                .getResultList();

        List<Pair> pairs = new ArrayList<>();
        for (Translate translate : resultList) {
            Pair pair = new Pair(translate.getSourceWord(), translate.getTargetWord());
            pairs.add(pair);
        }
        return pairs;
    }

    public List<Pair> findPairByKey(Long sourceLanguage, Long targetLanguage, String key) {
        List<Translate> foundPairs = getCurrentSession().createQuery("from Translate where sourceWord.value =: key and sourceWord.language.id =: sourceLanguage  and targetWord.language.id =: targetLanguage", Translate.class)
                .setParameter("key", key)
                .setParameter("sourceLanguage", sourceLanguage)
                .setParameter("targetLanguage", targetLanguage)
                .getResultList();
        List<Pair> answer = new ArrayList<>();

        for (Translate translate : foundPairs) {
            Pair pair = new Pair(translate.getSourceWord(), translate.getTargetWord());
            answer.add(pair);
        }
        return answer;
    }

    public void deletePair(Dictionary dictionary, Pair pair) {
        Session session = getCurrentSession();

        var translate = session.createQuery("from Translate where sourceWord.value =: source and targetWord.language.id =: targetLanguageId and sourceWord.language.id =: sourceLanguage", Translate.class)
                .setParameter("sourceLanguage", dictionary.getSourceLanguageId())
                .setParameter("source", pair.getSourceWord().getValue())
                .setParameter("targetLanguageId", dictionary.getTargetLanguageId())
                .getResultList();

        for (Translate pairForDelete : translate) {
            session.delete(pairForDelete);
        }
    }

    public boolean unique(Pair pair) {
        return getCurrentSession().createQuery("from Translate where sourceWord.id =: key and targetWord.id =: value")
                .setParameter("key", pair.getSourceWord().getId())
                .setParameter("value", pair.getTargetWord().getId())
                .uniqueResult() == null;
    }

    public void createPair(Pair pair) {
        Word sourceWord = pair.getSourceWord();
        Word targetWord = pair.getTargetWord();
        Translate translate = new Translate(sourceWord, targetWord);
        create(translate);
    }
}
