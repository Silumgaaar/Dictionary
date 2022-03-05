package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.User;

public class Add implements Commands{
    private static final String INFO = "Add - add line";
    private static final String NAME = "Add";
    private static final String ENTERING_A_WORD = "Enter a word: ";
    private static final String ENTERING_A_TRANSLATION = "Enter translation: ";
    private static final String ADD_NEW_STRING = "Record successfully added";
    private static final String ERROR_CHECK = "The new pair does not meet the conditions of the dictionary";

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
        System.out.print(ENTERING_A_WORD);
        String newKey = User.choice.next();
        System.out.print(ENTERING_A_TRANSLATION);
        String newValue = User.choice.next();


        if(dictionaryManager.add(newKey, newValue)){
            dictionaryManager.add(newKey, newValue);
            System.out.println(ADD_NEW_STRING);
        }
        else {
            System.out.println(ERROR_CHECK);
        }

    }
}
