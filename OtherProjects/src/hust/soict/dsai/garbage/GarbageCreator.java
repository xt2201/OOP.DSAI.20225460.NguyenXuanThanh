package hust.soict.dsai.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) {
        try {
            String content = readFileToString("test.txt");
            System.out.println("Content length: " + content.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFileToString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}