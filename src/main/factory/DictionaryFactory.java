package main.factory;

import main.config.Config;
import main.config.ConfigDictionary;
import main.documentswork.DocumentsManager;

import java.io.IOException;

public class DictionaryFactory implements LibraryFactory {
    public Config createConfig(DocumentsManager documentsManager, String name) throws IOException { // поправить исключения
        return new ConfigDictionary(documentsManager, name);
    }
}
