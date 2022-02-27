package main.dictionarywork;

import java.util.HashMap;

public interface DictionaryManager {
    public String search(HashMap<String,String> dictionarySelected,String key);
    public HashMap<String,String> add(HashMap<String,String> dictionarySelected , String newKey, String newMeaning, String[] info);
    public HashMap<String,String> remove(HashMap<String,String> dictionarySelected, String strDel);
}
