package main.view;

import main.view.commands.Commander;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final String COMMAND_NOT_FOUND = "Command entered not found ";
    private static final String ENTER_COMMAND = "Enter a command: ";

    private final HashMap<String, Commander> commands = new HashMap<>();

    public ConsoleApp(List<Commander> listCommand){
        for(Commander command : listCommand){
            commands.put(command.getInfo().getName(),command);
        }
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        Commander commander = commands.get("Back");
        commander.execute();
        while (!commander.getInfo().getName().equals("Exit")){
            System.out.print(ENTER_COMMAND);
            String userChoice = scanner.next();

            if (commands.get(userChoice) != null) {
                commander = commands.get(userChoice);
                commander.execute();
            } else {
                System.out.println(COMMAND_NOT_FOUND);
            }
        }
    }
}