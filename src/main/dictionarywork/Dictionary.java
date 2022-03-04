package main.dictionarywork;

import main.view.ConsoleConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
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
    private final HashMap<String,String> dictionary = new HashMap<>();
    private final List<String> dictionaries = new ArrayList<>();

    private static final String LIBRARY = "src/resources/Library.txt";
    private static final String DELIMITER = ",";
    private static final String ERROR_DIRECTORY = "Файл со списком словарей не найден";
    private static final String DASH = "-";

    public Dictionary(){
        infoDirectory();
    }
    public Dictionary(String name){
        infoDirectory();
        infoDictionary(name);
        readInFile();
    }


    public String getName(){
        return name;
    }
    public String getPatch(){
        return patch;
    }
    public String getRulesKey(){
        return rulesKey;                                                    // Геттеры возможно приваты
    }
    public String getRulesValue(){
        return rulesValue;
    }
    public List<String> getDictionaries(){
        return dictionaries;
    }
    public HashMap<String,String> getDictionary(){
        return dictionary;
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

    public String search(String key){
        return dictionary.get(key);
    }


    private void fileOverWrite(){
        try {
            Files.write(Paths.get(patch), ("").getBytes());

            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                Files.write(Paths.get(patch), (entry.getKey() + DASH + entry.getValue() + "\n").getBytes(), StandardOpenOption.APPEND);
            }
        }catch (IOException e){
            System.out.println(ConsoleConstants.FILE_NOT_FOUND);
        }
    }
    private void infoDirectory(){                           // чтение файла Library, запись информации об именах файлов в директории в List<String> dictionaries
            for(String s : filesDirectory()){
                String[] arr = s.split(DELIMITER);
                dictionaries.add(arr[2]);
            }
    }

    private List<String> filesDirectory(){
        List<String> list = new ArrayList<>();
        try {
            File file = new File(LIBRARY);
            Path path = file.toPath();
            list.addAll(Files.readAllLines(path));
        } catch (IOException e){
            System.out.println(ERROR_DIRECTORY);
            //list.add(""); проверить
        }
        return list;
    }
    private boolean checkAdd(String newKey, String newValue){
        String rulesKey = getRulesKey();
        String rulesValue = getRulesValue();

        Pattern patternKey = Pattern.compile(rulesKey);
        Pattern patternMeaning = Pattern.compile(rulesValue);

        Matcher matcherKey = patternKey.matcher(newKey);
        Matcher matcherMeaning = patternMeaning.matcher(newValue);

        if(!matcherKey.matches()){

            return false;
        }
        else return matcherMeaning.matches();

    }

    private void readInFile(){
        try {
            File file = new File(getPatch());
            Path path = file.toPath();

            for (String str : Files.readAllLines(path)) {
                String[] strDictionary = str.split(DASH);
                dictionary.put(strDictionary[0], strDictionary[1]);
            }
        } catch (IOException e){
            System.out.println("File not found");
        }
    }

    private void infoDictionary(String name){
        for(String s : filesDirectory()){
            String[] arr = s.split(DELIMITER);
            if(arr[2].equals(name)){
                this.rulesKey = arr[0];
                this.rulesValue = arr[1];
                this.name = arr[2];
                this.patch = arr[3];
                break;
            }
        }
    }

}
