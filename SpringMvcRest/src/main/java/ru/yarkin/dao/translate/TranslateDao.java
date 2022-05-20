package ru.yarkin.dao.translate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.libraries.LibrariesDao;
import ru.yarkin.dao.rules.RulesDao;
import ru.yarkin.dao.words.WordsDao;
import ru.yarkin.service.validators.Validator;
import ru.yarkin.service.validators.ValidationResult;
import ru.yarkin.service.validators.ValidatorDictionary;
import ru.yarkin.dao.SessionUtil;
import ru.yarkin.models.demobase.Libraries;
import ru.yarkin.models.demobase.Rules;
import ru.yarkin.models.demobase.Translate;
import ru.yarkin.models.demobase.Words;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;

import javax.persistence.NoResultException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TranslateDao extends SessionUtil{
    private  static final String UNIQUE_ERROR = " Уже есть в словаре";
    private static final String SUCCESSFULLY_ADDED = "Пара успешно добавлена";
    private static final String NOT_PAIR = "Пара не найдена";
    private static final String SUCCESSFUL_REMOVAL = "Удаление прошло успешно";
    private static final String NOT_DICTIONARY = "Словарь не найден";

    private final LibrariesDao librariesDao;
    private final RulesDao rulesDao;
    private final WordsDao wordsDao;

    @Autowired
    public TranslateDao(SessionFactory sessionFactory, LibrariesDao librariesDao, RulesDao rulesDao, WordsDao wordsDao) {
        super(sessionFactory);
        this.librariesDao = librariesDao;
        this.rulesDao = rulesDao;
        this.wordsDao = wordsDao;
    }


    public List<String> add(FormAdd formAdd) {
        Session session = currentSession();
        List<String> resultList = new ArrayList<>();
            try {
                Libraries library = librariesDao.findDictionary(formAdd.getId());


                Rules sourceRule = rulesDao.getRule(library.getSourceLanguageId());
                Rules targetRules = rulesDao.getRule(library.getTargetLanguageId());

                Validator pairValidator = new ValidatorDictionary(sourceRule.getRule(), targetRules.getRule());
                ValidationResult validationResult = pairValidator.checkAdd(formAdd.getKey(), formAdd.getValue());

                resultList = validationResult.getErrorsValidation();


                if (validationResult.isValid()) {
                    List<Words> words = wordsDao.findAll();

                    boolean check = true;
                    for (Words word : words) {
                        if (word.getValue().equals(formAdd.getKey())) {
                            resultList.add(formAdd.getKey() + UNIQUE_ERROR);
                            check = false;
                            break;
                        }
                        if (word.getValue().equals(formAdd.getValue())) {
                            resultList.add(formAdd.getValue() + UNIQUE_ERROR);
                            check = false;
                            break;
                        }
                    }

                    if (check) {
                        Words sourceWord = new Words(formAdd.getKey(), library.getSourceLanguageId());
                        Words targetWord = new Words(formAdd.getValue(), library.getTargetLanguageId());

                        Translate translate = new Translate(sourceWord, sourceWord.getLanguages_id(), targetWord, targetWord.getLanguages_id());

                        session.save(translate);
                        resultList.add(SUCCESSFULLY_ADDED);
                    }
                }

                return resultList;
            } catch (Exception e){
                resultList.add(NOT_DICTIONARY);
                return resultList;
            }
    }


    public Map<Words, Words> findAllPair(Long id) {

        Libraries library = librariesDao.findDictionary(id);

        List<Words> sourceWords = wordsDao.findAll(library.getSourceLanguageId());
        List<Words> targetWords = wordsDao.findAll(library.getTargetLanguageId());

        Map<Words,Words> result = new HashMap<>();
        for (int i = 0; i < sourceWords.size(); i++){
            result.put(sourceWords.get(i), targetWords.get(i));
        }
        return result;
    }


    public Translate search(FormSearch formSearch) {
        Session session = currentSession();
        try{
            Libraries library = librariesDao.findDictionary(formSearch.getId());

            Words words = wordsDao.findWord(formSearch.getKey(), library.getSourceLanguageId());
            return session.createQuery("from Translate where sourceWordsId.id=:id", Translate.class)
                    .setParameter("id", words.getId())
                    .getSingleResult();
        } catch (Exception e){
            return null;
        }
    }


    public List<String> delete(FormDelete formDel) {
        Session session = currentSession();
        List<String> resultList = new ArrayList<>();
        try {
            Libraries library = librariesDao.findDictionary(formDel.getId());
            try {
                Translate translate = session.createQuery("from Translate where sourceWordsId.value =: word and sourceLanguageId =: language", Translate.class)
                        .setParameter("word", formDel.getKey())
                        .setParameter("language", library.getSourceLanguageId())
                        .getSingleResult();
                session.delete(translate);
                resultList.add(SUCCESSFUL_REMOVAL);
            } catch (NoResultException e) {
                resultList.add(NOT_PAIR);
            }
        }catch (Exception e){
            resultList.add(NOT_DICTIONARY);
        }
        return resultList;
    }
}
