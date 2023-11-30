package hexlet.code;

import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Formatter.formatter;
import static hexlet.code.Parser.parser;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String extension = getExtension(filePath1);
        Map<String, Object> map1 = getMap(filePath1, extension);
        Map<String, Object> map2 = getMap(filePath2, extension);
        List<Map<String,Object>> differList = TreeDiff.getDifferList(map1, map2);
        return formatter(differList, formatName);
    }

    public static Map<String, Object> getMap(String filePath, String formatName) throws Exception {
        String content = Files.readString(getPath(filePath));
        return parser(content, formatName);
    }

    public static Path getPath(String filePath) throws Exception {
        Path testFilePath = Paths.get(filePath);
        Path fileName = testFilePath.getFileName();
        // Формируем абсолютный путь, если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
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
