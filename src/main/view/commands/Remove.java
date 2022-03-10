package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.Commands;

import java.util.Scanner;

public class Remove implements Command {

    private static final String WORD_DELETE = "Word to delete: ";
    private static final String ENTRY_DELETED = "The entry was successfully deleted ";
    private static final String STRING_NOT_FOUND = "String not found in dictionary ";
    private final Commands infoCommands;

    public Remove(){
        infoCommands = Commands.getCommandInfo("Remove");
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(WORD_DELETE);
        String removeStr = scanner.next();
        if(dictionaryManager.remove(removeStr)){
            dictionaryManager.remove(removeStr);
            System.out.println(ENTRY_DELETED);
        }
        else{
            System.out.println(STRING_NOT_FOUND);
        }
    }
}
