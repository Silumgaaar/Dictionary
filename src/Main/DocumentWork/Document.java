package main.documentwork;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document implements DocumentManager {
    public static final String EXTENSION = ".txt";
    public static final String DASH = "-";
    public static final String SLASH = "/";
    public static final String REGULAR_EXPRESSION = "RegularExpression.txt";
    public static final String DELIMITER = ",";
    public static final String NEXT_LINE = "\n";
    public static final String ENCODING = "UTF-8";
    public static final String EMPTY_LINE = "";

    private String nameFile;
    private String directory;
    private String[] infoDictionary = new String[2];
    private final HashMap<String, String> dictionaryFileSelection = new HashMap<>();



    public void fileSelection(String nameFile){
        this.nameFile = nameFile + EXTENSION;
    }
    public void currentDirectory(String directory){
        this.directory = directory;
    }
    public String[] getRules(int position) throws IOException {
        infoDictionary(position);
        return infoDictionary;
    }
    public void addInFile(String newKey, String newMeaning) throws IOException {
        Files.write(Paths.get(directory + SLASH + nameFile), (newKey + DASH + newMeaning + NEXT_LINE).getBytes(ENCODING), StandardOpenOption.APPEND);
    }
    public HashMap<String, String> getHashMapFileSelection(String directory, List<String> files) throws IOException {
        dictionaryFileSelection.clear();
        File fileSelected = null;
        String[] strDictionary;

        for(String str : files){
            if(nameFile.equals(str)) {
                fileSelected = new File(directory, nameFile);
            }
        }

        if(fileSelected != null) {
            Path dictionary = fileSelected.toPath();


            for (String str : Files.readAllLines(dictionary)) {
                strDictionary = str.split(DASH);
                dictionaryFileSelection.put(strDictionary[0], strDictionary[1]);
            }
        }
        return dictionaryFileSelection;
    }
    public void fileOverWrite(HashMap<String, String> newDictionary) throws IOException{
        fileCleaning();

        for(Map.Entry<String, String> entry : newDictionary.entrySet()){
            Files.write(Paths.get(directory + SLASH + nameFile), (entry.getKey() + DASH + entry.getValue() + NEXT_LINE).getBytes(ENCODING),StandardOpenOption.APPEND);
        }
    }

    private void infoDictionary(int position) throws IOException {
        String strInfoDictionary = Files.readAllLines(Paths.get(directory + SLASH + REGULAR_EXPRESSION)).get(position);
        infoDictionary = strInfoDictionary.split(DELIMITER);
    }
    private void fileCleaning() throws IOException{
        Files.write(Paths.get(directory + SLASH + nameFile), (EMPTY_LINE).getBytes(ENCODING));
    }



} 
