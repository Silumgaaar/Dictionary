package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.language.LanguageDao;
import ru.yarkin.dao.translate.TranslateDao;
import ru.yarkin.dao.word.WordDao;
import ru.yarkin.dictionary.Dictionary;
import ru.yarkin.dictionary.Pair;
import ru.yarkin.service.validators.ValidationResult;
import ru.yarkin.service.validators.Validator;
import ru.yarkin.service.validators.ValidatorDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class DictionaryService {
    private static final String SUCCESSFULLY_ADDED = "Пара успешно добавлена";
    private static final String NOT_PAIR = "Пара не найдена";
    private static final String SUCCESSFUL_REMOVAL = "Пара успешно удалена";
    private static final String INCORRECT_ID = "incorrect id";
    private static final String UNIQUE = "Пара уже существует";


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

        List<Long> idLanguage = languageDao.findAllIdLanguage();
        List<String> nameLanguage = languageDao.findAllNameLanguage();

        List<Dictionary> dictionaries = new ArrayList<>();
        for (int i = 0; i < idLanguage.size(); ++i) {
            for (int j = i + 1; j < idLanguage.size(); ++j) {
                Dictionary dictionary = new Dictionary();

                dictionary.setSourceLanguageId(idLanguage.get(i));
                dictionary.setTargetLanguageId(idLanguage.get(j));

                dictionary.setSourceLanguage(nameLanguage.get(i));
                dictionary.setTargetLanguage(nameLanguage.get(j));

                dictionaries.add(dictionary);
            }
        }
        return dictionaries;
    }

    public List<Pair> findAllPairsDictionary(Dictionary dictionary) {
        return translateDao.findAllPairsByDictionary(dictionary.getSourceLanguageId(), dictionary.getTargetLanguageId());
    }

    public List<Pair> findPairDictionaryByKey(Dictionary dictionary, String key) {
        return translateDao.findPairByKey(dictionary.getSourceLanguageId(), dictionary.getTargetLanguageId(), key);
    }

    public List<String> deletePairByKey(Dictionary dictionary, String key) {

        List<String> answer = new ArrayList<>();
        if (dictionary.getError() == null) {
            List<Pair> pairs = findAllPairsDictionary(dictionary);

            for (Pair pair : pairs) {
                if (pair.getValueSourceWord().equals(key)) {
                    translateDao.deletePair(dictionary, pair);
                    answer.add(SUCCESSFUL_REMOVAL);
                    return answer;
                }
            }
            answer.add(NOT_PAIR);
        } else {
            answer.add(dictionary.getError());
        }
        return answer;
    }

    public List<String> addPair(Dictionary dictionary, String key, String value) {

        List<String> answer = new ArrayList<>();
        if (dictionary.getError() == null) {
            List<String> rules = languageDao.findRulesDictionary(dictionary.getSourceLanguageId(), dictionary.getTargetLanguageId());

            Validator pairValidator = new ValidatorDictionary(rules.get(0), rules.get(1));
            ValidationResult validationResult = pairValidator.checkAdd(key, value);

            answer = validationResult.getErrorsValidation();

            if (validationResult.isValid()) {
                Map<String, Long> words = wordDao.getAllValueAndLanguageWord();

                Pair pair = new Pair();
                for (Map.Entry<String, Long> word : words.entrySet()) {
                    if (key.equals(word.getKey()) && dictionary.getSourceLanguageId().equals(word.getValue())) {
                        pair.setSourceWord(wordDao.findWordByValueAndIdLanguage(key, dictionary.getSourceLanguageId()));
                    }
                    if (value.equals(word.getKey()) && dictionary.getTargetLanguageId().equals(word.getValue())) {
                        pair.setTargetWord(wordDao.findWordByValueAndIdLanguage(value, dictionary.getTargetLanguageId()));
                    }
                }

                if (pair.getSourceWord() == null) {
                    pair.setSourceWord(wordDao.createWord(key, dictionary.getSourceLanguageId()));
                    wordDao.create(pair.getSourceWord());
                }
                if (pair.getTargetWord() == null) {
                    pair.setTargetWord(wordDao.createWord(value, dictionary.getTargetLanguageId()));
                    wordDao.create(pair.getTargetWord());
                }

                if (translateDao.unique(pair)) {
                    translateDao.createPair(pair);
                    answer.add(SUCCESSFULLY_ADDED);
                } else {
                    answer.add(UNIQUE);
                }
            }
        } else {
            answer.add(INCORRECT_ID);
        }
        return answer;
    }


    public Dictionary findDictionaryById(String idDictionary) {
        Dictionary dictionary = new Dictionary();
        String[] id = idDictionary.split("-");
        if (id.length != 2) {
            dictionary.setError(INCORRECT_ID);
            return dictionary;
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

        return dictionary;
    }
}
