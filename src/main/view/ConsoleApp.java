package main.view;


import main.dictionarywork.Dictionary;
import main.dictionarywork.DictionaryManager;
import main.view.commands.Commands;
import main.view.commands.Start;
import main.view.commands.View;


import java.util.List;

public class ConsoleApp {

    private static final String EXIT_INFO = "Type Exit to end the program ";
    private static final String EXIT = "Exit";
    private static final String COMMAND_NOT_FOUND = "Command entered not found ";
    private static final String FILE_NOT_FOUND = "File not found in directory";
    private static final String ENTER_COMMAND = "Enter a command: ";
    List<Commands> listCommand;

    public ConsoleApp(List<Commands> listCommand){
        this.listCommand = listCommand;
    }
    public void start(){

        String userChoice;
        List<String>  nameFiles;
        boolean exitProgram = false;

        DictionaryManager directory = new Dictionary();
        nameFiles = directory.getDictionaries();

        mainMenu:
        while(!exitProgram) {
            Commands command = new Start();
            command.execute(directory);
            System.out.println(EXIT_INFO);
            userChoice = User.choice.next();
            if (userChoice.equalsIgnoreCase(EXIT)) {
                exitProgram = true;
                continue;
            }
            if(checkFile(nameFiles, userChoice)) {

                DictionaryManager dictionary = new Dictionary(userChoice);
                command = new View();
                command.execute(dictionary);
                System.out.println(viewMenu());
                dictionaryMenu:
                while (!command.getInfo().getName().equalsIgnoreCase("Back")){
                    System.out.println(ENTER_COMMAND);
                    userChoice = User.choice.next();
                    for(Commands c : listCommand){
                        if(c.getInfo().getName().equalsIgnoreCase(userChoice)){
                            command = c;
                            command.execute(dictionary);
                            continue dictionaryMenu;
                        }
                    }

                    System.out.println(COMMAND_NOT_FOUND);
                    continue mainMenu;
                }
            }
            else {
                System.out.println(FILE_NOT_FOUND);
            }

        }


    }
    private boolean checkFile(List<String> nameFiles, String userChoice){
        for(String str : nameFiles){
            if(str.equals(userChoice)){
                return true;
            }
        }
        return false;
    }
    private String viewMenu(){
        StringBuilder menu = new StringBuilder();
        for(Commands com : listCommand){
            menu.append(com.getInfo()).append(" | ");
        }

        return menu.toString();
    }
}
