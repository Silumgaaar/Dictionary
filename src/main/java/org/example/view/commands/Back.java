package org.example.view.commands;

import org.example.dictionarywork.Dictionary;
import org.example.dictionarywork.DictionaryManager;
import org.example.structure.ConfigDictionary;
import org.example.view.Commands;
import java.util.Map;
import java.util.Scanner;

public class Back implements Commander {
    private final Commands infoCommands;
    private static final String DICTIONARY_SELECTION = "Choose a dictionary: ";
    private static final String FILE_NOT_FOUND = "File not found in directory";
    private final ConfigDictionary config;
    public Back(ConfigDictionary config){
        infoCommands = Commands.getCommandInfo("Back");
        this.config = config;
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        Map<String,String> info = config.getDirectory().view();

        StringBuilder s = new StringBuilder();
        for(String str : info.keySet()){
            s.append(str).append("\n");
        }

        System.out.print(s + DICTIONARY_SELECTION);

        Scanner scanner = new Scanner(System.in);

        String choice = scanner.next();
        while (!check(info, choice)){
            System.out.print(FILE_NOT_FOUND + "\n" + DICTIONARY_SELECTION);
            choice = scanner.next();
        }
        DictionaryManager dictionaryManager = new Dictionary(config,choice);
        config.setDictionary(dictionaryManager);
        for (Map.Entry<String,String> entry : config.getDictionary().view().entrySet()) {
            System.out.println(entry);
        }
        System.out.println(infoCommands.viewMenu());
    }



    private boolean check(Map<String,String> info, String choice){
        for(String name : info.keySet()){
            if(name.equals(choice)){
                return true;
            }
        }
        return false;
    }
}
