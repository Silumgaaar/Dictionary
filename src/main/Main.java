package main;

import main.structure.Config;
import main.structure.ConfigDictionary;
import main.view.ConsoleApp;
import main.view.commands.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Commander> listCommand = new ArrayList<>();
        Config config = new ConfigDictionary();


        listCommand.add(new Add(config));
        listCommand.add(new Remove(config));
        listCommand.add(new Search(config));
        listCommand.add(new View(config));
        listCommand.add(new Back(config));
        listCommand.add(new Exit());

        ConsoleApp console = new ConsoleApp(listCommand);

        console.start();
    }
}
