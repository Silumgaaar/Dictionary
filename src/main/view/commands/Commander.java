package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.Commands;

public interface Commander {
    Commands getInfo();
    void execute();
}
