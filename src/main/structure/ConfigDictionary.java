package main.structure;

import main.dictionarywork.DictionaryManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigDictionary implements Config {
    private static final String LIBRARY = "src/resources/Library.txt";
    private static final String ERROR_DIRECTORY = "Dictionary list file not found ";
    private static final String DELIMITER = ",";

    Map<String,String> infoDictionary = new HashMap<>();
    List<String> infoDictionaries = new ArrayList<>();
    DictionaryManager dictionaryManager;


    public ConfigDictionary(){
        filesDirectory();
    }

    @Override
    public void setDictionary(DictionaryManager dictionary) {
        this.dictionaryManager = dictionary;
    }

    @Override
    public DictionaryManager getDictionary() {
        return dictionaryManager;
    }

    public Map<String,String> getInfoDictionary(String name){
        newInfoDictionary(name);
        return infoDictionary;
    }

    public List<String> getAllNamesDirectory(){
        return infoDirectory();
    }



    private void newInfoDictionary(String name) {
        for (String s : infoDictionaries) {
            String[] arr = s.split(DELIMITER);
            if (arr[2].equals(name)) {
                infoDictionary.clear();

                infoDictionary.put("rulesKey", arr[0]);
                infoDictionary.put("rulesValue", arr[1]);
                infoDictionary.put("name", arr[2]);
                infoDictionary.put("patch", arr[3]);

                break;
            }
        }
    }
    private void filesDirectory(){
        try {
            File file = new File(LIBRARY);
            Path path = file.toPath();
            infoDictionaries.addAll(Files.readAllLines(path));
        } catch (IOException e){
            System.out.println(ERROR_DIRECTORY);
        }
    }
    private List<String> infoDirectory(){
        List<String> dictionaries = new ArrayList<>();
        for(String s : infoDictionaries){
            String[] arr = s.split(DELIMITER);
            dictionaries.add(arr[2]);
        }
        return dictionaries;
    }

}
