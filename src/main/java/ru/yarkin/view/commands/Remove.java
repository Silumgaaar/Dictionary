package ru.yarkin.view.commands;

import ru.yarkin.exception.DictionaryNotFoundException;
import ru.yarkin.structure.ConfigDictionary;
import ru.yarkin.view.Commands;
import java.util.Scanner;

public class Remove implements Commander {

    private static final String WORD_DELETE = "Word to delete: ";
    private static final String ENTRY_DELETED = "The entry was successfully deleted ";
    private static final String STRING_NOT_FOUND = "String not found in dictionary ";
    private static final String FILE_NOT_FOUND = "File not found in directory";
    private final Commands infoCommands = Commands.REMOVE;
    private final ConfigDictionary config;

    public Remove(ConfigDictionary config){
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
        try {
            if (config.getDictionary().remove(removeStr)) {
                config.getDictionary().remove(removeStr);
                System.out.println(ENTRY_DELETED);
            } else {
                System.out.println(STRING_NOT_FOUND);
            }
        }catch (DictionaryNotFoundException e){
            System.out.println(FILE_NOT_FOUND);
        }
    }
}
