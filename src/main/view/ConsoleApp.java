package main.view;

import main.dictionarywork.DictionaryManager;
import main.view.commands.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final String COMMAND_NOT_FOUND = "Command entered not found ";
    private static final String ENTER_COMMAND = "Enter a command: ";

    private final HashMap<String, Command> commands = new HashMap<>();
    private final DictionaryManager dictionaryManager;

    public ConsoleApp(List<Command> listCommand, DictionaryManager dictionaryManager){
        this.dictionaryManager = dictionaryManager;

        for(Command command : listCommand){
            commands.put(command.getInfo().getName(),command);
        }
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        Command command = commands.get("Back");
        command.execute(dictionaryManager);
        while (!command.getInfo().getName().equals("Exit")){
            System.out.print(ENTER_COMMAND);
            String userChoice;
            userChoice = scanner.next();

            if (Commands.checkCommand(userChoice)) {
                command = commands.get(userChoice);
                command.execute(dictionaryManager);
            } else {
                System.out.println(COMMAND_NOT_FOUND);
            }
        }
    }
}