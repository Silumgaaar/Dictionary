package ru.yarkin.structure;

import ru.yarkin.dictionarywork.DictionaryManager;


public class ConfigDictionary{
    private DictionaryManager dictionary;
    private final DictionaryManager directory;

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

    public String getInfoDictionary(String name){
        return directory.search(name);
    }
}
