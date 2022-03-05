package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.InfoCommands;

public interface Commands {
    InfoCommands getInfo();
    void execute(DictionaryManager dictionaryManager);
}
