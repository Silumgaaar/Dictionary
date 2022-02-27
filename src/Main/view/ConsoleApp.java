package main.view;

import main.dictionarywork.DictionaryManager;
import main.documentswork.DocumentsManager;


import java.io.IOException;
import java.util.*;


public class ConsoleApp {

    private final DocumentsManager documents;
    private final DictionaryManager dictionary;
    private String nameFile;
    public ConsoleApp(DocumentsManager documents, DictionaryManager dictionary){
        this.documents = documents;
        this.dictionary = dictionary;
    }
    public void work(){
        mainMenu();
    }
    private void mainMenu(){
        boolean exitProgram = false;
        while (!exitProgram){
            try {
                viewList(documents.getFileNames());
                System.out.println(ConsoleConstants.EXIT_INFO);
                System.out.print(ConsoleConstants.DICTIONARY_SELECTION);
                nameFile = ConsoleConstants.user.next();

                if(nameFile.equals(ConsoleConstants.EXIT)){
                    exitProgram = true;
                    continue;
                }
                HashMap<String,String> dictionarySelected;
                try {
                    dictionarySelected = documents.dictionaryHashMap(nameFile);
                    viewDictionary(dictionarySelected);

                } catch (NullPointerException e){
                    System.out.println(ConsoleConstants.FILE_NOT_FOUND);
                    continue;
                }
                workWitchDictionary(dictionarySelected);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private void workWitchDictionary(HashMap<String,String> dictionarySelected){
        System.out.println(Arrays.toString(CommandsMenu.values()));
        boolean exitMenu = false;
        while (!exitMenu){
            System.out.print(ConsoleConstants.ENTER_COMMAND);
            CommandsMenu command = CommandsMenu.checkCommand(ConsoleConstants.user.next());

            try {
                switch (Objects.requireNonNull(command)) {
                    case ADD -> addInDictionary(dictionarySelected);
                    case DEL -> removeInDictionary(dictionarySelected);
                    case VIEW -> viewDictionary(dictionarySelected);
                    case SEARCH -> searchInDictionary(dictionarySelected);
                    case BACK -> exitMenu = true;
                }
            } catch (NullPointerException e){
                System.out.println(ConsoleConstants.COMMAND_NOT_FOUND);
            } catch (IOException e){
                System.out.print(ConsoleConstants.FILE_NOT_FOUND);
            }
        }
    }
    private void addInDictionary(HashMap<String,String> dictionarySelected) throws IOException {
        System.out.print(ConsoleConstants.ENTERING_A_WORD);
        String newKey = ConsoleConstants.user.next();
        System.out.print(ConsoleConstants.ENTERING_A_TRANSLATION);
        String newMeaning = ConsoleConstants.user.next();
        String[] info = documents.getInfoDictionary(nameFile);

        if(dictionary.add( dictionarySelected,newKey, newMeaning,info) == null){
            System.out.println(ConsoleConstants.ERROR_CHECK);
        }
        else {
            dictionarySelected = dictionary.add(dictionarySelected, newKey, newMeaning, info);
            documents.fileOverWrite(dictionarySelected, info);
            System.out.println(ConsoleConstants.ADD_NEW_STRING);
        }
    }
    private void removeInDictionary(HashMap<String,String> dictionarySelected) throws IOException {
        System.out.print(ConsoleConstants.WORD_DELETE);
        String[] info = documents.getInfoDictionary(nameFile);
        dictionarySelected = dictionary.remove(dictionarySelected, ConsoleConstants.user.next());
        documents.fileOverWrite(dictionarySelected,info);
        System.out.println(ConsoleConstants.ENTRY_DELETED);

    }
    private void searchInDictionary(HashMap<String,String> dictionarySelected){
        System.out.print(ConsoleConstants.WORD_SEARCH);
        String str = dictionary.search(dictionarySelected,ConsoleConstants.user.next());
        System.out.println(Objects.requireNonNullElse(str, ConsoleConstants.STRING_NOT_FOUND));
    }
    private void viewList(List<String> list){
        for(String str : list){
            System.out.println(str);
        }
    }
    private void viewDictionary(HashMap<String,String> dictionary) {
        for (Map.Entry<String,String> entry : dictionary.entrySet()) {

            System.out.println(entry);

        }
    }
}
