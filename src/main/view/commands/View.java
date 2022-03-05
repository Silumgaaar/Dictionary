package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.InfoCommands;


import java.util.HashMap;
import java.util.Map;

public class View implements Commands{

    private final InfoCommands infoCommands;

    public View(){
        infoCommands = InfoCommands.getCommandInfo("view");
    }

    @Override
    public InfoCommands getInfo() {
        return infoCommands;
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
