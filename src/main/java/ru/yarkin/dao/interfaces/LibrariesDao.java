package ru.yarkin.dao.interfaces;

import ru.yarkin.models.demobase.Libraries;

import java.util.List;

public interface LibrariesDao {
    List<Libraries> findAllDictionary();
    Libraries findDictionary(Long id);
}
