package Main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class Console {
    public static final String NEXT_LINE = "\n";
    public static final String DICTIONARY_SELECTION = "Choose a dictionary";
    public static final String EXIT_INFO = "Type Exit to end the program ";
    public static final String EXIT = "Exit";
    public static final String FILE_NOT_FOUND = "File not found in directory";
    public static final String EXTENSION = ".txt";
    public static final String MENU = "Add - add line  |  Del - delete line  |  Ser - string search  |  View - output dictionary   |  Back - back to menu ";
    public static final String COMMAND_ADD = "Add";
    public static final String ENTERING_A_WORD = "Enter a word ";
    public static final String ENTERING_A_TRANSLATION = "Enter translation";
    public static final String ADD_NEW_STRING = "Record successfully added";
    public static final String ERROR_CHECK = "The new pair does not meet the conditions of the dictionary";
    public static final String COMMAND_DEL = "Del";
    public static final String WORD_DELETE = "Word to delete ";
    public static final String ENTRY_DELETED = "The entry was successfully deleted ";
    public static final String STRING_NOT_FOUND = "String not found in dictionary ";
    public static final String COMMAND_SEARCH = "Ser";
    public static final String WORD_SEARCH = "Enter a word ";
    public static final String COMMAND_VIEW = "View";
    public static final String COMMAND_BACK = "Back";
    public static final String SPACE = " ";
    public static final String COMMAND_NOT_FOUND = "Command entered not found ";


    private boolean exitProgram = false;
    private boolean exitMenu = false;
    private String nameFile;

    DirectoryManager directory;
    DocumentManager document;
    DictionaryManager dictionary;
    List<String> files = new ArrayList<>();
    HashMap<String, String> dictionarySelected = new HashMap<>();
    String[] rules = new String[2];

    public Console(DirectoryManager directory, DocumentManager document, DictionaryManager dictionary){
        this.directory = directory;
        this.document = document;
        this.dictionary = dictionary;
    }

    public void start() throws IOException {
        while (!exitProgram){
            exitMenu = false;
            System.out.print(DICTIONARY_SELECTION + NEXT_LINE);

            files = directory.getListFile();
            viewListFile();
            document.currentDirectory(directory.getDirectory());
            System.out.print(EXIT_INFO + NEXT_LINE);
            chooseFile();

            if(nameFile.equals(EXIT)){
                exitProgram = true;
                continue;
            }
            if(directory.checkFile(nameFile + EXTENSION)){
                document.fileSelection(nameFile);
                dictionarySelected = document.getHashMapFileSelection(directory.getDirectory(),files);

            }
            else {
                System.out.print(FILE_NOT_FOUND + NEXT_LINE);
                continue;
            }
			commandView();
            //System.out.print(dictionarySelected + NEXT_LINE);
            System.out.print(MENU + NEXT_LINE);
			menu();
            
        }
    }
	
	private void menu() throws IOException {
		while (!exitMenu) {
            switch (input()) {
                case (COMMAND_ADD):
                    commandAdd();
                    break;
                case (COMMAND_DEL):
                    commandDel();
					break;
				case (COMMAND_SEARCH):
					commandSearch();
					break;
				case (COMMAND_VIEW):
					commandView();
					break;
				case (COMMAND_BACK):
					commandBack();
					break;
				default:
					System.out.println(COMMAND_NOT_FOUND);
           }
       }
	}
    private String input(){
        Scanner str = new Scanner(System.in);
        String inp = str.nextLine();
        return inp;
    }
    private void viewListFile(){
		String[] str = new String[2];
        for (String l : files){
		   str = l.split("\\.");
           System.out.println(str[0]);
        }
		
    }
    private void chooseFile() throws IOException {
        Scanner str = new Scanner(System.in);
        nameFile = str.nextLine();
    }
    private void commandAdd() throws IOException {
        System.out.print(ENTERING_A_WORD + NEXT_LINE);
        String word = input();
        System.out.print(ENTERING_A_TRANSLATION + NEXT_LINE);
        String translation = input();

        document.currentDirectory(directory.getDirectory());

        rules = document.getRules(directory.getPosition(nameFile + EXTENSION));

        if(dictionary.checkAddDictionary(rules, word, translation)){
            dictionarySelected = dictionary.addInDictionary(dictionarySelected, word, translation);
            document.addInFile(word, translation);
            System.out.print(ADD_NEW_STRING + NEXT_LINE);
        }
        else
            System.out.print(ERROR_CHECK + NEXT_LINE);
    }
    private void commandDel() throws IOException {
        System.out.print(WORD_DELETE + SPACE);
        String strDel = input();
        if(dictionarySelected.containsKey(strDel)){
            dictionarySelected = dictionary.removeInDictionary(dictionarySelected, strDel);
            document.fileOverWrite(dictionarySelected);
            System.out.print(ENTRY_DELETED + NEXT_LINE);
        }
        else
            System.out.print(STRING_NOT_FOUND + NEXT_LINE);
    }
    private void commandSearch(){
        System.out.print(WORD_SEARCH + SPACE);
        String strSer = input();
        if(dictionary.searchInDictionary(dictionarySelected, strSer) == null)
            System.out.print(STRING_NOT_FOUND + NEXT_LINE);
        else
            System.out.print(dictionary.searchInDictionary(dictionarySelected, strSer) + NEXT_LINE);

    }
    private  void commandView(){
        //System.out.println(dictionarySelected);
		for (Map.Entry entry: dictionarySelected.entrySet()) {

		System.out.print(entry + NEXT_LINE);

}
    }
    private  void commandBack(){
        exitMenu = true;
    }
} 