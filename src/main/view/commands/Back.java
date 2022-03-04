package main.view.commands;

import main.dictionarywork.DictionaryManager;

public class Back implements Commands{
    public static final String INFO = "Back - back to menu";
    public static final String NAME = "Back";

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

    }
}
