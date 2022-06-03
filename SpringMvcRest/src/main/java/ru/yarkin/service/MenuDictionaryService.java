package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.language.LanguageDao;
import ru.yarkin.dao.translate.TranslateDao;
import ru.yarkin.dao.word.WordDao;
import ru.yarkin.service.validators.ValidationResult;
import ru.yarkin.service.validators.Validator;
import ru.yarkin.service.validators.ValidatorDictionary;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MenuDictionaryService {
    private static final String DICTIONARY_NOT_FOUND = "Dictionary not found";
    private static final String SUCCESSFULLY_ADDED = "Пара успешно добавлена";

    private final WordDao wordDao;
    private final TranslateDao translateDao;
    private final LanguageDao languageDao;


    @Autowired
    public MenuDictionaryService(WordDao wordDao, TranslateDao translateDao, LanguageDao languageDao) {
        this.wordDao = wordDao;
        this.translateDao = translateDao;
        this.languageDao = languageDao;
    }

    public List<String> findAllPairDictionary(String idDictionary) {
        Long[] languageId = getLanguageId(idDictionary);
        List<String> answer = new ArrayList<>();
        if (languageId.length != 2) {
            answer.add(DICTIONARY_NOT_FOUND);
            return answer;
        }


        return translateDao.findAllPairDictionary(languageId[0], languageId[1]);
    }

    public List<String> findPairDictionaryByKey(String idDictionary, String key) {
        Long[] languageId = getLanguageId(idDictionary);
        List<String> answer = new ArrayList<>();
        if (languageId.length != 2) {
            answer.add(DICTIONARY_NOT_FOUND);
            return answer;
        }
        answer = translateDao.findPairByKey(languageId[0], languageId[1], key);

        return answer;
    }

    public List<String> deletePairByKey(String idDictionary, String key) {
        Long[] languageId = getLanguageId(idDictionary);
        List<String> answer = new ArrayList<>();
        if (languageId.length != 2) {
            answer.add(DICTIONARY_NOT_FOUND);
            return answer;
        }
        answer = translateDao.deletePair(languageId[0], languageId[1], key);
        return answer;
    }

    public List<String> addPair(String idDictionary, String key, String value) {
        Long[] languageId = getLanguageId(idDictionary);
        List<String> answer = new ArrayList<>();

        if (languageId.length != 2) {
            answer.add(DICTIONARY_NOT_FOUND);
            return answer;
        }

        List<String> rules = languageDao.findRulesOfPair(languageId[0], languageId[1]);

        Validator pairValidator = new ValidatorDictionary(rules.get(0), rules.get(1));
        ValidationResult validationResult = pairValidator.checkAdd(key, value);
        answer = validationResult.getErrorsValidation();

        if (validationResult.isValid()) {
            translateDao.addPair(wordDao.addWords(languageId[0], languageId[1], key, value));
            answer.add(SUCCESSFULLY_ADDED);
        }

        return answer;
    }

    public String findDictionaryById(String idDictionary) {
        Long[] languageId = getLanguageId(idDictionary);
        if (languageId.length != 2) {
            return DICTIONARY_NOT_FOUND;
        }

        List<String> nameLanguage = languageDao.findNameDictionaryById(languageId[0], languageId[1]);
        return (nameLanguage.get(0) + " -- " + nameLanguage.get(1));
    }

    private Long[] getLanguageId(String idDictionary) {
        String[] arr = idDictionary.split("-");

        if (arr.length != 2) {
            return new Long[1];
        }
        Long[] languageId = new Long[2];

        languageId[0] = Long.parseLong(arr[0], 10);
        languageId[1] = Long.parseLong(arr[1], 10);

        if (languageDao.checkLanguageById(languageId[0]) && languageDao.checkLanguageById(languageId[1])) {
            return languageId;
        } else return new Long[1];
    }
}
