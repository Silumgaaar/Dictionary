package main.view.commands;

import main.dictionarywork.DictionaryManager;

public interface Commands {
    String getName();
    String getInfo();
    void execute(DictionaryManager dictionaryManager);
}
