package main.view;

import main.dictionarywork.DictionaryManager;

public enum CommandsMenu {
    ADD("Add - add line", "Add"),
    DEL("Del - delete line", "Del"),
    VIEW("View - output dictionary", "View"),
    SEARCH("Ser - string search", "Ser"),
    BACK("Back - back to menu", "Back");

    private String title;
    private String name;

    CommandsMenu(String title, String name){
        this.title = title;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static CommandsMenu checkCommand(String command){
        for(CommandsMenu commandsMenu : values()){
            if(commandsMenu.getName().equals(command)){
                return commandsMenu;
            }
        }
        return null;

    }

    public String toString() {
        return title;
    }
}
