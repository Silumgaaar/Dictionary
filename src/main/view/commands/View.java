package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.Commands;


import java.util.HashMap;
import java.util.Map;

public class View implements Command {

    private final Commands infoCommands;

    public View(){
        infoCommands = Commands.getCommandInfo("View");
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {
        viewDictionary(dictionaryManager.getDictionary());
    }
    private void viewDictionary(Map<String,String> dictionary){
        for (Map.Entry<String,String> entry : dictionary.entrySet()) {
            System.out.println(entry);
        }
    }
}
