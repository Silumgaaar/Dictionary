package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.dictionarydao.LanguageDao;
import ru.yarkin.dao.dictionarydao.TranslateDao;
import ru.yarkin.dao.dictionarydao.WordDao;
import ru.yarkin.dictionary.Dictionary;
import ru.yarkin.service.validators.ValidationResult;
import ru.yarkin.service.validators.Validator;
import ru.yarkin.service.validators.ValidatorDictionary;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class DictionaryService {
    private static final String SUCCESSFULLY_ADDED = "Пара успешно добавлена";
    private static final String NOT_PAIR = "Пара не найдена";
    private static final String SUCCESSFUL_REMOVAL = "Пара успешно удалена";
    private static final String INCORRECT_ID = "incorrect id";

    private final WordDao wordDao;
    private final TranslateDao translateDao;
    private final LanguageDao languageDao;


    @Autowired
    public DictionaryService(WordDao wordDao, TranslateDao translateDao, LanguageDao languageDao) {
        this.wordDao = wordDao;
        this.translateDao = translateDao;
        this.languageDao = languageDao;
    }

    public List<Dictionary> findAllDictionaries() {

        var languages = languageDao.findAll();

        List<Dictionary> dictionaries = new ArrayList<>();
        for (int i = 0; i < languages.size(); ++i) {
            for (int j = i + 1; j < languages.size(); ++j) {
                Dictionary dictionary = new Dictionary();

                dictionary.setSourceLanguageId(languages.get(i).getId());
                dictionary.setTargetLanguageId(languages.get(j).getId());

                dictionary.setSourceLanguage(languages.get(i).getLanguage());
                dictionary.setTargetLanguage(languages.get(j).getLanguage());

                dictionaries.add(dictionary);
            }
        }

        return dictionaries;
    }

    public List<String> findAllPairsDictionary(Dictionary dictionary) {
        if (dictionary.getError() == null) {
            return translateDao.findAllPairsByDictionary(dictionary.getSourceLanguageId(), dictionary.getTargetLanguageId());
        } else {
            List<String> answer = new ArrayList<>();
            answer.add(dictionary.getError());
            return answer;
        }
    }

    public List<String> findPairDictionaryByKey(Dictionary dictionary, String key) {
        if (dictionary.getError() == null) {
            List<String> answer;
            answer = translateDao.findPairByKey(dictionary.getSourceLanguageId(), dictionary.getTargetLanguageId(), key);

            if (answer.isEmpty()) {
                answer.add(NOT_PAIR);
            }
            return answer;
        } else {
            List<String> answer = new ArrayList<>();
            answer.add(dictionary.getError());
            return answer;
        }
    }

    public List<String> deletePairByKey(Dictionary dictionary, String key) {
        List<String> answer = new ArrayList<>();
        if (dictionary.getError() == null) {
            if (translateDao.deletePair(dictionary.getSourceLanguageId(), dictionary.getTargetLanguageId(), key)) {
                answer.add(SUCCESSFUL_REMOVAL);
            } else {
                answer.add(NOT_PAIR);
            }
        } else {
            answer.add(dictionary.getError());
        }
        return answer;
    }

    public List<String> addPair(Dictionary dictionary, String key, String value) {


        if (dictionary.getError() == null) {
            List<String> answer;

            List<String> rules = languageDao.findRulesDictionary(dictionary.getSourceLanguageId(), dictionary.getTargetLanguageId());

            Validator pairValidator = new ValidatorDictionary(rules.get(0), rules.get(1));
            ValidationResult validationResult = pairValidator.checkAdd(key, value);

            answer = validationResult.getErrorsValidation();

            if (validationResult.isValid()) {
                answer = translateDao.addPair(wordDao.addPairWords(dictionary.getSourceLanguageId(), dictionary.getTargetLanguageId(), key, value));
                if (answer.isEmpty()) {
                    answer.add(SUCCESSFULLY_ADDED);
                }
            }
            return answer;
        } else {
            List<String> answer = new ArrayList<>();
            answer.add(dictionary.getError());
            return answer;
        }
    }


    public void findDictionaryById(String idDictionary, Dictionary dictionary) {
        String[] id = idDictionary.split("-");
        if (id.length != 2) {
            dictionary.setError(INCORRECT_ID);
        } else {
            dictionary.setSourceLanguageId(Long.parseLong(id[0], 10));
            dictionary.setTargetLanguageId(Long.parseLong(id[1], 10));
            try {
                languageDao.findNameDictionaryById(dictionary);
                dictionary.setError(null);
            } catch (IndexOutOfBoundsException e) {
                dictionary.setError(INCORRECT_ID);
            }
        }
    }
}
