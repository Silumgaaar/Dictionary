package main.dictionarywork;

import java.util.Map;

public interface DictionaryManager {
    boolean add(String newKey, String newValue);
    boolean remove(String key);
    Map<String,String> view();
    String search(String key);

    Map<String,String> getAll();


}
