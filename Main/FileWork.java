package Main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

interface FileWork{
    public List<String> search();
    public HashMap<String, String> fileSelection(List<String> files, String fileName) throws IOException;
    public void fileOverWrite(HashMap<String, String> newDictionary, String nameFile) throws IOException;
}

