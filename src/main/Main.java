package main;

import main.dictionarywork.Dictionary;
import main.dictionarywork.DictionaryManager;
import main.view.ConsoleApp;
import main.view.InfoCommands;
import main.view.commands.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Commands> listCommand = new ArrayList<>();
        DictionaryManager dictionaryManager = new Dictionary();

        listCommand.add(new Add());
        listCommand.add(new Remove());
        listCommand.add(new Search());
        listCommand.add(new View());
        listCommand.add(new Back());

        ConsoleApp console = new ConsoleApp(listCommand, dictionaryManager);

        console.start();
    }
}
