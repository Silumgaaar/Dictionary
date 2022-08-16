package ru.yarkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.yarkin.dao.LibrariesDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuDictionariesService {
    private final LibrariesDao librariesDao;

    @Autowired
    public MenuDictionariesService(LibrariesDao librariesDao){
        this.librariesDao = librariesDao;
    }

    public Map<Long,String> getStartMenu(){
        Map<Long,String> result = new HashMap<>();
        List<Long> id = librariesDao.findAllIdDictionaries();
        List<String> name = librariesDao.findAllNameDictionaries();

        for (int i = 0; i < id.size(); i++){
            result.put(id.get(i),name.get(i));
        }

        return result;
    }
}
