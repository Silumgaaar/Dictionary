package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.InfoCommands;
import main.view.User;

public class Remove implements Commands{

    private static final String WORD_DELETE = "Word to delete: ";
    private static final String ENTRY_DELETED = "The entry was successfully deleted ";
    private static final String STRING_NOT_FOUND = "String not found in dictionary ";
    private final InfoCommands infoCommands;

    public Remove(){
        infoCommands = InfoCommands.getCommandInfo("remove");
    }

    @Override
    public InfoCommands getInfo() {
        return infoCommands;
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
