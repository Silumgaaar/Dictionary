package ru.yarkin.dictionarywork;

import ru.yarkin.exception.LibraryDictionariesNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Directory implements DictionaryManager{
    private static final String LIBRARY = "src/main/resources/Library.txt";
    private static final String DELIMITER = "'";

    private final Map<String,String> infoDictionaries = new HashMap<>();

    public Directory() throws LibraryDictionariesNotFoundException{
            filesDirectory();
    }

    @Override
    public boolean add(String newKey, String newValue) {
        return false;
    }
    @Override
    public boolean remove(String key) {
        return false;
    }

    @Override
    public Map<String, String> getAll() {
        return infoDictionaries;
    }

    @Override
    public String search(String key) {
        return infoDictionaries.get(key);
    }


    private void filesDirectory() throws LibraryDictionariesNotFoundException{
        try {
            File file = new File(LIBRARY);
            Path path = file.toPath();
            for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)){
                String[] info = line.split(DELIMITER);
                infoDictionaries.put(info[0],info[1]);
            }
        } catch (IOException e){
            throw new LibraryDictionariesNotFoundException(e.getMessage());
        }
    }

}
