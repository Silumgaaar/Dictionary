package main.view.commands;

import main.dictionarywork.DictionaryManager;
import main.view.InfoCommands;
import main.view.User;
import java.util.List;
import java.util.Map;

public class Back implements Commands{
    private final InfoCommands infoCommands;
    private static final String DICTIONARY_SELECTION = "Choose a dictionary: ";
    private static final String FILE_NOT_FOUND = "File not found in directory";

    public Back(){
        infoCommands = InfoCommands.getCommandInfo("back");
    }

    @Override
    public InfoCommands getInfo() {
        return infoCommands;
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
        while (!dictionaryManager.newDictionary(User.choice.next())){
            System.out.println(FILE_NOT_FOUND);
        }

        for (Map.Entry<String,String> entry : dictionaryManager.getDictionary().entrySet()) {
            System.out.println(entry);
        }
        System.out.println(infoCommands.viewMenu());
    }
}
