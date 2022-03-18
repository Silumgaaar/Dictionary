package ru.yarkin.dictionarywork;

import java.util.Map;

public interface DictionaryManager {
    boolean add(String newKey, String newValue);
    boolean remove(String key);
    Map<String,String> getAll();
    String search(String key);

}
