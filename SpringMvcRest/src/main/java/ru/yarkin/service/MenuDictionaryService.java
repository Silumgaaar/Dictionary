package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.yarkin.dao.LibrariesDao;
import ru.yarkin.dao.RulesDao;
import ru.yarkin.dao.TranslateDao;
import ru.yarkin.service.validators.ValidationResult;
import ru.yarkin.service.validators.Validator;
import ru.yarkin.service.validators.ValidatorDictionary;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuDictionaryService {
    private final LibrariesDao librariesDao;
    private final TranslateDao translateDao;
    private final RulesDao rulesDao;
    private static final String NOT_DICTIONARY = "Словарь не найден";

    @Autowired
    public MenuDictionaryService(LibrariesDao librariesDao, TranslateDao translateDao, RulesDao rulesDao){
        this.librariesDao = librariesDao;
        this.translateDao = translateDao;
        this.rulesDao = rulesDao;
    }

    public String findDictionaryById(Long id) {
        return librariesDao.findNameDictionaryById(id);
    }

    public List<String> addPair(Long id, String key, String value){
        List<String> resultList = new ArrayList<>();

        try {

            String sourceRule = rulesDao.getRule(librariesDao.findSourceIdLanguage(id)).getRule();
            String targetRule = rulesDao.getRule(librariesDao.findTargetIdLanguage(id)).getRule();


            Validator pairValidator = new ValidatorDictionary(sourceRule, targetRule);
            ValidationResult validationResult = pairValidator.checkAdd(key, value);
            resultList = validationResult.getErrorsValidation();

            if (validationResult.isValid()) {
                resultList.add(translateDao.add(id, key, value));
                return resultList;
            }
        } catch (NoResultException e){
            resultList.add(NOT_DICTIONARY);
            return resultList;
        }
        return resultList;
    }

    public List<String> deletePair(Long id, String key){
        return translateDao.delete(id, key);
    }

    public Map<String, String> findAllPairDictionary(Long id){
        return translateDao.findAllPair(id);
    }

    public List<String> searchPair(Long id, String key){
        return translateDao.search(id,key);
    }

    public String getSourceLanguageName(Long id){
        return librariesDao.findSourceIdLanguage(id).getLanguage();
    }

    public String getTargetLanguageName(Long id){
        return librariesDao.findTargetIdLanguage(id).getLanguage();
    }
}
