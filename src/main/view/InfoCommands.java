package main.view;

public enum InfoCommands {
    ADD("Add - add line", "Add", 1),
    REMOVE("Remove - delete line", "Remove", 2),
    VIEW("View - output dictionary", "View", 3),
    SEARCH("Search - string search", "Search", 4),
    BACK("Back - back to menu", "Back", 5);

    private final String title;
    private final String name;
    private final int number;

    InfoCommands(String title, String name, int number){
        this.title = title;
        this.name = name;
        this.number = number;
    }
    public String getName(){
        return name;
    }
    public int getNumber(){
        return number;
    }
    private String getTitle(){
        return title;
    }
    public static InfoCommands getCommandInfo(String command){
        for(InfoCommands infoCommands : values()){
            if(infoCommands.getName().equalsIgnoreCase(command)){
                return infoCommands;
            }
        }
        return null;
    }
    public static boolean checkCommand(int command){
        for(InfoCommands infoCommands : values()){
            if(infoCommands.getNumber() == command){
                return true;
            }
        }
        return false;
    }
    public StringBuilder viewMenu(){
        StringBuilder stringBuilder = new StringBuilder();
        for(InfoCommands infoCommands : values()) {
            stringBuilder.append(infoCommands.getNumber()).append(" ").append(infoCommands.getTitle()).append("\n");
        }
        return stringBuilder;
    }
}
