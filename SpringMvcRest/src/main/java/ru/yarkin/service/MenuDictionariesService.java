package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.yarkin.dao.language.LanguageDao;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class MenuDictionariesService {
    private final LanguageDao languageDao;

    @Autowired
    public MenuDictionariesService(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }


    public Map<String, String> findAllDictionaries() {

        var languages = languageDao.findAllLanguages();
        var idLanguages = languageDao.findAllLanguagesId();

        Map<String, String> dictionaries = new HashMap<>();

        for (int i = 0; i < languages.size(); ++i) {
            for (int j = i + 1; j < languages.size(); ++j) {
                dictionaries.put(idLanguages.get(i) + "-" + idLanguages.get(j), languages.get(i) + " - " + languages.get(j));
            }
        }

        return dictionaries;
    }

}
