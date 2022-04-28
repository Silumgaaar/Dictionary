package ru.yarkin.dao.interfaces;

import ru.yarkin.models.demobase.Languages;
import ru.yarkin.models.demobase.Words;

import java.util.List;

public interface WordsDao {
    List<Words> findAll();
    List<Words> findAll(Languages languages_id);
    Words findWord(String key, Languages languages);
}
