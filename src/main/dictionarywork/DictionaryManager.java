package main.dictionarywork;

import java.util.List;
import java.util.Map;

public interface DictionaryManager {
    Map<String,String> getDictionary();
    List<String> viewDirectory();
    boolean newDictionary(String name);
    boolean add(String newKey, String newValue);
    boolean remove(String key);
    String search(String key);


}
