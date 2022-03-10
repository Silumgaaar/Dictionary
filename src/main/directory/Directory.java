package main.directory;

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

public class Directory implements DirectoryWork{
    private static final String LIBRARY = "src/resources/Library.txt";
    private static final String ERROR_DIRECTORY = "Dictionary list file not found ";
    private static final String FILE_NOT_FOUND = "File not found in directory";
    private static final String DELIMITER = ",";
    private static final String DASH = "-";

    private final List<String> infoDictionaries = new ArrayList<>();


    public Directory(){
        filesDirectory();
    }
    public List<String> getInfoDictionaries(){
        return infoDirectory();
    }
    public HashMap<String,String> searchDictionary(String name){
        HashMap<String,String> infoDictionary = new HashMap<>();
        for(String s : infoDictionaries){
            String[] arr = s.split(DELIMITER);
            if(arr[2].equals(name)){

                infoDictionary.put("rulesKey", arr[0]);
                infoDictionary.put("rulesValue",arr[1]);
                infoDictionary.put("name", arr[2]);
                infoDictionary.put("patch", arr[3]);

                return infoDictionary;
            }
        }
        return infoDictionary;
    }
    public HashMap<String,String> readInFile(String patch){
        HashMap<String,String> dictionary = new HashMap<>();
        try {
            File file = new File(patch);
            Path path = file.toPath();

            for (String str : Files.readAllLines(path)) {
                String[] strDictionary = str.split(DASH);
                dictionary.put(strDictionary[0], strDictionary[1]);
            }

        } catch (IOException e){
            System.out.println("File not found");
        }
        return dictionary;
    }
    public void fileOverWrite(Map<String,String> dictionary,String patch){
        try {
            Files.write(Paths.get(patch), ("").getBytes());

            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                Files.write(Paths.get(patch), (entry.getKey() + DASH + entry.getValue() + "\n").getBytes(), StandardOpenOption.APPEND);
            }
        }catch (IOException e){
            System.out.println(FILE_NOT_FOUND);
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
