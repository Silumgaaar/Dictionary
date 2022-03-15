package org.example.dictionarywork;


import org.example.structure.ConfigDictionary;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary implements DictionaryManager {
    private static final String DASH = "-";
    private static final String FILE_NOT_FOUND = "File not found in directory";
    private final Map<String,String> dictionary = new HashMap<>();
    private String path;
    private String rulesKey;
    private String rulesValue;
    private final ConfigDictionary config;

    public Dictionary(ConfigDictionary config, String name){
        this.config = config;
        createDictionary(name);
    }

    public boolean add(String newKey, String newValue){
        if(checkAdd(newKey,newValue)){
            dictionary.put(newKey, newValue);
            fileOverWrite();
            return true;
        }
        return false;
    }

    public boolean remove(String key){
        if(dictionary.containsKey(key)){
            dictionary.remove(key);
            fileOverWrite();
            return true;
        }
        return false;
    }
    public Map<String,String> view(){
        return dictionary;
    }

    public String search(String key){
        return dictionary.get(key);
    }

    private void createDictionary(String name){
        Map<String,String> infoDictionary;
        infoDictionary = config.getInfoDictionary(name);
        if(!infoDictionary.isEmpty()) {
            this.path = infoDictionary.get("patch");
            this.rulesKey = infoDictionary.get("rulesKey");
            this.rulesValue = infoDictionary.get("rulesValue");
            readInFile(path);
        }
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
    private void fileOverWrite(){
        try {
            Files.write(Paths.get(path), ("").getBytes());

            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                Files.writeString(Paths.get(path), entry.getKey() + DASH + entry.getValue() + "\n", StandardOpenOption.APPEND);
            }
        }catch (IOException e){
            System.out.println(FILE_NOT_FOUND);
        }
    }
    private void readInFile(String patch){
        try {
            File file = new File(patch);
            Path path = file.toPath();

            for (String str : Files.readAllLines(path, StandardCharsets.UTF_8)) {
                String[] strDictionary = str.split(DASH);
                dictionary.put(strDictionary[0], strDictionary[1]);
            }

        } catch (IOException e){
            System.out.println("File not found");
        }
    }
}