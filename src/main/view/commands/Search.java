package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.ConsoleConstants;

import java.util.Objects;

public class Search implements Commands{

    public static final String INFO = "Search - string search";
    public static final String NAME = "Search";

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

        System.out.print(ConsoleConstants.WORD_SEARCH);
        String str = dictionaryManager.search(ConsoleConstants.userChoice.next());
        System.out.println(Objects.requireNonNullElse(str, ConsoleConstants.STRING_NOT_FOUND));

    }
}
