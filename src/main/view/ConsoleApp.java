package main.view;


import main.dictionarywork.Dictionary;
import main.dictionarywork.DictionaryManager;
import main.view.commands.Commands;
import main.view.commands.Start;
import main.view.commands.View;


import java.util.List;

public class ConsoleApp {
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
            userChoice = ConsoleConstants.userChoice.next();
            if (userChoice.equalsIgnoreCase("exit")) {
                exitProgram = true;
                continue;
            }
            if(checkFile(nameFiles, userChoice)) {

                DictionaryManager dictionary = new Dictionary(userChoice);
                command = new View();
                command.execute(dictionary);
                System.out.println(viewMenu());

                dictionaryMenu:
                while (!command.getName().equalsIgnoreCase("Back")){
                    userChoice = ConsoleConstants.userChoice.next();
                    for(Commands c : listCommand){
                        if(c.getName().equalsIgnoreCase(userChoice)){
                            command = c;
                            command.execute(dictionary);
                            continue dictionaryMenu;
                        }
                    }

                    System.out.println("Command not found");
                    continue mainMenu;
                }
            }
            else {
                System.out.println("File not found");
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
