package main.view.commands;

import main.dictionarywork.DictionaryManager;


import java.util.HashMap;
import java.util.Map;

public class View implements Commands{
    public static final String INFO = "View - output dictionary";
    public static final String NAME = "View";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {
        viewDictionary(dictionaryManager.getDictionary());
    }
    private void viewDictionary(HashMap<String,String> dictionary){
        for (Map.Entry<String,String> entry : dictionary.entrySet()) {
            System.out.println(entry);
        }
    }
}
