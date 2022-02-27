package main.documentswork;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface DocumentsManager {
    List<String> getFileNames() throws IOException;
    HashMap<String, String> dictionaryHashMap(String fileName) throws IOException;
    public String[] getInfoDictionary(String name) throws IOException;
    public void fileOverWrite(HashMap<String, String> newDictionary, String[] info) throws IOException;
}
