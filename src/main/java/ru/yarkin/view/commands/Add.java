package ru.yarkin.view.commands;

import org.springframework.stereotype.Component;
import ru.yarkin.structure.ConfigDictionary;
import ru.yarkin.view.Commands;
import java.util.Scanner;
@Component
public class Add implements Commander {
    private static final String ENTERING_A_WORD = "Enter a word: ";
    private static final String ENTERING_A_TRANSLATION = "Enter translation: ";
    private static final String ADD_NEW_STRING = "Record successfully added";
    private static final String ERROR_CHECK = "The new pair does not meet the conditions of the dictionary";
    private final Commands infoCommands = Commands.ADD;
    private final ConfigDictionary config;

    public Add(ConfigDictionary config){
        this.config = config;
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(ENTERING_A_WORD);
        String newKey = scanner.next();
        System.out.print(ENTERING_A_TRANSLATION);
        String newValue = scanner.next();


        if(config.getDictionary().add(newKey, newValue)){
            config.getDictionary().add(newKey, newValue);
            System.out.println(ADD_NEW_STRING);
        }
        else {
            System.out.println(ERROR_CHECK);
        }

    }
}
