package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.InfoCommands;
import main.view.User;

import java.util.Objects;

public class Search implements Commands{

    private static final String STRING_NOT_FOUND = "String not found in dictionary ";
    private static final String WORD_SEARCH = "Enter a word: ";
    private final InfoCommands infoCommands;

    public Search(){
        infoCommands = InfoCommands.getCommandInfo("search");
    }

    @Override
    public InfoCommands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {

        System.out.print(WORD_SEARCH);
        String str = dictionaryManager.search(User.choice.next());
        System.out.println(Objects.requireNonNullElse(str,STRING_NOT_FOUND));

    }
}
