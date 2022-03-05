package main.view;

import main.dictionarywork.DictionaryManager;
import main.view.commands.Commands;
import java.util.HashMap;
import java.util.List;

public class ConsoleApp {

    private static final String EXIT_INFO = "Type Exit to end the program ";
    private static final String EXIT = "Exit";
    private static final String COMMAND_NOT_FOUND = "Command entered not found ";
    private static final String ENTER_COMMAND = "Enter a command: ";
    private static final String NUMBER_FORMAT_EXCEPTION = "Enter the number";
    HashMap<Integer, Commands> commands = new HashMap<>();
    DictionaryManager dictionaryManager;
    Commands command;

    public ConsoleApp(List<Commands> listCommand, DictionaryManager dictionaryManager){
        this.dictionaryManager = dictionaryManager;

        for(Commands command : listCommand){
            commands.put(command.getInfo().getNumber(),command);
        }
    }

    public void start(){
        String userChoice = "";
        command = commands.get(5);
        command.execute(dictionaryManager);
        System.out.println(EXIT_INFO);
        while (!userChoice.equalsIgnoreCase(EXIT)){
            System.out.println(ENTER_COMMAND);
            userChoice = User.choice.next();
            try {
                if (InfoCommands.checkCommand(Integer.parseInt(userChoice))) {
                    command = commands.get(Integer.parseInt(userChoice));
                    command.execute(dictionaryManager);
                } else {
                    System.out.println(COMMAND_NOT_FOUND);
                }
            } catch (NumberFormatException e){
                System.out.println(NUMBER_FORMAT_EXCEPTION);
            }
        }
    }
}
