package main.directory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DirectoryWork {

    HashMap<String,String> searchDictionary(String name);
    void fileOverWrite(HashMap<String,String> dictionary,String patch);
    HashMap<String,String> readInFile(String patch);
    List<String> getInfoDictionaries();
}
