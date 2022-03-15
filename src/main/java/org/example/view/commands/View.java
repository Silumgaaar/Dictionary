package org.example.view.commands;

import org.example.structure.ConfigDictionary;
import org.example.view.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class View implements Commander {

    private final Commands infoCommands;
    private final ConfigDictionary config;
    @Autowired
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
        viewDictionary(config.getDictionary().view());
    }
    private void viewDictionary(Map<String,String> dictionary){
        for (Map.Entry<String,String> entry : dictionary.entrySet()) {
            System.out.println(entry);
        }
    }
}
