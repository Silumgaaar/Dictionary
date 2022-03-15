package org.example.structure;

import org.example.dictionarywork.DictionaryManager;
import java.util.HashMap;
import java.util.Map;

public class ConfigDictionary{
    private static final String DELIMITER = ",";

    DictionaryManager dictionary;
    DictionaryManager directory;


    public ConfigDictionary(DictionaryManager directory){
        this.directory = directory;
    }

    public void setDictionary(DictionaryManager dictionary) {
        this.dictionary = dictionary;
    }

    public DictionaryManager getDictionary() {
        return dictionary;
    }

    public DictionaryManager getDirectory(){
        return directory;
    }

    public Map<String,String> getInfoDictionary(String name){
        return newInfoDictionary(name);
    }

    private Map<String,String> newInfoDictionary(String name) {
        Map<String,String> infoDictionary = new HashMap<>();
        String[] info =  directory.getAll().get(name).split(DELIMITER);

        infoDictionary.put("rulesKey", info[0]);
        infoDictionary.put("rulesValue", info[1]);
        infoDictionary.put("patch", info[2]);

        return infoDictionary;
    }
}
