package main;

import main.consolework.*;
import main.dictionarywork.*;
import main.directorywork.*;
import main.documentwork.*;

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