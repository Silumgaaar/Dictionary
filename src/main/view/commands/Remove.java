package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.User;

public class Remove implements Commands{
    private static final String INFO = "Remove - remove line";
    private static final String NAME = "Remove";
    private static final String WORD_DELETE = "Word to delete: ";
    private static final String ENTRY_DELETED = "The entry was successfully deleted ";
    private static final String STRING_NOT_FOUND = "String not found in dictionary ";

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
        System.out.print(WORD_DELETE);
        String choice = User.choice.next();
        if(dictionaryManager.remove(choice)){
            dictionaryManager.remove(choice);
            System.out.println(ENTRY_DELETED);
        }
        else{
            System.out.println(STRING_NOT_FOUND);
        }
    }
}
