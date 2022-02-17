package Main;
import java.util.HashMap;

public interface DictionaryManager {
    public boolean checkAddDictionary(String[] rules, String newKey, String newMeaning);
    public HashMap<String, String> addInDictionary(HashMap<String, String> dictionarySelected, String newKey, String newMeaning);
    public HashMap<String, String> removeInDictionary(HashMap<String, String> dictionarySelected, String strDel);
    public String searchInDictionary(HashMap<String, String> dictionary, String key);
} 