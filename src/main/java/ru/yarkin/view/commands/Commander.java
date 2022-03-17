package ru.yarkin.view.commands;

import ru.yarkin.view.Commands;

public interface Commander {
    Commands getInfo();
    void execute();
}
