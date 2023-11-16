package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Formatter.formatter;
import static hexlet.code.Parser.parser;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String extension = getExtension(filePath1);
        Map<String, Object> map1 = getMap(filePath1, extension);
        Map<String, Object> map2 = getMap(filePath2, extension);
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
        return formatter(allKeys, map1, map2, extension, formatName);
    }

    public static Map<String, Object> getMap(String filePath, String formatName) throws Exception {
        String content = Files.readString(getPath(filePath));
        return parser(content, formatName);
    }

    public static Path getPath(String filePath) throws Exception {
        Path testFilePath = Paths.get(filePath);
        Path fileName = testFilePath.getFileName();
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }

    private static String getExtension(String filePath) {
        return filePath.substring(filePath.indexOf(".") + 1);
    }

}
