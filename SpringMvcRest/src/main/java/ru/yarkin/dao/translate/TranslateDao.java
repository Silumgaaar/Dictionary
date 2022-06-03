package ru.yarkin.dao.translate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ru.yarkin.dao.SessionUtil;
import ru.yarkin.models.database.Translate;
import ru.yarkin.models.database.Word;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TranslateDao extends SessionUtil {
    private static final String SUCCESSFUL_REMOVAL = "Удаление прошло успешно";
    private static final String NOT_PAIR = "Пара не найдена";


    public TranslateDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<String> findAllPairDictionary(Long sourceLanguageId, Long targetLanguageId) {
        List<Translate> resultList = currentSession().createQuery("from Translate", Translate.class)
                .getResultList();

        List<String> pairs = new ArrayList<>();
        for (Translate translate : resultList) {

            if (translate.getSourceWord().getLanguage().getId().equals(sourceLanguageId) && translate.getTargetWord().getLanguage().getId().equals(targetLanguageId)) {
                pairs.add(translate.getSourceWord().getValue() + "-" + translate.getTargetWord().getValue());
            }
        }
        return pairs;

    }

    public List<String> findPairByKey(Long sourceLanguage, Long targetLanguage, String key) {

        List<String> allPair = findAllPairDictionary(sourceLanguage, targetLanguage);

        List<String> searchResult = new ArrayList<>();
        for (String pair : allPair) {
            String[] arr = pair.split("-");
            if (arr[0].equals(key)) {
                searchResult.add(arr[0] + "--" + arr[1]);
            }
        }

        if (searchResult.isEmpty()) {
            searchResult.add(NOT_PAIR);
        }

        return searchResult;
    }

    public List<String> deletePair(Long sourceLanguage, Long targetLanguage, String key) {
        Session session = currentSession();
        List<String> answer = new ArrayList<>();
        List<String> allPair = findAllPairDictionary(sourceLanguage, targetLanguage);

        for (String pair : allPair) {
            String[] arr = pair.split("-");
            if (arr[0].equals(key)) {
                var translate = session.createQuery("from Translate where sourceWord.value =: source", Translate.class)
                        .setParameter("source", key)
                        .getResultList();
                for (Translate pairForDelete : translate) {
                    session.delete(pairForDelete);
                }
                answer.add(SUCCESSFUL_REMOVAL);
                return answer;

            }
        }
        answer.add(NOT_PAIR);
        return answer;

    }

    public void addPair(List<Word> words) {
        Session session = currentSession();
        Translate translate = new Translate(words.get(0), words.get(1));
        session.save(translate);
    }

}
