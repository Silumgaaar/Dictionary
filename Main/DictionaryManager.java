package Main;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

interface DictionaryManager {
    public void viewDictionary(File file) throws IOException;
    public Map dictionarySaving(File file) throws IOException;
    public String searchInDictionary(HashMap<String, String> dictionary, String key);
    public HashMap removeInDictionary(HashMap<String, String> dictionary, String key);
    public boolean checkAddDictionary(String[] rules, String newKey, String newMeaning);

}
