package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.ConsoleConstants;

public class Remove implements Commands{
    public static final String INFO = "Remove - remove line";
    public static final String NAME = "Remove";

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
        System.out.print(ConsoleConstants.WORD_DELETE);
        String choice = ConsoleConstants.userChoice.next();
        if(dictionaryManager.remove(choice)){
            dictionaryManager.remove(choice);
            System.out.println(ConsoleConstants.ENTRY_DELETED);
        }
        else{
            System.out.println(ConsoleConstants.STRING_NOT_FOUND);
        }
    }
}
