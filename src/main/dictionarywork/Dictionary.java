package main.dictionarywork;

import main.directory.DirectoryWork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary implements DictionaryManager {
    private String name;
    private String patch;
    private String rulesKey;
    private String rulesValue;

    private Map<String,String> dictionary = new HashMap<>();
    private final DirectoryWork directoryWork;


    public Dictionary(DirectoryWork directoryWork){
        this.directoryWork = directoryWork;
    }

    public boolean add(String newKey, String newValue){
        if(checkAdd(newKey,newValue)){
            dictionary.put(newKey, newValue);
            directoryWork.fileOverWrite((HashMap<String, String>) dictionary,patch);
            return true;
        }
        return false;
    }
    public HashMap<String,String> getDictionary(){
        return (HashMap<String, String>) dictionary;
    }
    public List<String> viewDirectory(){
        return directoryWork.getInfoDictionaries();
    }

    public boolean newDictionary(String name){
        HashMap<String,String> dictionary;
        dictionary = directoryWork.searchDictionary(name);
        if(!dictionary.isEmpty()) {
            this.name = dictionary.get("name");
            this.patch = dictionary.get("patch");
            this.rulesKey = dictionary.get("rulesKey");
            this.rulesValue = dictionary.get("rulesValue");
            this.dictionary = directoryWork.readInFile(patch);
            return true;
        }
        return false;
    }
    public boolean remove(String key){
        if(dictionary.containsKey(key)){
            dictionary.remove(key);
            directoryWork.fileOverWrite((HashMap<String, String>) dictionary,patch);
            return true;
        }
        return false;
    }
    public String search(String key){
        return dictionary.get(key);
    }



    private boolean checkAdd(String newKey, String newValue){

        Pattern patternKey = Pattern.compile(rulesKey);
        Pattern patternMeaning = Pattern.compile(rulesValue);

        Matcher matcherKey = patternKey.matcher(newKey);
        Matcher matcherMeaning = patternMeaning.matcher(newValue);

        if(!matcherKey.matches()){

            return false;
        }
        else return matcherMeaning.matches();
    }
}