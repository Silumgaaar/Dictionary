package main.dictionarywork;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary implements DictionaryManager{
    public String search(HashMap<String,String> dictionarySelected,String key){
        return dictionarySelected.get(key);
    }
    public HashMap<String,String> add(HashMap<String,String> dictionarySelected , String newKey, String newMeaning, String[] info){
        if(checkAdd(info,newKey,newMeaning)){
            dictionarySelected.put(newKey, newMeaning);
            return dictionarySelected;
        }
        return null;
    }
    public HashMap<String,String> remove(HashMap<String,String> dictionarySelected, String strDel){
        dictionarySelected.remove(strDel);
        return dictionarySelected;
    }
    private boolean checkAdd(String[] info, String newKey, String newMeaning){
        String rulesKey = info[0];
        String rulesMeaning = info[1];

        Pattern patternKey = Pattern.compile(rulesKey);
        Pattern patternMeaning = Pattern.compile(rulesMeaning);

        Matcher matcherKey = patternKey.matcher(newKey);
        Matcher matcherMeaning = patternMeaning.matcher(newMeaning);



        if(!matcherKey.matches()){

            return false;
        }
        else return matcherMeaning.matches();

    }
}
