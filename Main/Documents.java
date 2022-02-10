package Main;
import java.nio.file.*; 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Documents implements FileWork {
    public final static String DIRECTORY = "../resources";
    public final static String REGULAR_EXPRESSION = "RegularExpression.txt";
    public static final String EXTENSION = ".txt";
	int position = 0;



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
	public HashMap addInDictionary(HashMap<String, String> fileSelected, String newKey, String newMeaning, String nameFile)throws IOException{
		fileSelected.put(newKey,newMeaning);
		Files.write(Paths.get(DIRECTORY + "/" + nameFile + EXTENSION), (newKey + "-" + newMeaning + "\n").getBytes("UTF-8"), StandardOpenOption.APPEND);
		return fileSelected;
		//for(Map.Entry<String, String> entry : fileSelected.entrySet()){
		//	Files.write(Paths.get(DIRECTORY + "/" + nameFile + EXTENSION), (entry.getKey() + "-" + entry.getValue() + "\n").getBytes("UTF-8"),StandardOpenOption.APPEND);
		//}
		
	}
	
	public void fileOverWrite(HashMap<String, String> newDictionary, String nameFile)throws IOException{
		Files.write(Paths.get(DIRECTORY + "/" + nameFile + EXTENSION), ("").getBytes("UTF-8"));
		for(Map.Entry<String, String> entry : newDictionary.entrySet()){
			Files.write(Paths.get(DIRECTORY + "/" + nameFile + EXTENSION), (entry.getKey() + "-" + entry.getValue() + "\n").getBytes("UTF-8"),StandardOpenOption.APPEND);
		}	
		
	} 
	public int filePosition(List<String> files, String input) throws IOException{
		position = 0;
		String nameFile = input;
		nameFile = nameFile + EXTENSION;
		for(String str : files){
			
			if(nameFile.equals(str)){
				break;
			}
			position++;
		}
		return position;
	}
	public String[] rulesDictionary(int position)throws IOException{ 
		String[] rul = new String[2];
		String rules = Files.readAllLines(Paths.get(DIRECTORY + "/" + REGULAR_EXPRESSION)).get(position);
		return rul = rules.split(",");
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
		if(file == null){
		}
		else{
			Path dic = file.toPath();
		
			for(String str : Files.readAllLines(dic) ){
				strDictionary = str.split("-");
		
				fileSelected.put(strDictionary[0], strDictionary[1]);
			}
		}
        return fileSelected;
    }
}
