package ru.yarkin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.yarkin.view.ConsoleApp;


public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConsoleApp console = context.getBean(ConsoleApp.class);
        console.start();
    }
}
