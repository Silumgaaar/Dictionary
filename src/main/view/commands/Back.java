package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.Commands;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Back implements Commander {
    private final Commands infoCommands;
    private static final String DICTIONARY_SELECTION = "Choose a dictionary: ";
    private static final String FILE_NOT_FOUND = "File not found in directory";

    public Back(){
        infoCommands = Commands.getCommandInfo("Back");
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {
        List<String> list;
        list = dictionaryManager.viewDirectory();
        StringBuilder s = new StringBuilder();

        for(String str : list){
            s.append(str).append("\n");
        }
        System.out.print(s + DICTIONARY_SELECTION);
        Scanner choice = new Scanner(System.in);
        while (!dictionaryManager.newDictionary(choice.next())){
            System.out.println(FILE_NOT_FOUND);
        }

        for (Map.Entry<String,String> entry : dictionaryManager.getDictionary().entrySet()) {
            System.out.println(entry);
        }
        System.out.println(infoCommands.viewMenu());
    }
}
