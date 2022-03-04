package main.dictionarywork;

import java.util.HashMap;
import java.util.List;

public interface DictionaryManager {
    String getName();
    String getPatch();
    String getRulesKey();
    String getRulesValue();

    List<String> getDictionaries();
    HashMap<String,String> getDictionary();

    boolean add(String newKey, String newValue);
    boolean remove(String key);
    String search(String key);


}
