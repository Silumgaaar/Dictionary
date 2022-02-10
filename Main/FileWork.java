package Main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

interface FileWork{
    public List<String> search();
    public HashMap<String, String> fileSelection(List<String> files, String fileName) throws IOException;
    public void fileOverWrite(HashMap<String, String> newDictionary, String nameFile) throws IOException;
    public int filePosition(List<String> files, String input) throws IOException;
    public String[] rulesDictionary(int position)throws IOException;
    public HashMap addInDictionary(HashMap<String, String> fileSelected, String newKey, String newMeaning, String nameFile)throws IOException;
}

