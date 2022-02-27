package main;

import main.dictionarywork.Dictionary;
import main.dictionarywork.DictionaryManager;
import main.documentswork.Documents;
import main.documentswork.DocumentsManager;
import main.view.ConsoleApp;


public class Start {
    public static void main (String[] args){
        DocumentsManager documents = new Documents();
        DictionaryManager dictionary = new Dictionary();
        ConsoleApp console = new ConsoleApp(documents, dictionary);
        console.work();
    }
}
