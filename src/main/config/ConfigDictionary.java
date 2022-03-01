package main.config;

import main.documentswork.DocumentsManager;

import java.io.IOException;

public class ConfigDictionary implements Config{
    private String name;
    private String patch;
    private String rulesKey;
    private String rulesValue;

    private final DocumentsManager documentsManager;

    public ConfigDictionary(DocumentsManager documentsManager, String name) throws IOException { // поправить исключения
        this.documentsManager = documentsManager;
        infoDictionary(name);
    }

    public String getName(){
        return name;
    }
    public String getPatch(){
        return patch;
    }
    public String getRulesKey(){
        return rulesKey;
    }
    public String getRulesValue(){
        return rulesValue;
    }

    private void infoDictionary(String name) throws IOException { // поправить исключения
        String[] info = documentsManager.getInfoDictionary(name);
        this.rulesKey = info[0];
        this.rulesValue = info[1];
        this.name = info[2];
        this.patch = info[3];
    }
}
