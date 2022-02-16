import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Directory implements DirectoryManager {
    public static final String REGULAR_EXPRESSION = "RegularExpression.txt";

    private String directory = "../resource";
    private List<String> fileList = new ArrayList<>();
    private int position = 0;

    public void setDirectory(String directory){
        this.directory = directory;
    }
    public String getDirectory(){
        return directory;
    }
    public List<String> getListFile(){
        searchListFileInDirectory();
        return fileList;
    }
    public boolean checkFile(String nameFile){
        for(String str : fileList){
            if(nameFile.equals(str)){
                return true;
            }
        }
        return false;
    }
    public int getPosition(String nameFile){
        searchPositionFile(nameFile);
        return position;
    }


    private File searchDirectory(){
        return new File(this.directory);
    }
    private void searchListFileInDirectory(){
        fileList.clear();
        for(File file : Objects.requireNonNull(searchDirectory().listFiles())){
            if(!(REGULAR_EXPRESSION.equals(file.getName()))){
                fileList.add(file.getName());
            }
        }
    }
    private void searchPositionFile(String nameFile){
        position = 0;
        System.out.print(nameFile);
        for(String str : fileList){
            if(nameFile.equals(str)){
                break;
            }
            position++;
        }
    }

}