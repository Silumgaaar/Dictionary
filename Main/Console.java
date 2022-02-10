package Main;

import java.io.IOException;
import java.io.File;
import java.util.*;

public class Console implements Control {
	public static final String DICTIONARY_SELECTION = "Choose a dictionary";
	public static final String MENU = "Add - add line  |  Del - delete line  |  Ser - string search  |  View - output dictionary   |  Back - back to menu ";
	public static final String COMMAND_NOT_FOUND = "Command entered not found ";    
	public static final String EXIT = "Type Exit to end the program "; 
	public static final String WORD_SEARCH = "Enter a word "; 
	public static final String WORD_DELETE = "Word to delete ";
	public static final String STRING_NOT_FOUND = "String not found in dictionary ";
	public static final String ENTRY_DELETED = "The entry was successfully deleted ";
	public static final String ENTERING_A_WORD = "Enter a word "; 
	public static final String ENTERING_A_TRANSLATION = "Enter translation"; 
	private boolean exitProgram = false;
	private boolean exitMenu = false;

	FileWork documents;
	DictionaryManager dictionary;
	List<String> files = new ArrayList<>();
	HashMap<String, String> fileSelected = new HashMap<>();
	String nameFile;

	public Console(FileWork documents, DictionaryManager dictionary){
		this.documents = documents;
		this.dictionary = dictionary;
	}


	public void work() throws IOException {
		while(!exitProgram){
			exitMenu = false;
			System.out.println(DICTIONARY_SELECTION);
			files = documents.search();
 
			for (String l : files) {
				System.out.println(l); 
			}
		
			System.out.println(EXIT);

			Scanner console = new Scanner(System.in);
			nameFile = console.nextLine();
	
			if(nameFile.equals("Exit")){
				exitProgram = true;
				continue;
			} 
			else{
				fileSelected = documents.fileSelection(files, nameFile);
				System.out.println(fileSelected);
				System.out.println(MENU);
	
				while(!exitMenu){
           	 
					String input = console.nextLine();
		
					switch(input){
						case("Add"):
							System.out.println(ENTERING_A_WORD);
							String word = console.nextLine();
							System.out.println(ENTERING_A_TRANSLATION);
							String translation = console.nextLine();
							break;

						case("Del"):
							System.out.print(WORD_DELETE + " ");
							input = console.nextLine();
							if(fileSelected.containsKey(input)){
								fileSelected = dictionary.removeInDictionary(fileSelected, input);
								documents.fileOverWrite(fileSelected, nameFile);
								System.out.println(ENTRY_DELETED);
							}
							else
								System.out.println(STRING_NOT_FOUND);
			
			
							break;

						case("Ser"):
							System.out.print(WORD_SEARCH + " ");
							input = console.nextLine();
							if(dictionary.searchInDictionary(fileSelected, input) == null)
								System.out.println(STRING_NOT_FOUND);
							else
								System.out.println(dictionary.searchInDictionary(fileSelected, input));
							break;

						case("View"):
							System.out.println(fileSelected);
							break;
			
							case("Back"):
							exitMenu = true;
							break;

						default:
							System.out.println(COMMAND_NOT_FOUND);			
		
					}
				}
			}
		}
	}
}
