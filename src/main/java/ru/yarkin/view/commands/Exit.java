package ru.yarkin.view.commands;

import org.springframework.stereotype.Component;
import ru.yarkin.view.Commands;
@Component
public class Exit implements Commander {
    private final Commands infoCommands = Commands.EXIT;
    private static final String PROGRAM_COMPLETED = "Program completed";


    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        System.out.println(PROGRAM_COMPLETED);
    }
}
