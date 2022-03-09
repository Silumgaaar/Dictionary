package main.dictionarywork;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DictionaryManager {
    HashMap<String,String> getDictionary();
    List<String> viewDirectory();
    boolean newDictionary(String name);
    boolean add(String newKey, String newValue);
    boolean remove(String key);
    String search(String key);


}
