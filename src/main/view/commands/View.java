package main.view.commands;

import main.structure.ConfigDictionary;
import main.view.Commands;
import java.util.Map;

public class View implements Commander {

    private final Commands infoCommands;
    private final ConfigDictionary config;

    public View(ConfigDictionary config){
        infoCommands = Commands.getCommandInfo("View");
        this.config = config;
    }

    @Override
    public Commands getInfo() {
        return infoCommands;
    }

    @Override
    public void execute() {
        viewDictionary(config.getDictionary().getAll());
    }
    private void viewDictionary(Map<String,String> dictionary){
        for (Map.Entry<String,String> entry : dictionary.entrySet()) {
            System.out.println(entry);
        }
    }
}
