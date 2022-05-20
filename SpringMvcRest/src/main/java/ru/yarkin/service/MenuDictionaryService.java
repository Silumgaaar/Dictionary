package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.yarkin.dao.libraries.LibrariesDao;
import ru.yarkin.dao.translate.TranslateDao;
import ru.yarkin.models.demobase.Libraries;
import ru.yarkin.models.demobase.Translate;
import ru.yarkin.models.demobase.Words;
import ru.yarkin.models.form.FormAdd;
import ru.yarkin.models.form.FormDelete;
import ru.yarkin.models.form.FormSearch;

import java.util.List;
import java.util.Map;

@Service
public class MenuDictionaryService {
    private final LibrariesDao librariesDao;
    private final TranslateDao translateDao;

    @Autowired
    public MenuDictionaryService(LibrariesDao librariesDao, TranslateDao translateDao){
        this.librariesDao = librariesDao;
        this.translateDao = translateDao;
    }

    public Libraries findDictionaryById(Long id) {
        return librariesDao.findDictionary(id);
    }

    public List<String> addPair(FormAdd formAdd){
        return translateDao.add(formAdd);
    }

    public List<String> deletePair(FormDelete formDelete){
        return translateDao.delete(formDelete);
    }

    public Map<Words, Words> findAllPairDictionary(Long id){
        return  translateDao.findAllPair(id);
    }

    public Translate searchPair(FormSearch formSearch){
        return translateDao.search(formSearch);
    }
}
