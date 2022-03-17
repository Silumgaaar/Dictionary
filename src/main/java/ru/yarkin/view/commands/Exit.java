package ru.yarkin.view.commands;

import ru.yarkin.view.Commands;

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
