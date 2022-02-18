package Main;

import Main.ConsoleWork.*;
import Main.DictionaryWork.*;
import Main.DirectoryWork.*;
import Main.DocumentWork.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DirectoryManager directory = new Directory();
        DocumentManager document = new Document();
        DictionaryManager dictionary = new Dictionary();
        Console control = new Console(directory,document,dictionary);
        control.start();
    }
} 