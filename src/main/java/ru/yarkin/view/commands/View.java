package ru.yarkin.view.commands;

import org.springframework.stereotype.Component;
import ru.yarkin.structure.ConfigDictionary;
import ru.yarkin.view.Commands;

import java.util.Map;
@Component
public class View implements Commander {

    private final Commands infoCommands = Commands.VIEW;
    private final ConfigDictionary config;

    public View(ConfigDictionary config){
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
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String,String> entry : dictionary.entrySet()) {
           stringBuilder.append(entry).append("\n");
        }
        System.out.print(stringBuilder);
    }
}
