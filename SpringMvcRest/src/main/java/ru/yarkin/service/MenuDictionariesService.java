package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.yarkin.dao.libraries.LibrariesDao;
import ru.yarkin.models.demobase.Libraries;

import java.util.List;

@Service
public class MenuDictionariesService {
    private final LibrariesDao librariesDao;

    @Autowired
    public MenuDictionariesService(LibrariesDao librariesDao){
        this.librariesDao = librariesDao;
    }

    public List<Libraries> getStartMenu(){
        return librariesDao.findAllDictionary();
    }
}
