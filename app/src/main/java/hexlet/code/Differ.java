package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String str1, String Str2, String formatName) {
        str1 = "/mnt/d/VICTOR/HEXLET/java-project-71";
        Path testFilePath = Paths.get(str1);
        Path fileName = testFilePath.getFileName();
        System.out.println(fileName);
        return null;
    }
}
