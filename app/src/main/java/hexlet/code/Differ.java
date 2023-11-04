package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static hexlet.code.Parser.parser;

public class Differ {
    static List<String> diffList = new ArrayList<>();
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
//        String str1 = "/mnt/d/VICTOR/HEXLET/java-project-71/file1.json";
//        String filePath1 = "/home/kvv/PROJECTS/java-project-71/file1.json";
        if (formatName == null) {
            formatName = "yml";
        }
        diffList.clear();
        Map<String, Object> map1 = getMap(filePath1, formatName);
        Map<String, Object> map2 = getMap(filePath2, formatName);
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());
//        allKeys.forEach(k -> diffList.add(Differ.differOfMap(map1, map2, k)));
        for (String key : allKeys) {
            differOfMap(map1, map2, key);
        }
        return String.join("\n", diffList);
    }

    public static void differOfMap(Map<String, Object> data1, Map<String, Object> data2, String key) {
        Object valueMap1 = data1.get(key);
        Object valueMap2 = data2.get(key);
        if (data1.containsKey(key) && data2.containsKey(key)) {
            if (Objects.equals(valueMap1, valueMap2)) {
//                diffList.add("  " + key + ": " + (String)valueMap2);
                diffList.add(String.format("  %s: %s", key, valueMap2));
            } else {
                diffList.add(String.format("- %s: %s", key, valueMap1));
                diffList.add(String.format("+ %s: %s", key, valueMap2));
            }
        } else {
            if (!(data1.containsKey(key))) {
                diffList.add(String.format("+ %s: %s", key, valueMap2));
            } else {
                diffList.add(String.format("- %s: %s", key, valueMap1));
            }
        }
    }

    public static Map<String, Object> getMap(String filePath, String formatName) throws Exception {
        String content = Files.readString(getPath(filePath)); // Читаем файл
        return parser(content, formatName);
    }

    public static Path getPath(String filePath) throws Exception {
        Path testFilePath = Paths.get(filePath);
        Path fileName = testFilePath.getFileName();
        // Формируем абсолютный путь, если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }

}
