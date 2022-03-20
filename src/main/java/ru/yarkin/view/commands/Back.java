package ru.yarkin.view.commands;

import org.springframework.stereotype.Component;
import ru.yarkin.dictionarywork.Dictionary;
import ru.yarkin.dictionarywork.DictionaryManager;
import ru.yarkin.structure.ConfigDictionary;
import ru.yarkin.view.Commands;
import java.util.Map;
import java.util.Scanner;
@Component
public class Back implements Commander {
    private final Commands infoCommands = Commands.BACK;
    private static final String DICTIONARY_SELECTION = "Choose a dictionary: ";
    private static final String FILE_NOT_FOUND = "File not found in directory";
    private final ConfigDictionary config;
    public Back(ConfigDictionary config){
        this.config = config;
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        Map<String,String> info = config.getDirectory().getAll();

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
        for (Map.Entry<String,String> entry : config.getDictionary().getAll().entrySet()) {
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
