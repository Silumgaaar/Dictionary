package main.factory;

import main.config.Config;
import main.documentswork.DocumentsManager;

import java.io.IOException;

public interface LibraryFactory {
    Config createConfig(DocumentsManager documentsManager, String name) throws IOException; // поправить исключения
}
