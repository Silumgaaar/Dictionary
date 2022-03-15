package org.example;

import org.example.structure.ConfigDictionary;
import org.example.view.ConsoleApp;
import org.example.view.commands.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        List<Commander> listCommand = new ArrayList<>();
        ConfigDictionary config = context.getBean(ConfigDictionary.class);


        listCommand.add(context.getBean(Add.class));
        listCommand.add(context.getBean(Remove.class));
        listCommand.add(context.getBean(Search.class));
        listCommand.add(context.getBean(View.class));
        listCommand.add(context.getBean(Back.class));
        listCommand.add(context.getBean(Exit.class));

        ConsoleApp console = context.getBean(ConsoleApp.class);

        console.start(config);
    }
}
