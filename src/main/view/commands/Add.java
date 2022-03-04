package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.ConsoleConstants;

public class Add implements Commands{
    public static final String INFO = "Add - add line";
    public static final String NAME = "Add";

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
        System.out.print(ConsoleConstants.ENTERING_A_WORD);
        String newKey = ConsoleConstants.userChoice.next();
        System.out.print(ConsoleConstants.ENTERING_A_TRANSLATION);
        String newValue = ConsoleConstants.userChoice.next();


        if(dictionaryManager.add(newKey, newValue)){
            dictionaryManager.add(newKey, newValue);
            System.out.println(ConsoleConstants.ADD_NEW_STRING);
        }
        else {
            System.out.println(ConsoleConstants.ERROR_CHECK);
        }

    }
}
