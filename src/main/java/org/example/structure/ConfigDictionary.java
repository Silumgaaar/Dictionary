package org.example.structure;

import org.example.dictionarywork.DictionaryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ConfigDictionary{
    private static final String DELIMITER = ",";

    DictionaryManager dictionary;
    DictionaryManager directory;

    @Autowired
    public ConfigDictionary(DictionaryManager directory){
        this.directory = directory;
    }
   // @Autowired
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
        return createInfoDictionary(name);
    }

    private Map<String,String> createInfoDictionary(String name) {
        Map<String,String> infoDictionary = new HashMap<>();
        String[] info =  directory.view().get(name).split(DELIMITER);

        infoDictionary.put("rulesKey", info[0]);
        infoDictionary.put("rulesValue", info[1]);
        infoDictionary.put("patch", info[2]);

        return infoDictionary;
    }
}
