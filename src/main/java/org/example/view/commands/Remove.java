package org.example.view.commands;

import org.example.structure.ConfigDictionary;
import org.example.view.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Remove implements Commander {

    private static final String WORD_DELETE = "Word to delete: ";
    private static final String ENTRY_DELETED = "The entry was successfully deleted ";
    private static final String STRING_NOT_FOUND = "String not found in dictionary ";
    private final Commands infoCommands;
    private final ConfigDictionary config;
    @Autowired
    public Remove(ConfigDictionary config){
        infoCommands = Commands.getCommandInfo("Remove");
        this.config = config;

    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(WORD_DELETE);
        String removeStr = scanner.next();
        if(config.getDictionary().remove(removeStr)){
            config.getDictionary().remove(removeStr);
            System.out.println(ENTRY_DELETED);
        }
        else{
            System.out.println(STRING_NOT_FOUND);
        }
    }
}
