package main.view;

public enum InfoCommands {
    ADD("Add - add line", "Add", 1),
    REMOVE("Del - delete line", "Remove", 2),
    VIEW("View - output dictionary", "View", 3),
    SEARCH("Ser - string search", "Search", 4),
    BACK("Back - back to menu", "Back", 5),
    START("","",0); // убрать

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
    private int getNumber(){
        return number;
    }
    public static InfoCommands getCommandInfo(String command){
        for(InfoCommands infoCommands : values()){
            if(infoCommands.getName().equalsIgnoreCase(command)){
                return infoCommands;
            }
        }
        return null;
    }


    public String toString() {
        return title;
    }
}
