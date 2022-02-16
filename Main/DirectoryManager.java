import java.util.List;

public interface DirectoryManager {
    public void setDirectory(String directory);
    public String getDirectory();
    public List<String> getListFile();
    public boolean checkFile(String nameFile);
    public int getPosition(String nameFile);
}