package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.Commands;

import java.util.Scanner;

public class Add implements Commander {
    private static final String ENTERING_A_WORD = "Enter a word: ";
    private static final String ENTERING_A_TRANSLATION = "Enter translation: ";
    private static final String ADD_NEW_STRING = "Record successfully added";
    private static final String ERROR_CHECK = "The new pair does not meet the conditions of the dictionary";
    private final Commands infoCommands;

    public Add(){
        infoCommands = Commands.getCommandInfo("Add");
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ENTERING_A_WORD);
        String newKey = scanner.next();
        System.out.print(ENTERING_A_TRANSLATION);
        String newValue = scanner.next();


        if(dictionaryManager.add(newKey, newValue)){
            dictionaryManager.add(newKey, newValue);
            System.out.println(ADD_NEW_STRING);
        }
        else {
            System.out.println(ERROR_CHECK);
        }

    }
}
