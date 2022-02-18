package Main.DictionaryWork;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dictionary implements DictionaryManager {
    public boolean checkAddDictionary(String[] rules, String newKey, String newMeaning){
        String rulesKey = rules[0];
        String rulesMeaning = rules[1];

        Pattern patternKey = Pattern.compile(rulesKey);
        Pattern patternMeaning = Pattern.compile(rulesMeaning);

        Matcher matcherKey = patternKey.matcher(newKey);
        Matcher matcherMeaning = patternMeaning.matcher(newMeaning);



        if(!matcherKey.matches()){

            return false;
        }
        else return matcherMeaning.matches();
    }
    public HashMap<String, String> addInDictionary(HashMap<String, String> dictionarySelected, String newKey, String newMeaning) {
        dictionarySelected.put(newKey, newMeaning);
        return dictionarySelected;
    }
    public HashMap<String, String> removeInDictionary(HashMap<String, String> dictionarySelected, String strDel){
        dictionarySelected.remove(strDel);
        return dictionarySelected;
    }
    public String searchInDictionary(HashMap<String, String> dictionary, String key){
        return dictionary.get(key);
    }

} 