package ru.yarkin.dictionarywork;

import ru.yarkin.exception.DictionaryNotFoundException;
import java.util.Map;

public interface DictionaryManager {
    boolean add(String newKey, String newValue) throws DictionaryNotFoundException;
    boolean remove(String key) throws DictionaryNotFoundException;
    Map<String,String> getAll();
    String search(String key);

}
