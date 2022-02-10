package Main;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException {
        DictionaryManager dictionary = new Dictionary();
        FileWork documents = new Documents();
        Control control = new Console(documents, dictionary);
        control.work();
		

    }
}

