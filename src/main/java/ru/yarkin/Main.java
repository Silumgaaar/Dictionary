package ru.yarkin;


import ru.yarkin.dictionarywork.DictionaryManager;
import ru.yarkin.dictionarywork.Directory;
import ru.yarkin.exception.LibraryDictionariesNotFoundException;
import ru.yarkin.structure.ConfigDictionary;
import ru.yarkin.view.ConsoleApp;
import ru.yarkin.view.commands.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String ERROR_DIRECTORY = "Dictionary list file not found ";
    public static void main(String[] args){
        try {
            DictionaryManager directory = new Directory();
            ConfigDictionary config = new ConfigDictionary(directory);

            List<Commander> listCommand = new ArrayList<>();
            listCommand.add(new Add(config));
            listCommand.add(new Remove(config));
            listCommand.add(new Search(config));
            listCommand.add(new View(config));
            listCommand.add(new Back(config));
            listCommand.add(new Exit());

            ConsoleApp console = new ConsoleApp(listCommand);

            console.start();
        }catch (LibraryDictionariesNotFoundException e){
            e.printStackTrace();
            System.out.println(ERROR_DIRECTORY);
        }
    }
}
