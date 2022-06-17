package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.language.LanguageDao;
import ru.yarkin.dao.translate.TranslateDao;
import ru.yarkin.dao.word.WordDao;
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

    public List<String> findAllPairsDictionary(Long sourceLanguageId, Long targetLanguageId) {
        return translateDao.findAllPairsByDictionary(sourceLanguageId, targetLanguageId);
    }

    public List<String> findPairDictionaryByKey(Long sourceLanguage, Long targetLanguage, String key) {

        List<String> answer;
        answer = translateDao.findPairByKey(sourceLanguage, targetLanguage, key);

        if (answer.isEmpty()) {
            answer.add(NOT_PAIR);
        }

        return answer;
    }

    public List<String> deletePairByKey(Long sourceLanguage, Long targetLanguage, String key) {

        List<String> answer = new ArrayList<>();
        if(translateDao.deletePair(sourceLanguage, targetLanguage, key)) {
            answer.add(SUCCESSFUL_REMOVAL);
        }
        else {
            answer.add(NOT_PAIR);
        }
        return answer;
    }

    public List<String> addPair(Long sourceLanguage, Long targetLanguage, String key, String value) {

        List<String> rules = languageDao.findRulesDictionary(sourceLanguage, targetLanguage);

        List<String> answer;
        Validator pairValidator = new ValidatorDictionary(rules.get(0), rules.get(1));
        ValidationResult validationResult = pairValidator.checkAdd(key, value);

        answer = validationResult.getErrorsValidation();

        if (validationResult.isValid()) {
            answer = translateDao.addPair(wordDao.addPairWords(sourceLanguage, targetLanguage, key, value));
            if(answer.isEmpty()){
                answer.add(SUCCESSFULLY_ADDED);
            }
        }
        return answer;
    }
}
