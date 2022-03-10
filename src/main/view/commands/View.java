package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.Commands;


import java.util.Map;

public class View implements Commander {

    private final Commands infoCommands;
    private final DictionaryManager dictionaryManager;

    public View(DictionaryManager dictionaryManager){
        infoCommands = Commands.getCommandInfo("View");
        this.dictionaryManager = dictionaryManager;
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        viewDictionary(dictionaryManager.getDictionary());
    }
    private void viewDictionary(Map<String,String> dictionary){
        for (Map.Entry<String,String> entry : dictionary.entrySet()) {
            System.out.println(entry);
        }
    }
}
