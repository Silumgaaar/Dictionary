package main.view.commands;

import main.dictionarywork.DictionaryManager;

public interface Commander {
    main.view.Commands getInfo();
    void execute(DictionaryManager dictionaryManager);
}
