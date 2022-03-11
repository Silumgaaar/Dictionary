package main.structure;

import main.dictionarywork.DictionaryManager;
import java.util.List;
import java.util.Map;

public interface Config {
    List<String> getAllNamesDirectory();
    Map<String,String> getInfoDictionary(String name);
    void setDictionary(DictionaryManager dictionary);
    DictionaryManager getDictionary();
}
