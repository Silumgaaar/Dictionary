package main.documentwork;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface DocumentManager {
    public void fileSelection(String nameFile);
    public HashMap<String, String> getHashMapFileSelection(String directory, List<String> files) throws IOException;
    public void currentDirectory(String directory);
    public String[] getRules(int position) throws IOException;
    public void addInFile(String newKey, String newMeaning) throws IOException;
    public void fileOverWrite(HashMap<String, String> newDictionary) throws IOException;
} 