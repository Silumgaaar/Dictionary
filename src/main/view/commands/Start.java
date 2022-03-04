package main.view.commands;


import main.dictionarywork.DictionaryManager;

import java.util.List;

public class Start implements Commands{
    private static final String DICTIONARY_SELECTION = "Choose a dictionary: ";

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void execute(DictionaryManager dictionaryManager) {
        List<String> list;
        list = dictionaryManager.getDictionaries();
        StringBuilder s = new StringBuilder();

        for(String str : list){
            s.append(str).append("\n");
        }
        System.out.print(s + "\n" + DICTIONARY_SELECTION);
    }
}
