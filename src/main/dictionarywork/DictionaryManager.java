package main.dictionarywork;

import java.util.HashMap;
import java.util.List;

public interface DictionaryManager {

    HashMap<String,String> getDictionary();
    List<String> getDictionaries();
    boolean newDictionary(String name);
    boolean add(String newKey, String newValue);
    boolean remove(String key);
    String search(String key);


}
