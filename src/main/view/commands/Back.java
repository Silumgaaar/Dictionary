package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.InfoCommands;

public class Back implements Commands{
    private final InfoCommands infoCommands;

    public Back(){
        infoCommands = InfoCommands.getCommandInfo("back");
    }


    @Override
    public InfoCommands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {

    }
}
