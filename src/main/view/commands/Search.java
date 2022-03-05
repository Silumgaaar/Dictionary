package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.User;

import java.util.Objects;

public class Search implements Commands{

    private static final String INFO = "Search - string search";
    private static final String NAME = "Search";
    private static final String STRING_NOT_FOUND = "String not found in dictionary ";
    private static final String WORD_SEARCH = "Enter a word: ";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getInfo() {
        return INFO;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {

        System.out.print(WORD_SEARCH);
        String str = dictionaryManager.search(User.choice.next());
        System.out.println(Objects.requireNonNullElse(str,STRING_NOT_FOUND));

    }
}
