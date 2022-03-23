package ru.yarkin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yarkin.dictionarywork.Directory;
import ru.yarkin.structure.ConfigDictionary;
import ru.yarkin.view.ConsoleApp;
import ru.yarkin.view.commands.*;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class SpringConfig {
    @Bean
    public Directory directory(){
        return new Directory();
    }
    @Bean
    public ConfigDictionary configDictionary(){
        return new ConfigDictionary(directory());
    }
    @Bean
    public Add add(){
        return new Add(configDictionary());
    }
    @Bean
    public Back back(){
        return new Back(configDictionary());
    }
    @Bean
    public Exit exit(){
        return new Exit();
    }
    @Bean
    public Remove remove(){
        return new Remove(configDictionary());
    }
    @Bean
    public Search search(){
        return new Search(configDictionary());
    }
    @Bean
    public View view(){
        return new View(configDictionary());
    }
    @Bean
    public ConsoleApp consoleApp(){
        List<Commander> commanders = new ArrayList<>();
        commanders.add(add());
        commanders.add(back());
        commanders.add(exit());
        commanders.add(remove());
        commanders.add(search());
        commanders.add(view());
       return new ConsoleApp(commanders);
    }

}
