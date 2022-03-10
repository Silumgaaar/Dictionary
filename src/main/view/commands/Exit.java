package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.Commands;

public class Exit implements Commander {
    private final Commands infoCommands;
    private static final String PROGRAM_COMPLETED = "Program completed";

    public Exit(){
        infoCommands = Commands.getCommandInfo("Exit");
    }
    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        System.out.println(PROGRAM_COMPLETED);
    }
}
