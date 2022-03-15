package org.example.view.commands;

import org.example.structure.ConfigDictionary;
import org.example.view.Commands;
import java.util.Objects;
import java.util.Scanner;

public class Search implements Commander {

    private static final String STRING_NOT_FOUND = "String not found in dictionary ";
    private static final String WORD_SEARCH = "Enter a word: ";
    private final Commands infoCommands;
    private final ConfigDictionary config;

    public Search(ConfigDictionary config){
        infoCommands = Commands.getCommandInfo("Search");
        this.config = config;
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(WORD_SEARCH);
        String str = config.getDictionary().search(scanner.next());
        System.out.println(Objects.requireNonNullElse(str,STRING_NOT_FOUND));

    }
}
