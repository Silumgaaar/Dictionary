package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Dictionary implements DictionaryManager{
    public static final String EXTENSION = ".txt";
    Map<String, String> dictionary = new HashMap<>();
    private int sizeArray = 0;
	String[] rules = new String[2];
    public void viewDictionary(File file) throws IOException {

        Path dic = file.toPath();
        for(String str : Files.readAllLines(dic) ){
            System.out.println(str);
        }

    }
	
	public boolean checkAddDictionary(String[] rules, String newKey, String newMeaning){
		String rulesKey = rules[0];
		String rulesMeaning = rules[1];
		
		Pattern patternKey = Pattern.compile(rulesKey);
		Pattern patternMeaning = Pattern.compile(rulesMeaning);
		
		Matcher matcherKey = patternKey.matcher(newKey);
		Matcher matcherMeaning = patternMeaning.matcher(newMeaning);
		
		
		
		if(matcherKey.matches() != true){
			
			return false;
		}
		else if(matcherMeaning.matches() != true){
			
			return false;
		} else{
			
			return true;
		}
	}
	
    public Map dictionarySaving(File file) throws IOException {
        Path dic = file.toPath();
        String[] array = new String[2];
        for(String str : Files.readAllLines((dic))){
            array = str.split("-");
            dictionary.put(array[0],array[1]);
        }
        return dictionary;

    }

     public String searchInDictionary(HashMap<String, String> dictionary, String key){
	return dictionary.get(key);
      }


    public HashMap removeInDictionary(HashMap<String,String> dictionary , String key){
	
 	dictionary.remove(key);
	return dictionary;
	}

}