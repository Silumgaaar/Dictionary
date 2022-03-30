package ru.yarkin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.yarkin.view.ConsoleApp;

public class Main {
    public static void main(String[] args){
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
            ConsoleApp console = context.getBean(ConsoleApp.class);
            System.out.println(context.getType("consoleApp"));
            console.start();
    }
}
