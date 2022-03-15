package org.example.view;

public enum Commands {
    ADD("Add - add line", "Add"),
    REMOVE("Remove - delete line", "Remove"),
    VIEW("View - output dictionary", "View"),
    SEARCH("Search - string search", "Search"),
    BACK("Back - back to menu", "Back"),
    EXIT("Exit - exit from the program", "Exit");

    private final String title;
    private final String name;

    Commands(String title, String name){
        this.title = title;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    private String getTitle(){
        return title;
    }
    public static Commands getCommandInfo(String command){
        for(Commands infoCommands : values()){
            if(infoCommands.getName().equals(command)){
                return infoCommands;
            }
        }
        return null;
    }
    public StringBuilder viewMenu(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Commands infoCommands : values()) {
            stringBuilder.append(infoCommands.getTitle()).append(" | ");
        }
        return stringBuilder;
    }
}
