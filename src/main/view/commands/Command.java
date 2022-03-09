package main.view.commands;

import main.dictionarywork.DictionaryManager;

public interface Command {
    main.view.Commands getInfo();
    void execute(DictionaryManager dictionaryManager);
}
