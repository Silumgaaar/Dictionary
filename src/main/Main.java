package main;

import main.dictionarywork.Dictionary;
import main.dictionarywork.DictionaryManager;
import main.directory.Directory;
import main.directory.DirectoryWork;
import main.view.ConsoleApp;
import main.view.commands.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Commander> listCommand = new ArrayList<>();
        DirectoryWork directoryWork = new Directory();
        DictionaryManager dictionaryManager = new Dictionary(directoryWork);

        listCommand.add(new Add());
        listCommand.add(new Remove());
        listCommand.add(new Search());
        listCommand.add(new View());
        listCommand.add(new Back());
        listCommand.add(new Exit());

        ConsoleApp console = new ConsoleApp(listCommand, dictionaryManager);

        console.start();
    }
}
