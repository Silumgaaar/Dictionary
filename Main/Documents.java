package Main;
import java.nio.file.*; 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Paths;

public class Documents implements FileWork {
    public final static String DIRECTORY = "../resources";
    public final static String REGULAR_EXPRESSION = "RegularExpression.txt";
    public static final String EXTENSION = ".txt";



    public List<String> search(){
        File folder = new File(DIRECTORY);
        List<String> fileList = new ArrayList<>();

        for(File file : Objects.requireNonNull(folder.listFiles())){
            if(!(REGULAR_EXPRESSION.equals(file.getName())))
            {
                fileList.add(file.getName());
            }
        }

        return fileList;

    }
	
	public void fileOverWrite(HashMap<String, String> newDictionary, String nameFile)throws IOException{
		Files.write(Paths.get(DIRECTORY + "/" + nameFile + EXTENSION), ("").getBytes());
		for(Map.Entry<String, String> entry : newDictionary.entrySet()){
			Files.write(Paths.get(DIRECTORY + "/" + nameFile + EXTENSION), (entry.getKey() + "-" + entry.getValue() + "\n").getBytes(), StandardOpenOption.APPEND);
		}	
		
	} 

    public HashMap fileSelection(List<String> files, String input) throws IOException {

        HashMap<String, String> fileSelected = new HashMap<>();
        String[] strDictionary = new String[2];

        String nameFile = input;
        nameFile = nameFile + EXTENSION;
        File file = null;


        for(String str : files){
            if(nameFile.equals(str)) {
                file = new File(DIRECTORY, nameFile);
            }
        }

        Path dic = file.toPath();
        for(String str : Files.readAllLines(dic) ){
            strDictionary = str.split("-");

            fileSelected.put(strDictionary[0], strDictionary[1]);
        }

        return fileSelected;
    }
}
