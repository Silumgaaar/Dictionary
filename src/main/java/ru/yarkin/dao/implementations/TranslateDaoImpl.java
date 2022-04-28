package ru.yarkin.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.controllers.validators.ValidationResult;
import ru.yarkin.controllers.validators.Validator;
import ru.yarkin.controllers.validators.ValidatorDictionary;
import ru.yarkin.dao.SessionUtil;
import ru.yarkin.dao.interfaces.LibrariesDao;
import ru.yarkin.dao.interfaces.RulesDao;
import ru.yarkin.dao.interfaces.TranslateDao;
import ru.yarkin.dao.interfaces.WordsDao;
import ru.yarkin.models.demobase.Libraries;
import ru.yarkin.models.demobase.Rules;
import ru.yarkin.models.demobase.Translate;
import ru.yarkin.models.demobase.Words;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class TranslateDaoImpl extends SessionUtil implements TranslateDao {
    private  static final String UNIQUE_ERROR = " Уже есть в словаре";
    private static final String SUCCESSFULLY_ADDED = "Пара успешно добавлена";
    private final LibrariesDao librariesDao;
    private final RulesDao rulesDao;
    private final WordsDao wordsDao;

    @Autowired
    public TranslateDaoImpl(SessionFactory sessionFactory, LibrariesDao librariesDao, RulesDao rulesDao, WordsDao wordsDao) {
        super(sessionFactory);
        this.librariesDao = librariesDao;
        this.rulesDao = rulesDao;
        this.wordsDao = wordsDao;
    }

    @Override
    public List<String> add(FormAdd formAdd) {
        Session session = currentSession();

        Libraries library = librariesDao.findDictionary(formAdd.getId());

        Rules sourceRule = rulesDao.getRule(library.getSourceLanguageId());
        Rules targetRules = rulesDao.getRule(library.getTargetLanguageId());

        Validator pairValidator = new ValidatorDictionary(sourceRule.getRule(), targetRules.getRule());
        ValidationResult validationResult = pairValidator.checkAdd(formAdd.getKey(), formAdd.getValue());

        List<String> resultList = validationResult.getErrorsValidation();
        if(validationResult.isValid()) {
            List<Words> words = wordsDao.findAll();

            boolean check = true;
            for (Words word : words) {
                if (word.getValue().equals(formAdd.getKey())) {
                    resultList.add(formAdd.getKey() + UNIQUE_ERROR);
                    check = false;
                    break;
                }
                if(word.getValue().equals(formAdd.getValue())){
                    resultList.add(formAdd.getValue() + UNIQUE_ERROR);
                    check = false;
                    break;
                }
            }

            if(check) {
                Words sourceWord = new Words(formAdd.getKey(), library.getSourceLanguageId());
                Words targetWord = new Words(formAdd.getValue(), library.getTargetLanguageId());

                Translate translate = new Translate(sourceWord, sourceWord.getLanguages_id(), targetWord, targetWord.getLanguages_id());

                session.save(translate);
                resultList.add(SUCCESSFULLY_ADDED);
            }
        }

        return resultList;
    }

    @Override
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

    @Override
    public Translate search(FormSearch formSearch) {
        Session session = currentSession();
        Libraries library = librariesDao.findDictionary(formSearch.getId());

        try {
            Words words = wordsDao.findWord(formSearch.getKey(), library.getSourceLanguageId());
            return session.createQuery("from Translate where sourceWordsId.id=:id", Translate.class)
                    .setParameter("id", words.getId())
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public boolean delete(FormDelete formDel) {
        Session session = currentSession();
        Libraries library = librariesDao.findDictionary(formDel.getId());
        try {
            Translate translate = session.createQuery("from Translate where sourceWordsId.value =: word and sourceLanguageId =: language", Translate.class)
                    .setParameter("word", formDel.getKey())
                    .setParameter("language", library.getSourceLanguageId())
                    .getSingleResult();
            session.delete(translate);
            return true;
        } catch (NoResultException e){
            return false;
        }
    }
}
