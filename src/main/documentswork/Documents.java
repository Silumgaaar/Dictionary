package main.documentswork;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Documents implements DocumentsManager {
    public static final String DictionaryCollection = "src/resources/DictionaryCollection.txt";
    public static final String EMPTY_LINE = "";
    public static final String DELIMITER = ",";
    public static final String DASH = "-";
    public static final String ENCODING = "UTF-8";
    public static final String NEXT_LINE = "\n";
    public List<String> getFileNames() throws IOException{
        List<String> fileName;

        fileName = FileNameDictionaryCollection(dictionaryCollection());
        return fileName;

    }
    public void fileOverWrite(HashMap<String, String> newDictionary, String[] info) throws IOException{
        fileCleaning(info);

        for(Map.Entry<String, String> entry : newDictionary.entrySet()){
            Files.write(Paths.get(info[3]), (entry.getKey() + DASH + entry.getValue() + NEXT_LINE).getBytes(ENCODING), StandardOpenOption.APPEND);
        }
    }
    public HashMap<String, String> dictionaryHashMap(String fileName) throws IOException {
        if(checkFile(fileName)) {
            HashMap<String, String> dictionary;
            dictionary = readInFile(infoDictionary(fileName,dictionaryCollection()));
            return dictionary;
        }
        return null;
    }
    public String[] getInfoDictionary(String name) throws IOException {
        return infoDictionary(name,dictionaryCollection());
    }
    private void fileCleaning(String[] info) throws IOException{
        Files.write(Paths.get(info[3]), (EMPTY_LINE).getBytes(ENCODING));
    }

    private boolean checkFile(String fileName) throws IOException {
        List<String> list;

        list = FileNameDictionaryCollection(dictionaryCollection());
        for(String s : list){
            if(s.equals(fileName)){
                return true;
            }
        }
        return false;
    }

    private String[] infoDictionary(String name, List<String> collection){
        String[] info = new String[4];
        for(String str : collection){
            String[] inf = str.split(DELIMITER);
            if(inf[2].equals(name)){
                info = inf;
                break;
            }
        }
        return info;
    }
    private HashMap<String,String> readInFile(String[] info) throws IOException {
        HashMap<String,String> dictionary = new HashMap<>();
        File fileSelected = new File(info[3]);
        Path file = fileSelected.toPath();

        for (String str : Files.readAllLines(file)) {
            String[] strDictionary = str.split(DASH);
            dictionary.put(strDictionary[0], strDictionary[1]);
        }
        return dictionary;
    }
    private List<String> dictionaryCollection() throws IOException{
        List<String> list = new ArrayList<>();
        File file = new File(DictionaryCollection);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        while (line != null){
            list.add(line);
            line = reader.readLine();
        }

        return list;
    }
    private List<String> FileNameDictionaryCollection(List<String> list){
        List<String> fileName = new ArrayList<>();
        for(String str : list){
            String[] inf = str.split(DELIMITER);
            fileName.add(inf[2]);
        }
        return fileName;
    }
}
